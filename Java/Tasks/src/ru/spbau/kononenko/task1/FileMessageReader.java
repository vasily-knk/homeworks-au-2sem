package ru.spbau.kononenko.task1;

import java.io.*;

/**
 * Message reader which reads messages from a text file in the following format:
 * <p />
 * <pre> Number of lines in message 1
 * Line 1
 * Line 2
 * ...
 * Number of lines in message 2
 * ...</pre>
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class FileMessageReader implements MessageReader {
    private final BufferedReader reader;

    /**
     * opens file for reading
     * @param path input file path
     * @throws FileNotFoundException if the file can't be opened
     */
    public FileMessageReader(String path) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(path));
    }

    /**
     * closes the input file
     * @throws IOException if some IO problem is encountered
     */
    @Override
    public void close() throws IOException {
        reader.close();
    }
    
    @Override
    public Message readMessage() throws IOException, IllegalMessageFormatException {
        Message message = new Message();

        String str = reader.readLine();
        if (str == null)
            return null;
            
        int linesNum;
        try {
            linesNum = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalMessageFormatException("Can't read the lines number: got " +
                                                    "\"" + str + "\"");
        }
        
        for (int i = 0; i < linesNum; ++i) {
            str = reader.readLine();
            if (str == null)
                throw new IllegalMessageFormatException("Can't read enough lines");
            message.append(str);
        }

        return message;
    }
}
