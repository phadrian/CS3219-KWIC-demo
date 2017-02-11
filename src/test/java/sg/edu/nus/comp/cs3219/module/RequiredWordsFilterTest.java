package sg.edu.nus.comp.cs3219.module;

import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs3219.model.LineStorage;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RequiredWordsFilterTest {
	LineStorage inputLineStorage;
	LineStorage afterFilterLineStorage;
	RequiredWordsFilter filter;

	@Before
	public void setUp() {
		// TODO
		inputLineStorage = new LineStorage();
		afterFilterLineStorage = new LineStorage();
		filter = new RequiredWordsFilter(afterFilterLineStorage);
		inputLineStorage.addObserver(filter);
	}

	@Test
	public void test() {
		Set<String> words = new HashSet<>();
		words.add("space");
		filter.setRequiredWords(words);

		inputLineStorage.addLine("Space Odyssey 2001: a");
		inputLineStorage.addLine("2001: a Space Odyssey");
		assertEquals(1, afterFilterLineStorage.size());

		assertEquals("Space Odyssey 2001: a", afterFilterLineStorage.get(0).toString());
	}

	@Test
	public void testNoRequiredWords() {
		filter.setRequiredWords(new HashSet<>());

		inputLineStorage.addLine("Space Odyssey 2001: a");
		inputLineStorage.addLine("2001: a Space Odyssey");
		assertEquals(2, afterFilterLineStorage.size());

		assertEquals("Space Odyssey 2001: a", afterFilterLineStorage.get(0).toString());
		assertEquals("2001: a Space Odyssey", afterFilterLineStorage.get(1).toString());


	}
}
