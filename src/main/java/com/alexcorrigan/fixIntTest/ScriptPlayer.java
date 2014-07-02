package com.alexcorrigan.fixIntTest;

import com.alexcorrigan.fixIntTest.script.Script;

public class ScriptPlayer implements OutputLogger {

    private OutputLogListener outputLogListener;

    public ScriptPlayer(OutputLogListener outputLogListener) {
        this.outputLogListener = outputLogListener;
    }

    public void play(Script script) {
        out(script.toString());
        script.play();
    }

    @Override
    public void out(String s) {
        outputLogListener.onOutput(s);
    }
}
