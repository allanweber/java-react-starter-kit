/// <reference types="cypress" />

describe('Food Search', () => {
  beforeEach(() => {
    // Mock the search API endpoint
    cy.intercept('GET', '/api/v1/foods/search*', {
      statusCode: 200,
      body: {
        content: [
          {
            id: '1',
            name: 'Apple',
            description: 'Fresh red apple',
            caloriesPer100g: 52,
            proteinPer100g: 0.3,
            carbsPer100g: 14,
            fatsPer100g: 0.2,
            fiberPer100g: 2.4,
            sugarPer100g: 10,
            sodiumPer100g: 1,
            servingSizeG: 100,
            servingSizeUnit: 'g'
          }
        ],
        totalElements: 1,
        totalPages: 1,
        size: 10,
        number: 0
      }
    }).as('searchFoods')

    // Visit the food search page
    cy.visit('/')
  })

  it('should display search results', () => {
    // Search for a food item
    cy.searchFoods('apple')
    
    // Wait for the API call to complete
    cy.wait('@searchFoods')
    
    // Verify results are displayed
    cy.get('[data-testid="food-card"]').should('exist')
    cy.contains('Apple').should('be.visible')
  })

  it('should show no results message when no matches found', () => {
    // Mock empty results
    cy.intercept('GET', '/api/v1/foods/search*', {
      statusCode: 200,
      body: {
        content: [],
        totalElements: 0,
        totalPages: 0,
        size: 10,
        number: 0
      }
    }).as('searchFoodsEmpty')

    // Search for a non-existent food
    cy.searchFoods('nonexistentfood123')
    
    // Wait for the API call to complete
    cy.wait('@searchFoodsEmpty')
    
    // Verify no results message
    cy.contains('No foods found').should('be.visible')
  })

  it('should handle pagination', () => {
    // Mock paginated results
    cy.intercept('GET', '/api/v1/foods/search*', (req: any) => {
      const page = req.query.page || '0'
      const response = {
        content: [
          {
            id: page === '0' ? '1' : '2',
            name: page === '0' ? 'Apple' : 'Banana',
            description: page === '0' ? 'Fresh red apple' : 'Ripe banana',
            caloriesPer100g: 52,
            proteinPer100g: 0.3,
            carbsPer100g: 14,
            fatsPer100g: 0.2,
            fiberPer100g: 2.4,
            sugarPer100g: 10,
            sodiumPer100g: 1,
            servingSizeG: 100,
            servingSizeUnit: 'g'
          }
        ],
        totalElements: 2,
        totalPages: 2,
        size: 1,
        number: parseInt(page)
      }
      req.reply(response)
    }).as('searchFoodsPaginated')

    // Search for a common term that should return multiple results
    cy.searchFoods('fruit')
    
    // Wait for the API call to complete
    cy.wait('@searchFoodsPaginated')
    
    // Verify pagination controls are visible
    cy.contains('Page').should('be.visible')
    
    // Click next page
    cy.contains('Next').click()
    
    // Wait for the second page API call
    cy.wait('@searchFoodsPaginated')
    
    // Verify we're on page 2
    cy.contains('Page 2 of').should('be.visible')
    cy.contains('Banana').should('be.visible')
  })

  it('should clear results when search input is cleared', () => {
    // First search for something
    cy.searchFoods('apple')
    
    // Wait for the API call to complete
    cy.wait('@searchFoods')
    
    // Clear the search input
    cy.get('input[placeholder*="Search foods"]').clear()
    
    // Verify search results are cleared
    cy.get('[data-testid="food-card"]').should('not.exist')
    cy.contains('No foods found').should('not.exist')
  })
}) 