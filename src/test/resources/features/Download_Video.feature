@download-video
Feature: Validate download video 

  Background: Launching of the app
    Given The user is on the application's home page
    
  Scenario: Verify that the Downloads Tab is  empty after launching application     
    When The user shoul be on the application's downloads tab
    Then The Downloads Tab should display a message Download some videos to find them here   
    
  Scenario: Verify that the user is able  to Download a video
    When user taps three dots next to the video and select Download option of the video
    Then The selected video should be under downloads tab
    
  Scenario: Verify that the user is able  to Delete Downloaded  video
    Given The user is on the application's downloads tab 
    When user taps three dots next to the video and select Delete Download option of the video
    Then The selected video should be deleted from downloads tab
        
  

    
  