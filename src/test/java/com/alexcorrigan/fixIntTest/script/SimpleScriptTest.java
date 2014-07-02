package com.alexcorrigan.fixIntTest.script;

import com.alexcorrigan.fixIntTest.script.step.Step;
import com.alexcorrigan.fixIntTest.script.step.SubmitStep;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.alexcorrigan.fixIntTest.constants.ScriptDummyConstants.*;
import static com.alexcorrigan.fixIntTest.constants.SubmitStepDummyConstants.*;
import static org.junit.Assert.assertEquals;

public class SimpleScriptTest {

    @Test
    public void testNewSimpleScript() throws Exception {
        Script script = new SimpleScript(SCRIPT_ID, SCRIPT_NAME, SCRIPT_DESCRIPTION);
        Map<String, String> messageFields = new HashMap<String, String>();
        messageFields.put("member", "AMT");
        messageFields.put("quantity", "100");
        messageFields.put("price", "56.2");
        messageFields.put("symbol", "VOD.L");
        Step submitStep = new SubmitStep(SUBMIT_STEP_DESCRIPTION, SUBMIT_STEP_TEMPLATE, messageFields);
        script.addStep(submitStep);
        assertEquals(SCRIPT_ID, script.getID());
        assertEquals(SCRIPT_NAME, script.getName());
        assertEquals(SCRIPT_DESCRIPTION, script.getDescription());
        assertEquals(1, script.getStepCount());
    }
}
