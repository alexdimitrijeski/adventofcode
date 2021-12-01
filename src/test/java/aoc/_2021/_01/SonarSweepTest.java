package aoc._2021._01;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SonarSweepTest {

	@Test
	void givenExampleFileWhenLoadedThenExpectResult() throws IOException {
		final String filePath = "src/test/resources/2021/01/SonarSweep/example.txt";
		final ArrayList<Integer> expectedResults = new ArrayList<>();

		try (Scanner scanner = new Scanner(Files.newBufferedReader(Paths.get(filePath)))) {
			while (scanner.hasNext()) {
				expectedResults.add(scanner.nextInt());
			}
		}

		final ArrayList<Integer> results = (ArrayList<Integer>) SonarSweep.loadDepths(filePath);

		assertEquals(expectedResults, results, "expectedResults and results differ.");
	}

	@Test
	void givenArrayOfDepthsWhenCountingIncreasesThenExpectSeven() throws IOException {

		final String filePath = "src/test/resources/2021/01/SonarSweep/example.txt";
		final ArrayList<Integer> depths = (ArrayList<Integer>) SonarSweep.loadDepths(filePath);

		assertEquals(7, SonarSweep.countIncreases(depths), "Number of increases is not 7.");
	}

	@Test
	void givenArrayOfDepthsWithWindowSizeWhenCountIncreasesThenExpectFive() throws IOException {

		final String filePath = "src/test/resources/2021/01/SonarSweep/example.txt";
		final ArrayList<Integer> depths = (ArrayList<Integer>) SonarSweep.loadDepths(filePath);
		final int windowSize = 3;
		final int expectedIncreases = 5;

		assertEquals(expectedIncreases, SonarSweep.countSlidingScaleIncreases(depths,windowSize), "Number of increases is not 5.");
	}
}
