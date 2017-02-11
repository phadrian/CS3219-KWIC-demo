package sg.edu.nus.comp.cs3219.control;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class MasterControlTest {

	MasterControl master;
	
	@Before
	public void setUp() {
		master = new MasterControl();
	}

	@Test
	public void testExample1() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");
		
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("");

		List<String> input = new ArrayList<>();
		input.add("The Day after Tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");

		List<String> result = master.run(input, ignoreWords, requiredWords);

		assertEquals(6, result.size());
		assertEquals("Day after Tomorrow the", result.get(0));
		assertEquals("Fast and Furious", result.get(1));
		assertEquals("Furious Fast and", result.get(2));
		assertEquals("Man of Steel", result.get(3));
		assertEquals("Steel Man of", result.get(4));
		assertEquals("Tomorrow the Day after", result.get(5));
	}
	
	@Test
	public void testExample2() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");

		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("");
		
		List<String> input = new ArrayList<>();
		input.add("The day after tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");

		List<String> result = master.run(input, ignoreWords, requiredWords);

		assertEquals(6, result.size());
		assertEquals("Day after tomorrow the", result.get(0));
		assertEquals("Fast and Furious", result.get(1));
		assertEquals("Furious Fast and", result.get(2));
		assertEquals("Man of Steel", result.get(3));
		assertEquals("Steel Man of", result.get(4));
		assertEquals("Tomorrow the day after", result.get(5));
	}
	
	@Test
	public void testExample3() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");
		
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("space");
		requiredWords.add("star");
		
		List<String> input = new ArrayList<>();
		input.add("the day after tomorrow");
		input.add("fast and furious");
		input.add("man of steel");
		input.add("star trek");
		input.add("2001: a space odyssey");
		
		List<String> result = master.run(input, ignoreWords, requiredWords);
		
		assertEquals(2, result.size());
		assertEquals("Space odyssey 2001: a", result.get(0));
		assertEquals("Star trek", result.get(1));
	}
	
	@Test
	public void testExample4() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");
		
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("2017");
		requiredWords.add("movie");
		
		List<String> input = new ArrayList<>();
		input.add("the avengers");
		input.add("need for speed 2");
		input.add("spongebob squarepants the movie");
		input.add("avatar");
		input.add("adc in 2017");
		
		List<String> result = master.run(input, ignoreWords, requiredWords);
		
		assertEquals(2, result.size());
		assertEquals("2017 adc in", result.get(0));
		assertEquals("Movie spongebob squarepants the", result.get(1));
	}
	
	@Test
	public void testExample5() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");
		
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("2017");
		requiredWords.add("movie");
		
		List<String> input = new ArrayList<>();
		input.add("the avengers");
		input.add("need for speed 2");
		input.add("spongebob squarepants the movie");
		input.add("avatar");
		input.add("adc in 2017");
		input.add("travelling salesman the movie");
		input.add("social network");
		input.add("2017/01/01");
		input.add("moana");
		input.add("the dark knight");
		
		List<String> result = master.run(input, ignoreWords, requiredWords);
		
		assertEquals(3, result.size());
		assertEquals("2017 adc in", result.get(0));
		assertEquals("Movie spongebob squarepants the", result.get(1));
		assertEquals("Movie travelling salesman the", result.get(2));
	}
	
	@Test
	// Assume that ignore takes priority over required
	public void testExample6() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");
		
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("2017");
		requiredWords.add("movie");
		requiredWords.add("the");
		
		List<String> input = new ArrayList<>();
		input.add("the avengers");
		input.add("need for speed 2");
		input.add("spongebob squarepants the movie");
		input.add("avatar");
		input.add("adc in 2017");
		input.add("travelling salesman the movie");
		input.add("social network");
		input.add("2017/01/01");
		input.add("moana");
		input.add("the dark knight");
		
		List<String> result = master.run(input, ignoreWords, requiredWords);
		
		assertEquals(3, result.size());
		assertEquals("2017 adc in", result.get(0));
		assertEquals("Movie spongebob squarepants the", result.get(1));
		assertEquals("Movie travelling salesman the", result.get(2));
	}
}

