package com.indi.template_engine_tdd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {
	
	private Map<String, String> variables;
	
	private String templateText;

	public Template(String templateText) {
		this.templateText = templateText;
		this.variables = new HashMap<String, String>();
	}

	public void set(String key, String value) {
		this.variables.put(key, value);
	}

	public String evaluate() {
		TemplateParse parser = new TemplateParse();
		List<Segment> segments = parser.parseSegments(templateText);
		return concatenate(segments);
	}
	
	private String concatenate(List<Segment> segments) {
		StringBuilder result = new StringBuilder();
		for (Segment segment : segments) {
			result.append(segment.evaluate(variables));
		}
		return result.toString();
	}

}
