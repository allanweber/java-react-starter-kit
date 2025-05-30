/// <reference types="cypress" />
/// <reference types="@testing-library/cypress" />

declare namespace Cypress {
  interface Chainable {
    waitForLoadingToFinish(): Chainable<void>
    searchFoods(query: string): Chainable<void>
  }
} 