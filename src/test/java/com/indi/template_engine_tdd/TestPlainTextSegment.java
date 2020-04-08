package com.indi.template_engine_tdd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class TestPlainTextSegment {

	 @Test
	 public void plainTextEvaluatesAsIs() throws Exception {
		 Map<String, String> variables = new HashMap<String, String>();
		 String text = "abc def";
		 assertEquals(text, new PlainText(text).evaluate(variables));
	 }

}
