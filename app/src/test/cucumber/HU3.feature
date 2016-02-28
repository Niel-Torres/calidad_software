Feature: Access to the app sections by pushing on the menu

Scenario: Go to the Home section
		Given any section of the app
			And I press on the "menu" button
		When I want to go to the Home page
		Then I press on the "Home" button of the menu

Scenario: Go to the Team section
		Given any section of the app
			And I press on the "menu" button
		When I want to go to the Team page
		Then I press on the "Team" button of the menu

Scenario: Go to the Market section
		Given any section of the app
			And I press on the "menu" button
		When I want to go to the Market page
		Then I press on the "Market" button of the menu

Scenario: Go to the League section
		Given any section of the app
			And I press on the "menu" button
		When I want to go to the League page
		Then I press on the "League" button of the menu

Scenario: Go to My Account section
		Given any section of the app
			And I press on the "menu" button
		When I want to go to the My Account page
		Then I press on the "My Account" button of the menu

Scenario: Go to the Rules page
		Given any section of the app
			And I press on the "menu" button
		When I want to go to the Rules page
		Then I press on the "Rules" button of the menu

Scenario: Go to the Privacy Policy page
		Given any section of the app
			And I press on the "menu" button
		When I want to go to the Privacy Policy page
		Then I press on the "Privacy Policy" button of the menu
