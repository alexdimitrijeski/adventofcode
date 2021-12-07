package aoc._2021._02;

import aoc.utils.FileUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiveTest {
	private static final String FILE_PATH = "src/test/resources/2021/02/Dive/example.txt";

	private static final Logger LOGGER = Logger.getLogger(Dive.class.getName());
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
	void testMainPart1() throws IOException {
		final String expectedLogPart = "Product of height and depth: 150";
		String[] args = {FILE_PATH, "FALSE"};
		Dive.main(args);

		String capturedLog = getTestCapturedLog();

		assertTrue(capturedLog.contains(expectedLogPart), "Expected log message was not captured");
	}

	@Test
	void testMainPart2() throws IOException {
		final String expectedLogPart = "Product of height and depth: 900";
		String[] args = {FILE_PATH, "TRUE"};
		Dive.main(args);

		String capturedLog = getTestCapturedLog();

		assertTrue(capturedLog.contains(expectedLogPart), "Expected log message was not captured");
	}

	@Test
	void givenExampleFileWhenLoadedThenExpectResult() throws IOException {
		final List<String> expectedResults = FileUtil.loadFile(FILE_PATH);

		final List<String> results = Dive.loadDirections(FILE_PATH);

		assertEquals(expectedResults, results, "expectedResults and results differ.");
	}
}
