package com.indi.template_engine_tdd;

import java.util.Map;

public interface Segment {
	
	String evaluate(Map<String, String> variables);
	
}
