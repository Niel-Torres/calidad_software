Feature: Buy-sell and recover players
        In order to manage the team
		As an user
		I want to buy and sell players

Scenario: Scroll to see the avaible players in the market
    	Given the market section
			And I am on the players' list page
     	When I scroll down
     	Then I can see the avaible market's players

Scenario: Buy player from market section
		Given the market's list of players
        	And each player has a price
		When I press on a player's card
		Then I have the option to buy the player

Scenario: Scroll to see the players of your
    	Given the team' section
      		And I am on the players' list page
     	When I scroll down
     	Then I can see my team's players

Scenario: Sell player from my team
		Given the market's list of players
			And each player has an approximate price
		When I press on a player's card
		Then I have the option to put the player in the market to sell

Scenario: Recover a player from my team in the market
		Given the market's list of players
			And each player has an approximate price
		When I press on a player's card from my team
		Then I have the option to recover the player
