package cz.verbovsky.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Set of the tests for {@link DoubleCalculator}
 *
 * @author Martin Verbovsky
 */
class DoubleCalculatorTest {

    @Test
    void calculate() {
        DoubleCalculator doubleCalculator = new DoubleCalculator();
        LinkedList<Instruction<Double>> instructions = new LinkedList<>();

        instructions.add(new Instruction<>(DoubleOperationTypes.ADD, 3d));
        instructions.add(new Instruction<>(DoubleOperationTypes.SUBTRACT, 9d));
        instructions.add(new Instruction<>(DoubleOperationTypes.MULTIPLY, 2d));
        instructions.add(new Instruction<>(DoubleOperationTypes.DIVIDE, 2d));
        instructions.add(new Instruction<>(DoubleOperationTypes.APPLY, 5d));

        Assertions.assertEquals(Double.valueOf(-1), doubleCalculator.calculate(instructions));
    }

    @Test
    void calculate_OnlyInitializationInstruction() {
        DoubleCalculator doubleCalculator = new DoubleCalculator();
        LinkedList<Instruction<Double>> instructions = new LinkedList<>();

        instructions.add(new Instruction<>(DoubleOperationTypes.APPLY, 5d));

        Assertions.assertEquals(Double.valueOf(5), doubleCalculator.calculate(instructions));
    }

    @Test
    void calculate_NullParam_ExceptionThrown() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> new DoubleCalculator().calculate(null));
        assertEquals("Instructions must not be null.", exception.getMessage());
    }

    @Test
    void calculate_EmptyInstructions_ExceptionThrown() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new DoubleCalculator().calculate(new LinkedList<>()));
        assertEquals("Instructions must not be empty.", exception.getMessage());
    }

    @Test
    void calculate_NoInitializationInstruction_ExceptionThrown() {
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            LinkedList<Instruction<Double>> instructions = new LinkedList<>();

            OperationType<Double> mockOperationType = mock(OperationType.class);
            when(mockOperationType.isInitializationOperation()).thenReturn(false);

            Instruction<Double> mockInstruction = mock(Instruction.class);
            when(mockInstruction.getOperationType()).thenReturn(mockOperationType);

            instructions.add(mockInstruction);

            new DoubleCalculator().calculate(instructions);
        });
        assertEquals("The last instruction has to be initialization instruction.", exception.getMessage());
    }

}