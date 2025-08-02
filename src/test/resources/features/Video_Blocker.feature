@video-blocker
Feature: Validate Video blocker 

  Background: Launching of the app
    Given The user is on the application's home page

  Scenario: Verify that the user can view the video blocker popup
    When The user taps the shield icon next to search button
    Then The user should see the Video Blocker popup with buttons Cancel and Setup
    
  Scenario: Verify the Cancel functionality of the video blocker popup 
  	Given The user is on the video blocker popup  	
  	When The user taps the Cancel button
  	Then The popup should close and the user remains on the home page
  	
  Scenario: Verify the Set up functionality of the video blocker popup
  	Given The user is on the video blocker popup  	
  	When The user taps the Set up button
  	Then The user is navigated to the Preferences page
  
  
  
    
  