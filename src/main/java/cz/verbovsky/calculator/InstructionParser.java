package cz.verbovsky.calculator;

/**
 * Instruction parser interface for parsing given string
 * and creating corresponding {@link Instruction}.
 *
 * @param <T> the type of the operands
 *
 * @author Martin Verbovsky
 */
public interface InstructionParser<T> {

    /**
     * Parse given string and creates corresponding instance of {@link Instruction}.
     *
     * @param string non-null string representing instruction for {@link Calculator}
     *
     * @return instance of {@link Instruction} based on given parameters.
     */
    Instruction<T> parse(String string);
}
