package com.alexcorrigan.fixIntTest;

import com.alexcorrigan.fixIntTest.Session.FIXSessionProperties;
import com.alexcorrigan.fixIntTest.script.JSONToScriptConverter;
import com.alexcorrigan.fixIntTest.script.Script;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class FITTApp implements OutputLogListener, OutputLogger {

    private final OutputLogListener outputLogListener;

    public FITTApp(
            OutputLogListener outputLogListener,
            FIXSessionProperties fixSessionProperties) throws Exception {
        this.outputLogListener = outputLogListener;
    }

    public void playScript(String pathToScript) throws Exception {
        new ScriptPlayer(this).play(convertFromJSONScript(pathToScript));
    }

    private Script convertFromJSONScript(String pathToScript) throws Exception {
        JSONObject jsonScript = (JSONObject) new JSONParser().parse(readScriptFromFile(pathToScript));
        JSONToScriptConverter jsonToScriptConverter = new JSONToScriptConverter();
        return jsonToScriptConverter.convert(jsonScript);
    }

    private String readScriptFromFile(String pathToScript) throws IOException {
        File jsonScriptFile = new File(pathToScript);
        InputStreamReader jsonFileInputStreamReader = new InputStreamReader(new FileInputStream(jsonScriptFile));
        BufferedReader jsonScriptFileReader = new BufferedReader(jsonFileInputStreamReader);
        StringBuilder jsonScriptBuilder = new StringBuilder();
        String line;
        while ((line = jsonScriptFileReader.readLine()) != null) {
            jsonScriptBuilder.append(line);
        }
        return jsonScriptBuilder.toString();
    }

    @Override
    public void onOutput(String s) {
        outputLogListener.onOutput(s);
    }

    @Override
    public void out(String s) {
        onOutput(s);
    }
}
