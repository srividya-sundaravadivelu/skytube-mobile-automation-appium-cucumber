@SubscribeandUnsubscribe
Feature: Verify the subscribe and unsubscribe channel functionality

Background: Launching of the app and search the channel
    Given The user is on the application's home page
    When The user search the "Cocomelon" channel
    
Scenario: Verify that user is able to "Subscribed" to the channel through Menu Option
  When The user navigate to channel option from menu and click on subscribe option
  Then The "Subscribed" popup message should be displayed
  
Scenario: Verify that user is able to "UnSubscribed" to the channel through Menu Option
  When The user navigate to channel option from menu and click on unsubscribe option
  Then The "Unsubscribed" popup message should be displayed
  
Scenario: Verify that user is able to "Subscribed" to the channel through SUBSCRIBE button
  When The user navigate to channel and click on subscribe option 
  Then The "Subscribed" popup message should be displayed
  
Scenario: Verify that user is able to "Subscribed" channel list displayed in subscription page
  When The user navigate to subscription page
  And click on subscribe channel
  Then The user navigate to subscribed channel

  
Scenario: Verify that user is able to "UnSubscribed" to the channel through UNSUBSCRIBE button
  When The user navigate to channel and click on unsubscribe option
  Then The "Unsubscribed" popup message should be displayed