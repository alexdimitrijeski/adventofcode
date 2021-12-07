package aoc._2021._01;

import aoc.utils.FileUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class SonarSweep {

	private static final Logger LOGGER = Logger.getLogger(SonarSweep.class.getName());

	public static void main(final String[] args) throws IOException {
		final List<Integer> depths = loadDepths(args[0]);
		final int windowSize = Integer.parseInt(args[1]);
		final int countOfIncreases = countSlidingScaleIncreases(depths, windowSize);
		LOGGER.setLevel(Level.INFO);
		LOGGER.log(Level.INFO, "Count of Increases: {0}", countOfIncreases);
	}

	public static List<Integer> loadDepths(final String filePath) throws IOException {
		final List<String> fileContents = FileUtil.loadFile(filePath);
		return FileUtil.convertList(fileContents, Integer::parseInt);
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
