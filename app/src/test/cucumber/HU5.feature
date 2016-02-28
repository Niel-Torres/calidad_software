Feature: Read the news about the players' transferences between teams
	In order to be updated
  	As an user
  	I want to know in which team are the players going to be

Scenario: Open the app on the Home section
    	Given the opened app
      		And I am on the login
     	When I type the user "urjc@gmail.com"
      		And type password "donquijote"
        	And I push "enter"
     	Then I enter my profile view
     		And the app open the Home section

Scenario: Go to the Home section to read the news
		Given any section of the application
			And I press on the menu button
		When I want to go to the home page
		Then I press on the home button of the menu

Scenario: Scroll to read the transference's news
    	Given the homescreen app
      		And I am on the Inicio Screen
     	When I scroll to down
     	Then I can read every single transference

Scenario: Introduce information in the news screen
    	Given the homescreen app
        	And I am on the Inicio Screen
     	When I push on the "+" icon
      		And I want to introduce information
     	Then I write what I want to show
      	And press the "OK" button

Scenario: Automatically update the information news
    	Given the homescreen app
        	And I am on the Inicio Screen
     	When some user buy or shell a player
     	Then the app shows a notification in the Home section
