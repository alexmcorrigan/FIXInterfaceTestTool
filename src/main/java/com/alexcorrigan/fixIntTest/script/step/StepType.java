package com.alexcorrigan.fixIntTest.script.step;

public enum StepType {

    SUBMIT,
    EXPECT;

    public static StepType lookUp(String rawStepType) throws Exception {
        for (StepType stepType : StepType.values()) {
            if (stepType.toString().equals(rawStepType.toUpperCase())) return stepType;
        }
        throw new Exception("\"" + rawStepType + "\" is not a valid StepType.");
    }
}
