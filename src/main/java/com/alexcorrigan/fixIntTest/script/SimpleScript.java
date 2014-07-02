package com.alexcorrigan.fixIntTest.script;

import com.alexcorrigan.fixIntTest.constants.Constants;
import com.alexcorrigan.fixIntTest.script.step.Step;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.alexcorrigan.fixIntTest.constants.Constants.DOUBLE_NEW_LINE;
import static com.alexcorrigan.fixIntTest.constants.Constants.NEW_LINE;

public class SimpleScript implements Script {

    private final String id;
    private final String name;
    private final String description;
    private final List<Step> steps = new ArrayList<Step>();

    public SimpleScript(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public void addStep(Step submitStep) {
        steps.add(submitStep);
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getStepCount() {
        return steps.size();
    }

    @Override
    public Iterator<Step> stepIterator() {
        return steps.iterator();
    }

    @Override
    public void play() {

    }

    /* Scenario: scenario_101 - bla
    Description: bla bla bla

    - Submit Message: simpleNewOrder
    - Entering a simple new order.
    - member = AMT
    - quantity = 100
            - price = 56.2
            - symbol = VOD.L */

    @Override
    public String toString() {
        StringBuilder scriptStringBuilder = new StringBuilder();
        scriptStringBuilder.append(String.format("Scenario: %s - %s", id, name));
        scriptStringBuilder.append(NEW_LINE);
        scriptStringBuilder.append(String.format("Description: %s", description));
        scriptStringBuilder.append(DOUBLE_NEW_LINE);
        Iterator<Step> stepIterator = steps.iterator();
        while (stepIterator.hasNext()) {
            Step step = stepIterator.next();
            scriptStringBuilder.append(step.toString());
            if (stepIterator.hasNext()) scriptStringBuilder.append(DOUBLE_NEW_LINE);
        }
        return scriptStringBuilder.toString();
    }

}
