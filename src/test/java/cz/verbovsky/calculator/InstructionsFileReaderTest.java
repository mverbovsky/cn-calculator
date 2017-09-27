package cz.verbovsky.calculator;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of the tests for {@link InstructionsFileReader}
 *
 * @author Martin Verbovsky
 */
class InstructionsFileReaderTest {

    @Mock private InstructionParser<Double> parser;
    private ClassLoader classLoader;

    @BeforeEach
    void initialize() {
        MockitoAnnotations.initMocks(this);
        classLoader = getClass().getClassLoader();
        when(parser.parse("apply 4")).thenReturn(
                new Instruction<>(DoubleOperationTypes.APPLY, Double.valueOf("4")));
        when(parser.parse("add 2")).thenReturn(
                new Instruction<>(DoubleOperationTypes.ADD, Double.valueOf("2")));
        when(parser.parse("multiply 3")).thenReturn(
                new Instruction<>(DoubleOperationTypes.MULTIPLY, Double.valueOf("3")));
    }

    @AfterEach
    void clear() {
        classLoader = null;
        reset(parser);
    }

    @Test
    void constructor_NullParam_ExceptionThrown() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> new InstructionsFileReader<Double>(null));
        assertEquals("Parser argument must not be null.", exception.getMessage());
    }

    @Test
    void read_NullParam_ExceptionThrown() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            InstructionsFileReader<Double> instructionsFileReader =
                    new InstructionsFileReader<>(parser);
            instructionsFileReader.read(null);
        });
        assertEquals("FileName argument must not be null.", exception.getMessage());
    }

    @Test
    void read_EmptyParam_ExceptionThrown() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            InstructionsFileReader<Double> instructionsFileReader =
                    new InstructionsFileReader<>(parser);
            instructionsFileReader.read("");
        });
        assertEquals("FileName argument must not be empty.", exception.getMessage());
    }

    @Test
    void read_NonExistingFile_ExceptionThrown() {
        Throwable exception = assertThrows(FileNotFoundException.class, () -> {
            InstructionsFileReader<Double> instructionsFileReader =
                    new InstructionsFileReader<>(parser);
            instructionsFileReader.read("file.txt");
        });
        assertEquals("File 'file.txt' does not exist", exception.getMessage());
    }

    @Test
    void read_EmptyFile_ExceptionThrown() {
        Throwable exception = assertThrows(FileFormatException.class, () -> {
            InstructionsFileReader<Double> instructionsFileReader =
                    new InstructionsFileReader<>(parser);
            String file = classLoader.getResource("emptyFile.txt").getFile();
            instructionsFileReader.read(file);
        });
        assertEquals("Initialization operation must be defined.", exception.getMessage());
    }

    @Test
    void read_WithoutInitOperationFile_ExceptionThrown() {
        Throwable exception = assertThrows(FileFormatException.class, () -> {
            InstructionsFileReader<Double> instructionsFileReader =
                    new InstructionsFileReader<>(parser);
            String file = classLoader.getResource("withoutInitOperationFile.txt").getFile();
            instructionsFileReader.read(file);
        });
        assertEquals("Initialization operation must be defined.", exception.getMessage());
    }

    @Test
    void read_UnknownInstructionFile_ExceptionThrown() {
        Throwable exception = assertThrows(FileFormatException.class, () -> {
            InstructionsFileReader<Double> instructionsFileReader =
                    new InstructionsFileReader<>(parser);
            String file = classLoader.getResource("unknownInstructionFile.txt").getFile();
            instructionsFileReader.read(file);
        });
        assertEquals("Instruction must not be null.", exception.getMessage());
    }

    @Test
    void read() throws IOException {
        InstructionsFileReader<Double> instructionsFileReader =
                new InstructionsFileReader<>(parser);
        String file = classLoader.getResource("testFile.txt").getFile();

        LinkedList<Instruction<Double>> instructions = instructionsFileReader.read(file);

        assertNotNull(instructions);
        assertEquals(3, instructions.size());

        Instruction<Double> instruction0 = instructions.get(0);
        assertNotNull(instruction0);
        assertEquals(DoubleOperationTypes.ADD, instruction0.getOperationType());
        assertEquals(Double.valueOf(2), instruction0.getValue());

        Instruction<Double> instruction1 = instructions.get(1);
        assertNotNull(instruction1);
        assertEquals(DoubleOperationTypes.MULTIPLY, instruction1.getOperationType());
        assertEquals(Double.valueOf(3), instruction1.getValue());

        Instruction<Double> instruction2 = instructions.get(2);
        assertNotNull(instruction2);
        assertEquals(DoubleOperationTypes.APPLY, instruction2.getOperationType());
        assertEquals(Double.valueOf(4), instruction2.getValue());
    }

    @Test
    void read_OnlyInitInstruction() throws IOException {
        InstructionsFileReader<Double> instructionsFileReader =
                new InstructionsFileReader<>(parser);
        String file = classLoader.getResource("onlyInitInstructionFile.txt").getFile();

        LinkedList<Instruction<Double>> instructions = instructionsFileReader.read(file);

        assertNotNull(instructions);
        assertEquals(1, instructions.size());

        Instruction<Double> instruction0 = instructions.get(0);
        assertNotNull(instruction0);
        assertEquals(DoubleOperationTypes.APPLY, instruction0.getOperationType());
        assertEquals(Double.valueOf(4), instruction0.getValue());
    }

}