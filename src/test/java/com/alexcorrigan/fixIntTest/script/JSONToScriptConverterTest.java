package com.alexcorrigan.fixIntTest.script;

import com.alexcorrigan.fixIntTest.script.step.Step;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class JSONToScriptConverterTest {


    private String jsonScript;

    @Before
    public void setUp() throws Exception {
        StringBuilder jsonScriptBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/testScripts/testScript.json")));
        String line;
        while ((line = br.readLine()) != null) {
            jsonScriptBuilder.append(line);
        }
        jsonScript = jsonScriptBuilder.toString();
    }

    @Test
    public void testConvertScript() throws Exception {
        JSONToScriptConverter jsonToScriptConverter = new JSONToScriptConverter();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonScript);
        Script script = jsonToScriptConverter.convert(jsonObject);
        assertEquals("scenario_101", script.getID());
        assertEquals("bla", script.getName());
        assertEquals("bla bla bla", script.getDescription());
        assertEquals(1, script.getStepCount());
    }
}
