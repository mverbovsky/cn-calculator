package cz.verbovsky.calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test for simple calculator application.
 *
 * @author Martin Verbovsky
 */
public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    void main() {
        Double result = (((4d + 2d) * 3d) / 2d) + 12.6d - 8;
        runFileTest("complexTestFile.txt", result);
    }

    @Test
    void main_example1() {
        Double result = 18d;
        runFileTest("example1.txt", result);
    }

    @Test
    void main_example2() {
        Double result = 45d;
        runFileTest("example2.txt", result);
    }

    @Test
    void main_example3() {
        Double result = 1d;
        runFileTest("example3.txt", result);
    }

    @Test
    void main_NullParam_ExceptionThrown() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> App.main(null));
        assertEquals(exception.getMessage(), "Arguments must not be null.");
    }

    @Test
    void main_EmptyParams() {
        App.main(new String[]{});
        assertEquals("Enter a file name with instructions as parameter.\n", outContent.toString());
    }

    private void runFileTest(String fileName, Double expectedValue) {
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource(fileName).getFile();
        App.main(new String[]{file});
        assertEquals("Input: " + file + "\nResult: " + expectedValue.toString() + "\n",
                outContent.toString());
    }
}
