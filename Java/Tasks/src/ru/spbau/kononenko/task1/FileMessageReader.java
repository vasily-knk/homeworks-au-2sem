package ru.spbau.kononenko.task1;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Vasya
 * Date: 21.03.12
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */
class FileMessageReader implements MessageReader {
    private final BufferedReader reader;
    
    public FileMessageReader(String path) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(path));
    }

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
            throw new IllegalMessageFormatException("Can't read lines number: got \""
                                                    + str + "\"");
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
