Feature: Get longitude and latitude
  ME AS
    a user of the postal code service
  I WANT
    to know the latitude and longitude of a place by its zip code
  SO THAT
    I can locate the place on a map

  @LatLongList
   Scenario: Get longitude and latitude
    Given a user knows the postal code of a place
    When the user sends the request with the zip code to the API
    Then the user gets the latitude and longitude of the place