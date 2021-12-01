package aoc._2021._01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SonarSweep {

	public static void main(final String[] args) throws IOException {
		final List<Integer> depths = loadDepths(args[0]);
		final int windowSize = Integer.parseInt(args[1]);
		final int countOfIncreases = countSlidingScaleIncreases(depths, windowSize);
		Logger.getGlobal().setLevel(Level.INFO);
		Logger.getGlobal().log(Level.INFO, "Count of Increases: {0}", countOfIncreases);
	}

	public static List<Integer> loadDepths(final String filePath) throws IOException {
		final ArrayList<Integer> result = new ArrayList<>();

		try (InputStream inputStream = Files.newInputStream(Paths.get(filePath));
			 Scanner scanner = new Scanner(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			while (scanner.hasNext()) {
				result.add(scanner.nextInt());
			}
		}

		return result;
	}

	public static int countIncreases(final Iterable<Integer> depths) {
		int increases = 0;
		Integer previousDepth = null;
		for (final Integer currentDepth : depths) {
			if (previousDepth != null && currentDepth.compareTo(previousDepth) > 0) {
				increases++;
			}

			previousDepth = currentDepth;
		}

		return increases;
	}

	public static int countSlidingScaleIncreases(final List<Integer> depths, final int windowSize) {
		final HashMap<Integer, Integer> depthWindowSums = new HashMap<>();

		for (int i = 0; i <= depths.size() - windowSize; i++) {
			int windowSum = 0;
			for (int j = i; j < i + windowSize; j++) {
				windowSum += depths.get(j);
			}
			depthWindowSums.put(i, windowSum);
		}

		return countIncreases(depthWindowSums.values());
	}
}
