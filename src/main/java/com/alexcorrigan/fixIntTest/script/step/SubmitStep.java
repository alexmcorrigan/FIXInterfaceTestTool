package com.alexcorrigan.fixIntTest.script.step;

import com.alexcorrigan.fixIntTest.constants.Constants;

import java.util.Iterator;
import java.util.Map;

import static com.alexcorrigan.fixIntTest.constants.Constants.NEW_LINE;

public class SubmitStep implements Step {

    /*
        "steps": [
            {
                "type": "submit",
                "description": "Entering a simple",
                "template": "simpleNewOrder",
                "fields": [
                    {
                        "member": "AMT",
                        "quantity": 100,
                        "price": 56.2,
                        "symbol": "VOD.L"
                    }
                ]
            }
        ]
    */


    private final String description;
    private final String template;
    private final Map<String, String> fields;

    public SubmitStep(
            String description,
            String template,
            Map<String, String> fields) {
        this.description = description;
        this.template = template;
        this.fields = fields;
    }

    /*
    - Submit Message: simpleNewOrder
    - Entering a simple new order.
        - member = AMT
        - quantity = 100
        - price = 56.2
        - symbol = VOD.L
    */

    @Override
    public String toString() {
        StringBuilder stepStringBuilder = new StringBuilder();
        stepStringBuilder.append(String.format("- Submit Message: %s", template));
        stepStringBuilder.append(NEW_LINE);
        stepStringBuilder.append(String.format("  - Description: %s", description));
        stepStringBuilder.append(NEW_LINE);
        Iterator<String> fieldNameIterator = fields.keySet().iterator();
        while (fieldNameIterator.hasNext()) {
            String fieldName = fieldNameIterator.next();
            stepStringBuilder.append(String.format("    - %s = %s", fieldName, fields.get(fieldName)));
            if (fieldNameIterator.hasNext()) stepStringBuilder.append(NEW_LINE);
        }
        return stepStringBuilder.toString();
    }

}
