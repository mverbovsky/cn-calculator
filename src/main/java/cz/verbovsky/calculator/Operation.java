package cz.verbovsky.calculator;

/**
 * Interface of n-operator.
 *
 * @param <T> the type of the operands
 *
 * @author Martin Verbovsky
 */
@FunctionalInterface
public interface Operation<T> {

    /**
     * Method which applies this function to the given arguments.
     *
     * @param args the function arguments
     *
     * @return the function result
     */
    T apply(T... args);
}
