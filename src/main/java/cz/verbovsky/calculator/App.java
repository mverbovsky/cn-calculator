package cz.verbovsky.calculator;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Calculator application which loads instructions from a given file.
 * Instructions comprise of a keyword and a number that are separated
 * by a space per line. Instructions are loaded from file and results
 * are output to the screen. Any number of Instructions can be specified.
 * The instructions ignore mathematical precedence. The last instruction
 * has to be “apply” and a number (e.g., “apply 3”). The calculator is
 * then initialised with that number and the previous instructions
 * are applied to that number.
 *
 * Example 1.
 * <pre>{@code
 *
 *  [Input from file]
 *  add 2
 *  multiply 3
 *  apply 4
 *
 *  [Output to screen]
 *  18
 *
 * }</pre>
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

            try {
                LinkedList<Instruction<Double>> instructions = instructionsFileReader.read(args[0]);
                Calculator<Double> doubleCalculator = new DoubleCalculator();
                Double result = doubleCalculator.calculate(instructions);
                System.out.println("Result: " + result.toString());
            } catch (IOException e) {
                System.out.println("During reading the file an error has occurred: " + e.getMessage());
            } catch (FileFormatException e) {
                System.out.println("The file has an appropriate format.");
            }
        } else {
            System.out.println("Enter a file name with instructions as parameter.");
        }

    }
}
