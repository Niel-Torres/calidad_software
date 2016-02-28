Feature: Select alignments
	In order to manage my team alignments
  	As an user
  	I want to select multiple types of alignments

Scenario: Select a default/saved alignment 1
    	Given that I am on my alignments
     	When I select a "4"-"4"-"2" alignment
     	Then the players change the alignment

Scenario: Select a default/saved alignment 2
    	Given that I am on my alignments
     	When I select a "4"-"3"-"3" alignment
     	Then the players change the alignment

Scenario: Select a default/saved alignment 3
    	Given that I am on my alignments
     	When I select a "3"-"4"-"3" alignment
     	Then the players change the alignment

Scenario: create a new valid alignments
    	Given that I am on my alignments
     	When I want to create a new one
	And I push "add alignment" button
	And yet I type a valid "5"-"3"-"2" alignment
     	Then the app creates the new alignment
	And my team change the alignment

Scenario: create a new invalid alignments
    	Given that I am on my alignments
     	When I want to create a new one
	And I push "add alignment" button
	And yet I type a invalid "5"-"3"-"5" alignment
     	Then the app shows me an error because the alignment is over 10 players
	And give me another chance to introduce a new one
