Feature: Preferences Functionality

Scenario Outline: Verify that the user is able to view "<menu>" menu in the Preferences page
  Given The user is in the home page
  When The user clicks on Preferences menu
  Then The user should be able to view "<menu>" menu in the Preferences page

Examples:
  | menu                   |
  | Video Player           |
  | Video Blocker          |
  | SponsorBlock           |
  | Import/Export          |
  | Privacy                |
  | Network and Downloads  |
  | Others                 |
  | About                  |