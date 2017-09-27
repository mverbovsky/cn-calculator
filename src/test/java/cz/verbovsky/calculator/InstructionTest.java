package cz.verbovsky.calculator;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of the tests for {@link Instruction}
 *
 * @author Martin Verbovsky
 */
class InstructionTest {

    @Test
    void constructor_NullAs1stParam_ExceptionThrown() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> new Instruction<>(null, 1d));
        assertEquals("OperationType must not be null.", exception.getMessage());
    }

    @Test
    void constructor_NullAs2ndParam_ExceptionThrown() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> new Instruction<>(DoubleOperationTypes.APPLY, null));
        assertEquals("Value must not be null.", exception.getMessage());
    }

    @Test
    void getOperationType() {
        OperationType<Double> operationType = DoubleOperationTypes.APPLY;
        Instruction<Double> instruction = new Instruction<>(operationType, 1d);
        assertEquals(operationType, instruction.getOperationType());
    }

    @Test
    void getValue() {
        Double value = 1d;
        OperationType<Double> operationType = DoubleOperationTypes.APPLY;
        Instruction<Double> instruction = new Instruction<>(operationType, value);
        assertEquals(value, instruction.getValue());
    }

    @Test
    void applyToValue_NullArguments_ExceptionThrown() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            Double value = 1d;
            OperationType<Double> mockedOperationType = mock(OperationType.class);
            Instruction<Double> instruction = new Instruction<>(mockedOperationType, value);
            instruction.applyToValue(null);
        });
        assertEquals("Arguments must not be null.", exception.getMessage());
    }

    @Test
    void applyToValue_UnaryOperator() {
        Double value = 1d;
        OperationType<Double> mockedOperationType = mock(OperationType.class);
        when(mockedOperationType.isInitializationOperation()).thenReturn(true);
        when(mockedOperationType.getOperation()).thenReturn((input) -> input[0]);

        Instruction<Double> instruction = new Instruction<>(mockedOperationType, value);
        assertEquals(value, instruction.applyToValue());
    }

    @Test
    void applyToValue_BinaryOperator() {
        OperationType<Double> mockedOperationType = mock(OperationType.class);
        when(mockedOperationType.isInitializationOperation()).thenReturn(false);
        when(mockedOperationType.getOperation()).thenReturn((input) -> input[0] + input[1]);

        Double value1 = 1d;
        Double value2 = 4d;
        Instruction<Double> instruction = new Instruction<>(mockedOperationType, value1);
        assertEquals(Double.valueOf(5), instruction.applyToValue(value2));
    }

}