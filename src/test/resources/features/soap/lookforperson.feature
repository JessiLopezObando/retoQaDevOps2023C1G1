Feature: Person Data
  As an administrator of a SOAP search service for person data by their code
  I want to be able to view the data of a person
  So that I can validate the search functionality.

  Scenario: Successful person search
    Given the administrator wants to search for a person
    When the administrator makes a request to search for the person by their code
    Then the administrator should see the data of the person associated with the code.