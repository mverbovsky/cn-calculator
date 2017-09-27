package cz.verbovsky.calculator;

/**
 * Interface of operation type wrapping function - {@link Operation}.
 *
 * @param <T> the type of the operands
 *
 * @author Martin Verbovsky
 */
public interface OperationType<T> {

    String getName();

    boolean isInitializationOperation();

    Operation<T> getOperation();

}
