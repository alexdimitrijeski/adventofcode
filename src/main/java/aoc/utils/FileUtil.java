package aoc.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class FileUtil {

	private FileUtil(){
		//Hiding constructor as it is not used.
	}

	public static List<String> loadFile(String filePath) throws IOException {
		ArrayList<String> fileContents = new ArrayList<>();
		try (Scanner scanner = new Scanner(Files.newBufferedReader(Paths.get(filePath)))) {
			while (scanner.hasNextLine()) {
				fileContents.add(scanner.nextLine());
			}
		}
		return fileContents;
	}

	public static <T, U> List<U> convertList(Collection<T> from, Function<T, U> func) {
		return from.stream().map(func).collect(Collectors.toList());
	}

}
