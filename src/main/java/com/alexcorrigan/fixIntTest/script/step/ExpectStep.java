package com.alexcorrigan.fixIntTest.script.step;

import com.alexcorrigan.fixIntTest.constants.Constants;

import java.util.Iterator;
import java.util.Map;

import static com.alexcorrigan.fixIntTest.constants.Constants.NEW_LINE;

public class ExpectStep implements Step {

    /*
    {
      "type": "expect"
        "description": "Message received in response.",
        "fields": {
          "35": "8"
        }
    }
    */

    private String description;
    private Map<String, String> fieldExpectations;

    public ExpectStep(String description, Map<String, String> fieldExpectations) {
        this.description = description;
        this.fieldExpectations = fieldExpectations;
    }

    @Override
    public String toString() {
        StringBuilder stepStringBuilder = new StringBuilder();
        stepStringBuilder.append("- Expect Message:");
        stepStringBuilder.append(NEW_LINE);
        stepStringBuilder.append(String.format("  - Description: %s", description));
        stepStringBuilder.append(NEW_LINE);
        Iterator<String> tagIterator = fieldExpectations.keySet().iterator();
        while (tagIterator.hasNext()) {
            String tag = tagIterator.next();
            stepStringBuilder.append(String.format("    - %s = %s", tag, fieldExpectations.get(tag)));
            if (tagIterator.hasNext()) stepStringBuilder.append(NEW_LINE);
        }
        return stepStringBuilder.toString();
    }

}
