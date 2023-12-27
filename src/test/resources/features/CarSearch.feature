@Regression @PerformCarSearch
Feature: Scenarios for performing car search

  @Smoke
  Scenario Outline: Perform '<StockType>' car search
    Given user is on the Cars Shopping page
    And user selects '<StockType>' in Stock Type dropdown
    And user selects '<Make>' in Make dropdown
    And user selects '<Model>' in Model dropdown
    And user selects '<Price>' in Price dropdown
    And user selects '<Distance>' in Distance dropdown
    And user enters '50131' into ZIP input
    When user clicks Search button
    Then Results page should be displayed with header '<Header>'

    Examples:
      | StockType       | Make      | Model          | Price   | Distance       | Header                                       |
      | New & used      | Ford      | Mustang        | $60,000 | All miles from | New and used Ford Mustang for sale           |
      | New             | Chevrolet | Silverado 1500 | $50,000 | 500 miles      | New Chevrolet Silverado 1500 for sale        |
      | Used            | Audi      | S6             | $45,000 | All miles from | Used Audi S6 for sale                        |
      | Certified       | Hyundai   | Sonata         | $30,000 | 250 miles      | Certified used Hyundai Sonata for sale       |
      | New & certified | Honda     | Accord         | $40,000 | 150 miles      | New and certified used Honda Accord for sale |


  @Smoke @DropdownValidation
  Scenario Outline: Validate New Model dropdown options for '<Make>'
    Given user is on the Cars Shopping page
    And user selects '<StockType>' in Stock Type dropdown
    When user selects '<Make>' in Make dropdown
    Then model dropdown should contain expected new model options for Make '<Make>'
    Examples:
      | StockType | Make  |
      | New       | Acura |
      | New       | Buick |

    @Smoke @GetVehicleData
    Scenario Outline: Get used vehicle data for '<Model>'
      Given user is on the Cars Shopping page
      And user selects '<StockType>' in Stock Type dropdown
      And user selects '<Make>' in Make dropdown
      And user selects '<Model>' in Model dropdown
      And user selects '<Price>' in Price dropdown
      And user selects '<Distance>' in Distance dropdown
      And user enters '50312' into ZIP input
      When user clicks Search button
      Then Results page should be displayed with header '<Header>'
      And log details of each used vehicle found

      Examples:
        | StockType | Make    | Model  | Price   | Distance       | Header                                 |
        | Used      | Audi    | A6     | $20,000 | All miles from | Used Audi A6 for sale                  |
        | Certified | Hyundai | Sonata | $30,000 | 250 miles      | Certified used Hyundai Sonata for sale |
        | Used      | Mercury | Cougar | $15,000 | All miles from | Used Mercury Cougar for sale           |
