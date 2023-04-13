Feature: Country Name
  As an administrator of a SOAP search service
  I want to be able to view countries by their codes
  So that I can validate the functionality of searching for a country by its code.

  @validCountrySearch
  Scenario Outline: Successful country search
    Given the administrator wants to search for a country by its corresponding international code
    When the administrator makes a request to search for the country with the <code> code
    Then the administrator should see the name of the country corresponding to the provided code and a <status>
    Examples:
      | code | status |
      | "CO" | 200    |
      | "AR" | 200    |