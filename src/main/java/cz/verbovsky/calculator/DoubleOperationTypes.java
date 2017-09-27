package cz.verbovsky.calculator;

import java.util.Arrays;
import java.util.Objects;

/**
 * Enum of supported operation types.
 *
 * @author Martin Verbovsky
 */
public enum DoubleOperationTypes implements OperationType<Double> {

    APPLY("apply", true, (input) -> input[0]),
    ADD("add", false, (input) -> input[0] + input[1]),
    SUBTRACT("subtract", false, (input) -> input[0] - input[1]),
    MULTIPLY("multiply", false, (input) -> input[0] * input[1]),
    DIVIDE("divide", false, (input) -> input[0] / input[1]);

    private final String name;
    private final boolean initializationOperation;
    private final Operation<Double> operation;

    DoubleOperationTypes(String name, boolean initializationOperation, Operation<Double> operation) {
        this.name = Objects.requireNonNull(name, "Name most not be null.");
        this.initializationOperation = initializationOperation;
        this.operation = Objects.requireNonNull(operation, "Operation must not be null.");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isInitializationOperation() {
        return initializationOperation;
    }

    @Override
    public Operation<Double> getOperation() {
        return operation;
    }

    public static OperationType<Double> getOperationTypeByName(String name) {
        Objects.requireNonNull(name, "Name must not be null.");
        OperationType<Double> operationType =
                Arrays.stream(DoubleOperationTypes.values())
                        .filter(op -> op.getName().equals(name))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("No operation type found for name " + name));
        return operationType;
    }

}
