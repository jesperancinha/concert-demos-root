describe('WebFlux E2E Tests', () => {
  it('shows swagger', () => {
    let host = Cypress.env('host.webflux') ? Cypress.env('host.webflux') : 'localhost';
    cy.visit(`http://${host}:8081/webjars/swagger-ui/index.html`);
    cy.get('h2').contains('OpenAPI definition').should('not.be.null');
    cy.wait(1000);

    cy.get('div[class="servers"] > label > select > option').should('have.value', 'http://localhost:8081');
  });
})