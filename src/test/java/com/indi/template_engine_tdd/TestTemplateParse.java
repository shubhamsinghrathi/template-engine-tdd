package com.indi.template_engine_tdd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestTemplateParse {

	@Test
	void emptyTemplateRendersAsEmptyString() throws Exception {
		List<String> segments = parse("");
		assertSegments(segments, "");
	}
	
	@Test
	public void templateWithOnlyPlainText() throws Exception {
		List<String> segments = parse("plain text only");
		assertSegments(segments, "plain text only");
	}
	
	@Test
	public void parsingMultipleVariables() throws Exception {
		List<String> segments = parse("${a}:${b}:${c}");
		assertSegments(segments, "${a}", ":", "${b}", ":", "${c}");
	}
	
	 @Test
	 public void parsingTemplateIntoSegmentObjects() throws Exception {
		 TemplateParse p = new TemplateParse();
		 List<Segment> segments = p.parseSegments("a ${b} c ${d}");
		 assertSegments(
				 segments,
				 new PlainText("a "), new Variable("b"),
				 new PlainText(" c "), new Variable("d")
		 );
	 }
	
	private List<String> parse(String template) {
		return new TemplateParse().parse(template);
	}
	
	private void assertSegments(List<? extends Object> segments, Object... expected) {
		assertEquals(expected.length, segments.size(), "Number of segments doesn't match.");
		assertEquals(Arrays.asList(expected), segments);
	}

}
