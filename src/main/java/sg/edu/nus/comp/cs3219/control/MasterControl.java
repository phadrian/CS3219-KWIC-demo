package sg.edu.nus.comp.cs3219.control;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sg.edu.nus.comp.cs3219.model.Line;
import sg.edu.nus.comp.cs3219.model.LineStorage;
import sg.edu.nus.comp.cs3219.module.Alphabetizer;
import sg.edu.nus.comp.cs3219.module.CircularShifter;
import sg.edu.nus.comp.cs3219.module.RequiredWordsFilter;

public class MasterControl {
	final private Alphabetizer alphabetizer;
	final private RequiredWordsFilter filter;   // added filter
	final private CircularShifter shifter;

	private LineStorage rawInputLines;
	private LineStorage shiftedLines;
	private LineStorage resultLines;

	public MasterControl() {
		// Storage
		rawInputLines = new LineStorage();
		shiftedLines = new LineStorage();   // added intermediate storage for shifted lines
		resultLines = new LineStorage();

		// Sub-modules
		shifter = new CircularShifter(shiftedLines);    // shifter outputs to a new storage
		filter = new RequiredWordsFilter(resultLines);  // create filter sub module
		alphabetizer = new Alphabetizer();

		// Set up observation
		rawInputLines.addObserver(shifter);
		shiftedLines.addObserver(filter);   // insert observer for shifted lines
		resultLines.addObserver(alphabetizer);
	}

    public List<String> run(List<String> input, Set<String> ignoredWords, Set<String> requiredWords) {
        rawInputLines.clearLines();
        resultLines.clearLines();

        // Set ignore words (make them lowercase for comparison)
        shifter.setIgnoreWords(this.transformSetToLowercase(ignoredWords));

        // Set required words (make them lowercase for comparison)
        filter.setRequiredWords(this.transformSetToLowercase(requiredWords));

        // Add data line by line
        for (String line : input) {
            rawInputLines.addLine(line);
        }

        return resultLines.getAll();
    }
	
	private Set<String> transformSetToLowercase(Set<String> set) {
		return set.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
	}
}
