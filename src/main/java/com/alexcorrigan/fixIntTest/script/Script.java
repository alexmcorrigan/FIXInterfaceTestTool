package com.alexcorrigan.fixIntTest.script;

import com.alexcorrigan.fixIntTest.script.step.Step;

import java.util.Iterator;

public interface Script {

    public void addStep(Step submitStep);
    public String getID();
    public String getName();
    public String getDescription();
    public int getStepCount();
    public Iterator<Step> stepIterator();
    public void play();
    
}
