package com.alexcorrigan.fixIntTest.script;

import com.alexcorrigan.fixIntTest.script.step.StepType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StepTypeTest {

    @Test
    public void testLowerCaseLookUp() throws Exception {
        assertLookUp("submit");
    }

    @Test
    public void testUpperCaseLookUp() throws Exception {
        assertLookUp("SUBMIT");
    }

    @Test
    public void testMixedCaseLookUp() throws Exception {
        assertLookUp("Submit");
    }

    @Test
    public void testFailedLookUp() throws Exception {
        Exception caughtException = null;
        try {
            assertLookUp("aaa");
        } catch (Exception e) {
            caughtException = e;
        }
        assertNotNull(caughtException);
        assertEquals("\"aaa\" is not a valid StepType.", caughtException.getMessage());
    }

    private void assertLookUp(String input) throws Exception {
        assertEquals(StepType.SUBMIT, StepType.lookUp(input));
    }

}
