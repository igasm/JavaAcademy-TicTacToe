package game.io;

import java.io.PrintStream;
import java.util.function.Consumer;

public class Writer {

    private final Consumer<String> consoleWriter;
    public final String newline = System.getProperty("line.separator");

    public Writer(PrintStream printStream) {
        consoleWriter = printStream::println;
    }

    public void accept(String message){
        consoleWriter.accept(message);
    }

    public void addNewLine(){
        consoleWriter.accept("");
    }

}
