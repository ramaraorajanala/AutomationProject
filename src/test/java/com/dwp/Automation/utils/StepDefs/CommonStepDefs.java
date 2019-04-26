package com.dwp.Automation.utils.StepDefs;

import com.dwp.Automation.utils.AbstractWebconn;
import com.dwp.Automation.utils.JobCenterWebConn;
import com.sun.xml.internal.ws.policy.AssertionSet;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class CommonStepDefs {



    AbstractWebconn connector = JobCenterWebConn.getInstance();

    @Before("@jobCenter")
    public void SmitWebConnector()
    {
        connector = JobCenterWebConn.getInstance();

    }

    @Given("^I navigate to \"([^\"]*)\"$")
    public void goTogoogle(String arg) {
        connector.navigate(arg);
    }


    @And("^I enter Yahoo into search field$")
    public void iEnterWebsiteIntoSearchField() {
        throw new PendingException();
    }

    @Given("^I enter xpath \"([^\"]*)\" into \"([^\"]*)\"$")
    public void iEnterIntoSearchField(String text,String pageElement) {
        connector.typeByxpath(text, pageElement);

    }

    @And("^I click on \"([^\"]*)\"$")
    public void iClickOnSearchButton(String arg) {
        connector.clickXpath(arg);
    }

    @Then("^the user has landed on \"([^\"]*)\"$")
    public void theUserHasLandedOn(String arg0){
       Assert.assertTrue(connector.explicitWaitObject(arg0));


    }
}

