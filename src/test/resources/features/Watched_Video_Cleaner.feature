@watched-video-cleaner
Feature: Validate Watched Video Cleaner

  Background: Launching of the app
    Given The user is on the application's home page   

  Scenario: Verify that the user can view the Watched Video Cleaner popup
    When The user taps on Watched Video Cleaner from the three-dot More Options menu
    Then The user should see the Watched Video Cleaner popup with buttons Cancel and Clean
    
    
  Scenario: Verify the Cancel functionality of the Watched Video Cleaner popup
    Given The user is on the Watched Video Cleaner popup
    When The user taps the Cancel button on the Watched Video Cleaner popup
    Then The watched video cleaner popup should close and the user remains on the home page
    
  
  Scenario: Verify the Clean functionality with both checkboxes checked
    Given The user has some bookmarks and downloads and is on the Watched Video Cleaner popup 
    When The user checks both Remove watched downloads and Remove watched bookmarks checkboxes and taps the Clean button
    Then Both Watched bookmarks and downloads should be removed    
  
  Scenario: Verify the Clean functionality with Remove watched downloads checkbox checked
    Given The user has some bookmarks and downloads and is on the Watched Video Cleaner popup
    When The user checks only the Remove watched downloads checkbox and taps the Clean button
    Then Only the watched downloads should be removed
    
    
  Scenario: Verify the Clean functionality with Remove watched bookmarks checkbox checked
    Given The user has some bookmarks and downloads and is on the Watched Video Cleaner popup
    When The user checks only the Remove watched bookmarks checkbox and taps the Clean button
    Then Only the watched bookmarks should be removed