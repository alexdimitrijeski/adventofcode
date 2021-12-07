package aoc._2021._01;

import aoc.utils.FileUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SonarSweepTest {

	private static final String FILE_PATH = "src/test/resources/2021/01/SonarSweep/example.txt";

	private static final Logger LOGGER = Logger.getLogger(SonarSweep.class.getName());
	private transient ByteArrayOutputStream logCapturingStream;
	private transient StreamHandler customLogHandler;

	@BeforeEach
	void setUp() {
		attachLogCapture();
	}

	private void attachLogCapture() {
		logCapturingStream = new ByteArrayOutputStream();
		Handler[] handlers = LOGGER.getParent().getHandlers();
		customLogHandler = new StreamHandler(logCapturingStream, handlers[0].getFormatter());
		LOGGER.addHandler(customLogHandler);
	}

	private String getTestCapturedLog() throws UnsupportedEncodingException {
		customLogHandler.flush();
		return logCapturingStream.toString(String.valueOf(StandardCharsets.UTF_8));
	}

	@Test
	void testMain() throws IOException {
		final String expectedLogPart = "Count of Increases: 7";
		String[] args = {FILE_PATH, "1"};
		SonarSweep.main(args);

		String capturedLog = getTestCapturedLog();
		assertTrue(capturedLog.contains(expectedLogPart), "Expected log message was not captured");
	}

	@Test
	void givenExampleFileWhenLoadedThenExpectResult() throws IOException {
		final List<String> fileContents = FileUtil.loadFile(FILE_PATH);
		final List<Integer> expectedResults = FileUtil.convertList(fileContents, Integer::parseInt);

		final ArrayList<Integer> results = (ArrayList<Integer>) SonarSweep.loadDepths(FILE_PATH);

		assertEquals(expectedResults, results, "expectedResults and results differ.");
	}

	@Test
	void givenArrayOfDepthsWhenCountingIncreasesThenExpectSeven() throws IOException {

		final ArrayList<Integer> depths = (ArrayList<Integer>) SonarSweep.loadDepths(FILE_PATH);

		assertEquals(7, SonarSweep.countIncreases(depths), "Number of increases is not 7.");
	}

	@Test
	void givenArrayOfDepthsWithWindowSizeWhenCountIncreasesThenExpectFive() throws IOException {

		final ArrayList<Integer> depths = (ArrayList<Integer>) SonarSweep.loadDepths(FILE_PATH);
		final int windowSize = 3;
		final int expectedIncreases = 5;

		assertEquals(expectedIncreases, SonarSweep.countSlidingScaleIncreases(depths, windowSize), "Number of increases is not 5.");
	}
}
