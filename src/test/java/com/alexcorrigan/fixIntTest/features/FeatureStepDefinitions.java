package com.alexcorrigan.fixIntTest.features;

import com.alexcorrigan.fixIntTest.FITTApp;
import com.alexcorrigan.fixIntTest.OutputLogListener;
import com.alexcorrigan.fixIntTest.Session.FIXSessionProperties;
import com.alexcorrigan.fixIntTest.mocks.MockHost;
import com.alexcorrigan.fixIntTest.mocks.MockHostListener;
import com.alexcorrigan.fixIntTest.script.JSONToScriptConverter;
import com.alexcorrigan.fixIntTest.script.Script;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FeatureStepDefinitions implements OutputLogListener, MockHostListener {

    private static final String RUNTIME_DIR = "./runtime/";
    private static final String SCRIPT_FILENAME = "testScript.json";
    private static final String MESSAGE_TEMPLATE_FILENAME = "simpleNewOrder.msg";
    private static final String TEST_HOST = "localhost";
    private static final int TEST_PORT = 9999;
    private static final FIXSessionProperties FIX_SESSION_PROPERTIES = new FIXSessionProperties(TEST_HOST, TEST_PORT);

    private List<String> outputsCaught = new ArrayList<String>();
    private List<String> mockHostReceivedMessages = new ArrayList<String>();
    private MockHost mockHost;


    @Given("^a script$")
    public void a_script(String jsonScript) throws Throwable {
        FileWriter scriptFileWriter = new FileWriter(new File(RUNTIME_DIR + SCRIPT_FILENAME));
        scriptFileWriter.write(jsonScript);
        scriptFileWriter.close();
    }

    @When("^FITT loads the script$")
    public void FITT_loads_the_script() throws Throwable {
        FITTApp fittApp = new FITTApp(this, FIX_SESSION_PROPERTIES);
        fittApp.playScript(RUNTIME_DIR + SCRIPT_FILENAME);
    }

    @Then("^FITT outputs$")
    public void FITT_outputs(String expectedOutput) throws Throwable {
        assertEquals(1, outputsCaught.size());
        assertEquals(expectedOutput, outputsCaught.get(0));
    }

    @Given("^a message template$")
    public void a_message_template_called(String message) throws Throwable {
        FileWriter messageTemplateFileWriter = new FileWriter(new File(RUNTIME_DIR + MESSAGE_TEMPLATE_FILENAME));
        messageTemplateFileWriter.write(message);
        messageTemplateFileWriter.close();
    }

    @When("^FITT plays the script$")
    public void FITT_connects_to_a_host() throws Throwable {
        FITTApp fittApp = new FITTApp(this, FIX_SESSION_PROPERTIES);
        new Thread(new MockHostListenerThread()).run();
        fittApp.playScript(RUNTIME_DIR + SCRIPT_FILENAME);
    }

    @Then("^the following message is sent$")
    public void the_following_message_is_sent(String expectedSentMessage) throws Throwable {
        assertEquals(1, mockHostReceivedMessages.size());
        assertEquals(expectedSentMessage, mockHostReceivedMessages.get(0));
    }

    @After
    private void tearDown() {
        outputsCaught.clear();
    }

    @Override
    public void onReceivedMessage(String message) {
        mockHostReceivedMessages.add(message);
    }

    @Override
    public void onOutput(String s) {
        outputsCaught.add(s);
    }

    private class MockHostListenerThread implements MockHostListener, Runnable {

        private MockHostListener mockHostListener;

        @Override
        public void run() {
            mockHost = new MockHost(TEST_PORT);
            mockHost.listen(mockHostListener);
        }

        @Override
        public void onReceivedMessage(String message) {
            mockHostReceivedMessages.add(message);
        }
    }

}
