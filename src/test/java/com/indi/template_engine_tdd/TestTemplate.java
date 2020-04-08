package com.indi.template_engine_tdd;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class TestTemplate {
	
	private Template template;

	@BeforeEach
	public void setUp() throws Exception {
		template = new Template("${one}, ${two}, ${three}");
		template.set("one", "1");
		template.set("two", "2");
		template.set("three", "3");
	}
	
	@Test
	public void oneVariable() throws Exception {
		Template temp = new Template("Hello, ${name}");
		temp.set("name", "Reader");
		Assertions.assertEquals("Hello, Reader", temp.evaluate());
	}
	
	@Test
	public void multipleVarible() throws Exception {
		assertTemplateEvaluatesTo("1, 2, 3");
	}
	
	@Test
	public void unknownVariblesGetsIgnored() throws Exception {
		template.set("four", "4");
		assertTemplateEvaluatesTo("1, 2, 3");
	}
	
	@Test
    public void settingValueMultipleTimes() {
        template.set("three", "4");
        assertTemplateEvaluatesTo("1, 2, 4");
    }
	
	@Test
	public void missingValueRaisesException() throws Exception {
		try {
			new Template("${foo}").evaluate();
			fail("evaluate() should throw an exception if a variable was left without a value!");
		} catch (MissingValueException expected) {
			Assertions.assertEquals("No value for ${foo}", expected.getMessage());
		}
	}
	
	@Test
	public void templateWith100WordsAnd20Variables() throws Exception {
		Template temp = new Template("${a}, ${b}, ${c}, ${d}, ${e}, ${f}, ${g}, ${h}, ${i}, ${j}, "
				+ "${a}, ${b}, ${c}, ${d}, ${e}, ${f}, ${g}, ${h}, ${i}, ${j}");
		
		String[] arr = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		for (String s: arr) {
			setSomeString(temp, s);	
		}
		
		long expected = 200L;
		long time = System.currentTimeMillis();
		temp.evaluate();
		
		time = System.currentTimeMillis() - time;
		Assertions.assertTrue(time <= expected, "Rendering template in " + time + "ms and expected time is less than equal to " + expected + "ms");
	}
	
	@Test
	public void variablesGetProcessedJustOnce() throws Exception {
	 template.set("one", "${one}");
	 template.set("two", "${three}");
	 template.set("three", "${two}");
	 assertTemplateEvaluatesTo("${one}, ${three}, ${two}");
	}
	
	private void setSomeString(Template t, String name) {
		t.set(name, "qwertyuiopasdfghjklzxcvbnm1234567890");
	}
	
	private void assertTemplateEvaluatesTo(String expected) {
		Assertions.assertEquals(expected, template.evaluate());
	}
	
}
