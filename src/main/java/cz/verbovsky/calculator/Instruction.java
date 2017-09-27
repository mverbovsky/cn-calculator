package cz.verbovsky.calculator;

import java.util.Arrays;
import java.util.Objects;

/**
 * Instruction for {@link Calculator} which contains {@link OperationType}
 * and value of the operand.
 *
 * @param <T> the type of the operands
 *
 * @author Martin Verbovsky
 */
public class Instruction<T> {

    private final OperationType<T> operationType;
    private final T value;

    /**
     * Constructor for initialization which requires non-null
     * parameters.
     *
     * @param operationType non-null type of operation
     * @param value non-null value of operand
     */
    public Instruction(OperationType<T> operationType, T value) {
        this.operationType = Objects.requireNonNull(operationType, "OperationType must not be null.");
        this.value = Objects.requireNonNull(value, "Value must not be null.");
    }

    public OperationType<T> getOperationType() {
        return operationType;
    }

    public T getValue() {
        return value;
    }

    /**
     * Apply given {@link OperationType} on {@link Instruction} value
     * and method arguments. If {@link OperationType} is initialization
     * operation than method arguments are not used.
     *
     * @param a operands for instruction's operation
     *
     * @return result of instruction's operation
     */
    @SafeVarargs
    public final T applyToValue(T... a) {
        Objects.requireNonNull(a, "Arguments must not be null.");
        int newLength = a.length + 1;
        T[] values = Arrays.copyOf(a, newLength);
        values[newLength - 1] = value;
        return operationType.getOperation().apply(values);
    }
}
