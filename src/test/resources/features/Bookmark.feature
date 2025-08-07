Feature: Bookmark Functionality

  Scenario: Verify that the user is able to navigate to "Bookmarks" tab
  Given The user is in the home page
  When The user clicks on Bookmarks Tab
  Then The user should be able to view the message Bookmark some videos to find them here.
  
  Scenario: Verify that the user is able to bookmark the video after clicking "More Options" in the home page
  Given The user is in the home page
  When The user clicks on More Options and then clicks on Bookmark in the menu
  Then The user should be able to view the bookmark video under Bookmarks tab with a message Bookmarked
  
  Scenario: Verify that the user is able to unbookmark the video after clicking "More Options" in the home page
  Given The user is under Bookmarks tab
  When The user clicks on More Options and then clicks on Unbookmark in the menu
  Then The user should not be able to view the bookmarked video under Bookmarks tab with a message Bookmark removed