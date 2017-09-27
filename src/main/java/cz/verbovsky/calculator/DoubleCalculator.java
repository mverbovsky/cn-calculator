package cz.verbovsky.calculator;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Implementation of {@link Calculator} for double.
 *
 * @author Martin Verbovsky
 */
public class DoubleCalculator implements Calculator<Double> {

    @Override
    public Double calculate(LinkedList<Instruction<Double>> instructions) {
        Objects.requireNonNull(instructions, "Instructions must not be null.");
        if (instructions.isEmpty()) {
            throw new IllegalArgumentException("Instructions must not be empty.");
        }

        LinkedList<Instruction<Double>> instructionsCopy = new LinkedList<>(instructions);
        Instruction<Double> initializationInstruction = instructionsCopy.removeLast();

        if (initializationInstruction == null
                || !initializationInstruction.getOperationType().isInitializationOperation()) {
            throw new IllegalStateException("The last instruction has to be initialization instruction.");
        }

        Double result = initializationInstruction.applyToValue();
        for (Instruction<Double> instruction : instructionsCopy) {
            result = instruction.applyToValue(result);
        }
        return result;
    }

}
