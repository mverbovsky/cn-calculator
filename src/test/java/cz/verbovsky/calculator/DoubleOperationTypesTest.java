package cz.verbovsky.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of the tests for {@link DoubleOperationTypes}
 *
 * @author Martin Verbovsky
 */
class DoubleOperationTypesTest {

    @Test
    void values_Length() {
        assertEquals(5, DoubleOperationTypes.values().length);
    }

    @Test
    void getOperationTypeByName_NullParam_ExceptionThrown() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> DoubleOperationTypes.getOperationTypeByName(null));
        assertEquals("Name must not be null.", exception.getMessage());
    }

    @Test
    void getOperationTypeByName_UnknownOperation_ExceptionThrown() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> DoubleOperationTypes.getOperationTypeByName("unknown"));
        assertEquals("No operation type found for name unknown", exception.getMessage());
    }

    @Test
    void getOperationTypeByName_Apply() {
        OperationType<Double> operation = DoubleOperationTypes.getOperationTypeByName("apply");
        assertEquals(DoubleOperationTypes.APPLY, operation);
    }

    @Test
    void getOperationTypeByName_Add() {
        OperationType<Double> operation = DoubleOperationTypes.getOperationTypeByName("add");
        assertEquals(DoubleOperationTypes.ADD, operation);
    }

    @Test
    void getOperationTypeByName_Multiply() {
        OperationType<Double> operation = DoubleOperationTypes.getOperationTypeByName("multiply");
        assertEquals(DoubleOperationTypes.MULTIPLY, operation);
    }

    @Test
    void getOperationTypeByName_Subtract() {
        OperationType<Double> operation = DoubleOperationTypes.getOperationTypeByName("subtract");
        assertEquals(DoubleOperationTypes.SUBTRACT, operation);
    }

    @Test
    void getOperationTypeByName_Divide() {
        OperationType<Double> operation = DoubleOperationTypes.getOperationTypeByName("divide");
        assertEquals(DoubleOperationTypes.DIVIDE, operation);
    }

    @Test
    void getName_Apply() {
        assertEquals("apply", DoubleOperationTypes.APPLY.getName());
    }

    @Test
    void getName_Add() {
        assertEquals("add", DoubleOperationTypes.ADD.getName());
    }

    @Test
    void getName_Multiply() {
        assertEquals("multiply", DoubleOperationTypes.MULTIPLY.getName());
    }

    @Test
    void getName_Subtract() {
        assertEquals("subtract", DoubleOperationTypes.SUBTRACT.getName());
    }

    @Test
    void getName_Divide() {
        assertEquals("divide", DoubleOperationTypes.DIVIDE.getName());
    }

    @Test
    void getOperation_Apply() {
        assertNotNull(DoubleOperationTypes.APPLY.getOperation());
        assertEquals(Double.valueOf(2), DoubleOperationTypes.APPLY.getOperation().apply(Double.valueOf(2)));
    }

    @Test
    void getOperation_Add() {
        assertNotNull(DoubleOperationTypes.ADD.getOperation());
        assertEquals(Double.valueOf(2 + 3), DoubleOperationTypes.ADD.getOperation().apply(2d, 3d));
    }

    @Test
    void getOperation_Multiply() {
        assertNotNull(DoubleOperationTypes.MULTIPLY.getOperation());
        assertEquals(Double.valueOf(2 * 3), DoubleOperationTypes.MULTIPLY.getOperation().apply(2d, 3d));
    }

    @Test
    void getOperation_Divide() {
        assertNotNull(DoubleOperationTypes.DIVIDE.getOperation());
        assertEquals(Double.valueOf(2d / 3d), DoubleOperationTypes.DIVIDE.getOperation().apply(2d, 3d));
    }

    @Test
    void getOperation_DivideByZero() {
        Double result = DoubleOperationTypes.DIVIDE.getOperation().apply(2d, 0d);
        assertTrue(result.isInfinite());
    }

}