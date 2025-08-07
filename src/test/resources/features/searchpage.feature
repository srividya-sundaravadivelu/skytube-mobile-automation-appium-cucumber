@search
Feature: Verify the search channel functionality

Background: Launching of the app
    Given The user is on the application's home page
    
    Scenario: Search the channel in the application
    When The user search the "Cocomelon" channel
    Then The "Cocomelon - Nursery Rhymes" channel name should displayed
    
    
    Scenario: Search the channel in the application with wrong input
    When The user search the "bqtwgcunknown" channel
    Then The "bqtwgcunknown" channel name should not be displayed
    
    Scenario: Search the invalid channel in the application with error message
    When The user search the "-bqtwgcunknown-" channel
    Then The error message should be displayed