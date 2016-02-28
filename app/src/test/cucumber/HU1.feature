Feature: Login and logout in my application
	In order to manage my players
  	As an user
  	I want to connect to my profile aplication

Scenario: Succesfull Login the application
    	Given the opened application
      	And I am on the login page
     	When I type the user name "urjc@gmail.com"
      	And type the password "donquijote"
	And yet push "enter"
     	Then I enter my profile

Scenario: Succesfull Logout the application
    	Given in my profile
      	And I am managing my players
     	When I finish with the manage
      	And I want to close the application
     	Then I push the "logout" button
      	And come back to the login page

Scenario: Create a new account
    	Given the opened application
      	And I am on the login application
	And yet I haven't an account
     	When I tap on the "create account" button
     	Then it shows a fields to create a new account

Scenario: Error with the user name in the login
    	Given the opened application
      	And I am on the login page
     	When I type a wrong user name "ucm@gmail.com"
      	And type the password "donquijote"
	And yet push "enter"
     	Then I don't enter my profile
	And the application warns me of an error
	And yet give me a second chance to enter the application

Scenario: Error with the password in the login
    	Given the opened application
      	And I am on the login page
     	When I type the user name "urjc@gmail.com"
      	And type a wrong password "parguela"
	And yet push "enter"
     	Then I don't enter my profile
	And the application warns me of an error
	And yet give me a second chance to enter the application
