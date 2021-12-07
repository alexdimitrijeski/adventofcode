package aoc._2021._02;

import aoc.utils.FileUtil;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dive {

	private static final Logger LOGGER = Logger.getLogger(Dive.class.getName());

	public static void main(final String[] args) throws IOException {
		final List<String> directions = loadDirections(args[0]);
		final boolean useAim = Boolean.parseBoolean(args[1]);
		final List<Pair<String, Integer>> readDirections = readDirections(directions);
		final int product = followDirections(readDirections, useAim);

		LOGGER.setLevel(Level.INFO);
		LOGGER.log(Level.INFO, "Product of height and depth: {0}", product);
	}

	public static List<String> loadDirections(final String filePath) throws IOException {
		return FileUtil.loadFile(filePath);
	}

	public static List<Pair<String, Integer>> readDirections(final Iterable<String> directions) {
		ArrayList<Pair<String, Integer>> directionsParsed = new ArrayList<>();

		for (String direction : directions) {
			String[] directionStringPair = direction.split("\\s+");
			directionsParsed.add(new Pair<>(directionStringPair[0], Integer.parseInt(directionStringPair[1])));
		}

		return directionsParsed;
	}

	public static int followDirections(final Iterable<Pair<String, Integer>> directions, boolean useAim) {
		int horizontal = 0;
		int depth = 0;
		int aim = 0;

		for (Pair<String, Integer> direction : directions) {
			switch (direction.getKey()) {
				case "forward":
					if (useAim) {
						depth += (aim * direction.getValue());
					}
					horizontal += direction.getValue();
					break;
				case "up":
					if (useAim) {
						aim -= direction.getValue();
					} else {
						depth -= direction.getValue();
					}
					break;
				case "down":
					if (useAim) {
						aim += direction.getValue();
					} else {
						depth += direction.getValue();
					}
					break;
				default:
					break;
			}
		}
		return horizontal * depth;
	}
}
