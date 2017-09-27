package cz.verbovsky.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of the tests for {@link DoubleInstructionParser}.
 *
 * @author Martin Verbovsky
 */
class DoubleInstructionParserTest {

    @Test
    void parse_NullParam_ExceptionThrown() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> new DoubleInstructionParser().parse(null));
        assertEquals("String arguments must not be null.", exception.getMessage());
    }

    @Test
    void parse_BadFormat_ExceptionThrown() {
        Throwable exception = assertThrows(FileFormatException.class,
                () -> new DoubleInstructionParser().parse("1aaa a1"));
        assertEquals("Bad format.", exception.getMessage());
    }

    @Test
    void parse_NoOperation_ExceptionThrown() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new DoubleInstructionParser().parse("aaa 1"));
        assertEquals("No operation type found for name aaa", exception.getMessage());
    }

    @Test
    void parse() {
        DoubleInstructionParser parser = new DoubleInstructionParser();
        Instruction<Double> instruction = parser.parse("apply 10.12");
        assertEquals(DoubleOperationTypes.APPLY, instruction.getOperationType());
        assertEquals(Double.valueOf(10.12), instruction.getValue());
    }

}