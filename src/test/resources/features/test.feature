Feature: test feature




  @test
  Scenario Outline: Test to check different websites
    Given I navigate to "https://www.google.com/"
    Given I enter xpath "HelloWorld" into "/html/body/div[1]/div[3]/form/div[2]/div/div[1]/div/div[1]/input"
    And I click on "/html/body/div[1]/div[3]/form/div[2]/div/div[3]/center/input[1]"
    When I click the UC link
    Then User has navigated to Gov.uk website

  Examples:
  |Yahoo|
  |Gmail  |
  |Universal Credit|
