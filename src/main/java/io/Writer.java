package io;

import java.io.PrintStream;
import java.util.function.Consumer;

public class Writer {

    private final Consumer<String> consoleWriter;


    public Writer(PrintStream printStream) {
        consoleWriter = printStream::println;
        printStream.getClass().getName();
    }

    public void accept(String message){
        consoleWriter.accept(message);
    }

    public String getPrintStreamClass(){
        return consoleWriter.toString();
    }

}
