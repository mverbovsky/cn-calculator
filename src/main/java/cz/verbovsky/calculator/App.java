package cz.verbovsky.calculator;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Calculator application which loads instructions from a given file.
 *
 * @author Martin Verbovsky
 */
public class App {

    public static void main(String[] args) {
        Objects.requireNonNull(args, "Arguments must not be null.");
        if (args.length > 0) {
            System.out.println("Input: " + args[0]);
            InstructionParser<Double> instructionParser = new DoubleInstructionParser();
            InstructionsFileReader<Double> instructionsFileReader = new InstructionsFileReader<>(instructionParser);

            LinkedList<Instruction<Double>> instructions = instructionsFileReader.read(args[0]);

            Calculator<Double> doubleCalculator = new DoubleCalculator();
            Double result = doubleCalculator.calculate(instructions);

            System.out.println("Result: " + result.toString());
        } else {
            System.out.println("Enter a file name with instructions as parameter.");
        }

    }
}
