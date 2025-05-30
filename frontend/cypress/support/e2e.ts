/// <reference types="cypress" />
/// <reference types="@testing-library/cypress" />

import '@testing-library/cypress/add-commands'

declare global {
  namespace Cypress {
    interface Chainable {
      waitForLoadingToFinish(): void
      searchFoods(query: string): void
      intercept(method: string, url: string, response?: any): Chainable
      intercept(method: string, url: string, handler: (req: { 
        query: Record<string, string>,
        reply: (response: any) => void 
      }) => void): Chainable
    }
  }
}

const cypress = (window as any).Cypress

// Custom command to wait for loading spinner to disappear
cypress.Commands.add('waitForLoadingToFinish', () => {
  cy.get('[data-testid="loading-spinner"]').should('not.exist')
})

// Custom command to search for foods
cypress.Commands.add('searchFoods', (query: string) => {
  cy.get('input[placeholder*="Search foods"]').type(query)
  cy.waitForLoadingToFinish()
}) 