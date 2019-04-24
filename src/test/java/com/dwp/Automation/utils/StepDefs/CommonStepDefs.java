package com.dwp.Automation.utils.StepDefs;

import com.dwp.Automation.utils.AbstractWebconn;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.junit.Assert;

public class CommonStepDefs {

    AbstractWebconn connector = new AbstractWebconn();

    @Given("^I navigate to \"([^\"]*)\"$")
    public void goTogoogle(String arg) {
        connector.navigate(arg);


    }


    @And("^I enter Yahoo into search field$")
    public void iEnterWebsiteIntoSearchField() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I enter xpath \"([^\"]*)\" into \"([^\"]*)\"$")
    public void iEnterIntoSearchField(String text,String pageElement) {
        connector.typeByxpath(text,pageElement);

    }

    @And("^I click on \"([^\"]*)\"$")
    public void iClickOnSearchButton(String arg) {
        // Write code here that turns the phrase above into concrete actions
        connector.clickXpath(arg);
    }

}

