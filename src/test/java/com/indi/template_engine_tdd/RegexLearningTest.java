package com.indi.template_engine_tdd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegexLearningTest {
	
	String haystack = "The needle shop sells needles";
	String regex = "needle";
	Matcher matcher;
	
	@BeforeEach
	void init() {
		matcher = Pattern.compile(regex).matcher(haystack);
	}

	@Test
	void testHowGroupCountWorks() throws Exception {
		assertTrue(matcher.find());
	}
	
	@Test
	void testFindStartAndEnd() throws Exception {
		assertTrue(matcher.find());
		assertEquals(4, matcher.start(), "Wrong start index of 1st match");
		assertEquals(10, matcher.end(), "Wrong end index of 1st match");
		
		assertTrue(matcher.find());
		assertEquals(22, matcher.start(), "Wrong start index of 2nd match.");
		assertEquals(28, matcher.end(), "Wrong end index of 2nd match.");
		
		assertFalse(matcher.find(), "Should not have any more matches");
	}

}
