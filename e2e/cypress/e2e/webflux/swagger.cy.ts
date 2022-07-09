describe('empty spec', () => {
  it('shows swagger', () => {
    cy.visit('http://localhost:8081/webjars/swagger-ui/index.html');
    cy.get('h2').contains('OpenAPI definition').should('not.be.null');
    cy.wait(1000);
  });
})