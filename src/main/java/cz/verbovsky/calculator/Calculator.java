package cz.verbovsky.calculator;

import java.util.LinkedList;

/**
 * Interface of calculator that carry out calculations based on given instructions.
 * The instructions ignore mathematical precedence. The last instruction has to be
 * initialization instruction. The calculator is then initialised with that number
 * and the previous instructions are applied to that number.
 *
 * @param <T> the type of the operands
 *
 * @author Martin Verbovsky
 */
public interface Calculator<T> {

    /**
     * Calculate a result from a set of instructions.
     *
     * @param instructions non-null and non-empty set of instructions
     *
     * @return result based on the given instructions.
     */
    T calculate(LinkedList<Instruction<T>> instructions);

}
