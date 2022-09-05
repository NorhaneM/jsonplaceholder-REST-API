@Runner
Feature: GET | GET a random user ID and Verify its associated posts


  Scenario:GET a random user ID and Verify its associated posts

    Given user navigate to Base URL
    Then GET random user ID and print his email to console
    And get this user’s associated posts and verify they contains a valid Post IDs
