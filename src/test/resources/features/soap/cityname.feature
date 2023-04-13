Feature: Get the list of city names with their geographic coordinates
  AS
  user of the service global weather
  I WANT TO
  get city names with their geographic coordinates
  SO THAT
  see list of city names and their geographic coordinates

  @cities
  Scenario: Get list of city names with their geographic coordinates successfully
    Given that the user has access to the LatLonListCityNames service
    When send a SOAP request with the latitude and longitude of the desired area
    Then you should receive a successful response with a list of city names and their geographic coordinates