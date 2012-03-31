package ru.spbau.kononenko.task1;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Main class
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class Main {
    /**
     * program entry point
     * @param args command-line args
     */
    public static void main(String[] args) {
        if (args.length == 0)
        {
            System.err.println("Args expected: in_filename [out_filename]");
            return;
        }
        
        try (MessageReader reader = new FileMessageReader(args[0]);
                MessageWriter realWriter = (args.length == 1)
                                           ? new ConsoleMessageWriter()
                                           : new FileMessageWriter(args[1]);
                MessageWriter writer = new CompressingMessageWriter(realWriter)) {
        
            Message message;
            while ((message = reader.readMessage()) != null)
                writer.writeMessage(message);

        } catch (FileNotFoundException e) {
            System.err.println("File error: " + e.getMessage());
        } catch (IllegalMessageFormatException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
