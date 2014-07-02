package com.alexcorrigan.fixIntTest.script;

import com.alexcorrigan.fixIntTest.script.step.ExpectStep;
import com.alexcorrigan.fixIntTest.script.step.StepType;
import com.alexcorrigan.fixIntTest.script.step.SubmitStep;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class JSONToScriptConverter {

    public Script convert(JSONObject jsonScript) throws Exception {
        JSONObject jsonScenario = (JSONObject) jsonScript.get("scenario");
        String id = extractString(jsonScenario, "id");
        String name = extractString(jsonScenario, "name");
        String description = extractString(jsonScenario, "description");
        Script script = new SimpleScript(id, name, description);

        JSONArray jsonSteps = (JSONArray) jsonScenario.get("steps");
        Iterator jsonStepsIterator = jsonSteps.iterator();
        while (jsonStepsIterator.hasNext()) {
            JSONObject jsonStep = (JSONObject) jsonStepsIterator.next();
            StepType stepType = StepType.lookUp(extractString(jsonStep, "type"));

            if (StepType.SUBMIT.equals(stepType)) {
                Map<String, String> fieldReplacements = new TreeMap<String, String>();

                JSONObject jsonFields = (JSONObject) jsonStep.get("fields");
                Set<String> fieldNames = jsonFields.keySet();
                for (String fieldName : fieldNames) {
                    fieldReplacements.put(fieldName, (String) jsonFields.get(fieldName));
                }

                script.addStep(new SubmitStep(
                        extractString(jsonStep, "description"),
                        extractString(jsonStep, "template"),
                        fieldReplacements));

            } else if (StepType.EXPECT.equals(stepType)) {
                Map<String,String> fieldExpectations = new TreeMap<String, String>();
                JSONObject jsonFields = (JSONObject) jsonStep.get("fields");
                Set<String> tags = jsonFields.keySet();
                for (String tag : tags) {
                    fieldExpectations.put(tag, (String) jsonFields.get(tag));
                }

                script.addStep(new ExpectStep(
                        extractString(jsonStep, "description"),
                        fieldExpectations
                ));
            };
        }

        return script;
    }

    private String extractString(JSONObject jsonObject, String field) {
        return (String) jsonObject.get(field);
    }

}
