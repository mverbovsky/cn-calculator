package cz.verbovsky.calculator;

import java.util.Objects;

/**
 * Implementation of {@link InstructionParser}. Instructions comprise
 * of a keyword (operation name) and a number (r-operand) that are separated by a space.
 * <pre>{@code
 *   OPERATION_NAME R-OPERAND
 *
 *   Examples:
 *   add 2
 *   multiply 3
 *   apply 4
 *
 * }</pre>
 *
 * See {@link DoubleOperationTypes} for supported instructions.
 *
 * @author Martin Verbovsky
 */
public class DoubleInstructionParser implements InstructionParser<Double> {

    @Override
    public Instruction<Double> parse(String string) throws FileFormatException {
        Objects.requireNonNull(string, "String arguments must not be null.");
        if (!string.matches("[a-z]+\\s+[0-9]{1,13}(\\.[0-9]*)?")) {
            throw new FileFormatException("Bad format.");
        }
        String[] split = string.split("\\s+");

        try {
            OperationType<Double> operationType = DoubleOperationTypes.getOperationTypeByName(split[0]);
            Double value = Double.valueOf(split[1]);
            return new Instruction<>(operationType, value);
        } catch (NumberFormatException e) {
            throw new FileFormatException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new FileFormatException(e.getMessage(), e);
        }
    }
}
