Feature: Ability to navigate to different approved websites

  @test @job
  Scenario: Ability for the user to navigate to JobCentreQA
    Given I navigate to "GoogleQA"
    Given I enter xpath "Gmail" into "googleField"
    And I click on "googleClickButton"
#    When I click the UC link
 #   Then User has navigated to Gov.uk website


  Scenario: Ability for the user to navigate to UC
    Given I navigate to "UCwebsiteQA"
    And I click on "HowToClaimLinkQA"
    Then the user has landed on "universalcredittext"
