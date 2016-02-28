package com.example.grupo7.comunio

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class MyStepDefinitions {

    @Given("^the opened application$")
    public void the_opened_application() throws Throwable {
        throw new PendingException();
    }

    @Given("^in my profile$")
    public void in_my_profile() throws Throwable {
        throw new PendingException();
    }

    @When("^I type the user name \"([^\"]*)\"$")
    public boolean i_type_the_user_name_something(String strArg1) throws Throwable {
        if(strArg1.equals("urjc@gmail.com")){
		return true;
	}
	else{
		return false;
	}
    }

    @When("^I finish with the manage$")
    public void i_finish_with_the_manage() throws Throwable {
        throw new PendingException();
    }

    @When("^I tap on the \"([^\"]*)\" button$")
    public boolean i_tap_on_the_something_button(String strArg1) throws Throwable {
        if(strArg1.equals("create account")){
		return true;
	}
	else{
		return false;
	}
    }

    @When("^I type a wrong user name \"([^\"]*)\"$")
    public boolean i_type_a_wrong_user_name_something(String strArg1) throws Throwable {
        if(strArg1.equals("ucm@gmail.com")){
		return true;
	}
	else{
		return false;
	}
    }

    @Then("^I enter my profile$")
    public void i_enter_my_profile() throws Throwable {
        throw new PendingException();
    }

    @Then("^I push the \"([^\"]*)\" button$")
    public boolean i_push_the_something_button(String strArg1) throws Throwable {
        if(strArg1.equals("logout")){
		return true;
	}
	else{
		return false;
	}
    }

    @Then("^it shows a fields to create a new account$")
    public void it_shows_a_fields_to_create_a_new_account() throws Throwable {
        throw new PendingException();
    }

    @Then("^I don't enter my profile$")
    public void i_dont_enter_my_profile() throws Throwable {
        throw new PendingException();
    }

    @And("^I am on the login page$")
    public void i_am_on_the_login_page() throws Throwable {
        throw new PendingException();
    }

    @And("^type the password \"([^\"]*)\"$")
    public boolean type_the_password_something(String strArg1) throws Throwable {
        if(strArg1.equals("donquijote")){
		return true;
	}
	else{
		return false;
	}
    }

    @And("^yet push \"([^\"]*)\"$")
    public boolean yet_push_something(String strArg1) throws Throwable {
        if(strArg1.equals("enter")){
		return true;
	}
	else{
		return false;
	}
    }

    @And("^I am managing my players$")
    public void i_am_managing_my_players() throws Throwable {
        throw new PendingException();
    }

    @And("^I want to close the application$")
    public void i_want_to_close_the_application() throws Throwable {
        throw new PendingException();
    }

    @And("^come back to the login page$")
    public void come_back_to_the_login_page() throws Throwable {
        throw new PendingException();
    }

    @And("^I am on the login application$")
    public void i_am_on_the_login_application() throws Throwable {
        throw new PendingException();
    }

    @And("^yet I haven't an account$")
    public void yet_i_havent_an_account() throws Throwable {
        throw new PendingException();
    }

    @And("^the application warns me of an error$")
    public void the_application_warns_me_of_an_error() throws Throwable {
        throw new PendingException();
    }

    @And("^yet give me a second chance to enter the application$")
    public void yet_give_me_a_second_chance_to_enter_the_application() throws Throwable {
        throw new PendingException();
    }

    @And("^type a wrong password \"([^\"]*)\"$")
    public boolean type_a_wrong_password_something(String strArg1) throws Throwable {
        if(strArg1.equals("parguela")){
		return true;
	}
	else{
		return false;
	}
    }

}
