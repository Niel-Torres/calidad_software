package com.example.grupo7.comunio;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class HU2 {

    @Given("^that I am on my alignments$")
    public void that_i_am_on_my_alignments() throws Throwable {
        throw new PendingException();
    }

    @When("^I select a \"([^\"]*)\"-\"([^\"]*)\"-\"([^\"]*)\" alignment$")
    public boolean i_select_a_somethingsomethingsomething_alignment(String strArg1, String strArg2, String strArg3) throws Throwable {
	int a = Integer.parseInt(strArg1);
	int b = Integer.parseInt(strArg2);
	int c = Integer.parseInt(strArg3);       
	if((a+b+c) == 10){
		return true;
	}
	else{
		return false;
	}
    }

    @When("^I want to create a new one$")
    public void i_want_to_create_a_new_one() throws Throwable {
        throw new PendingException();
    }

    @Then("^the players change the alignment")
    public void my_team_is_placed_in_the_alignment() throws Throwable {
        throw new PendingException();
    }

    @Then("^the app creates the new alignment$")
    public void the_app_creates_the_new_alignment() throws Throwable {
        throw new PendingException();
    }

    @Then("^the app shows me an error because the alignment is over 10 players$")
    public void the_app_shows_me_an_error_because_the_alignment_is_over_10_players() throws Throwable {
        throw new PendingException();
    }

    @And("^I push \"([^\"]*)\" button$")
    public boolean i_push_something_button(String strArg1) throws Throwable {
        int a = Integer.parseInt(strArg1);
	int b = Integer.parseInt(strArg2);
	int c = Integer.parseInt(strArg3);       
	if(strArg1.equals("add alignment")){
		return true;
	}
	else{
		return false;
	}
    }

    @And("^yet I type a \"([^\"]*)\"-\"([^\"]*)\"-\"([^\"]*)\" alignment$")
    public boolean yet_i_type_a_somethingsomethingsomething_alignment(String strArg1, String strArg2, String strArg3) throws Throwable {
        int a = Integer.parseInt(strArg1);
	int b = Integer.parseInt(strArg2);
	int c = Integer.parseInt(strArg3);       
	if((a+b+c) == 10){
		return true;
	}
	else{
		return false;
	}
    }

    @And("^my team change the alignment$")
    public void my_team_is_placed_in_the_alignment() throws Throwable {
        throw new PendingException();
    }

    @And("^give me another chance to introduce a new one$")
    public void give_me_another_chance_to_introduce_a_new_one() throws Throwable {
        throw new PendingException();
    }

}
