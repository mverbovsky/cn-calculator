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
        assertEquals(exception.getMessage(), "String arguments must not be null.");
    }

    @Test
    void parse_BadFormat_ExceptionThrown() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new DoubleInstructionParser().parse("1aaa a1"));
        assertEquals(exception.getMessage(), "Bad string format.");
    }

    @Test
    void parse_NoOperation_ExceptionThrown() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new DoubleInstructionParser().parse("aaa 1"));
        assertEquals(exception.getMessage(), "No operation type found for name aaa");
    }

    @Test
    void parse() {
        DoubleInstructionParser parser = new DoubleInstructionParser();
        Instruction<Double> instruction = parser.parse("apply 10.12");
        assertEquals(DoubleOperationTypes.APPLY, instruction.getOperationType());
        assertEquals(Double.valueOf(10.12), instruction.getValue());
    }

}