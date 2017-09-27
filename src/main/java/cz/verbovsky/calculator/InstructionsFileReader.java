package cz.verbovsky.calculator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Instructions reader which reads instructions from file.
 *
 * @param <T> the type of the operands
 *
 * @author Martin Verbovsky
 */
public class InstructionsFileReader<T> {

    private final InstructionParser<T> parser;

    /**
     * Constructor.
     *
     * @param parser non-null parser that is used for parsing lines in the given files
     */
    public InstructionsFileReader(InstructionParser<T> parser) {
        this.parser = Objects.requireNonNull(parser, "Parser argument must not be null.");
    }

    /**
     * Create a {@link LinkedList} of instructions for {@link Calculator}.
     * The last instruction is the initialization instruction.
     *
     * @param fileName file name of a file which contains instructions for {@link Calculator}
     *
     * @return LinkedList of the instructions. The last instruction is the initialization instruction
     */
    public LinkedList<Instruction<T>> read(String fileName) throws IOException, FileFormatException {
        LineIterator lineIterator = getLineIterator(fileName);

        LinkedList<Instruction<T>> instructionsList = new LinkedList<>();

        boolean applyFound = false;

        while (lineIterator.hasNext() && !applyFound) {
            String line = lineIterator.nextLine();
            Instruction<T> instruction = parser.parse(line);
            if (instruction == null) {
                throw new FileFormatException("Instruction must not be null.");
            }
            instructionsList.add(instruction);
            if (instruction.getOperationType().isInitializationOperation()) {
                applyFound = true;
            }
        }

        if (!applyFound) {
            throw new FileFormatException("Initialization operation must be defined.");
        }

        return instructionsList;
    }

    private LineIterator getLineIterator(String fileName) throws IOException {
        Objects.requireNonNull(fileName, "FileName argument must not be null.");
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("FileName argument must not be empty.");
        }

        LineIterator lineIterator;
        File file = FileUtils.getFile(fileName);
        lineIterator = FileUtils.lineIterator(file);

        return lineIterator;
    }

}
