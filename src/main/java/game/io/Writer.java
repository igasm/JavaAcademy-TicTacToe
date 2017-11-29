package game.io;

import java.io.PrintStream;
import java.util.function.Consumer;

public class Writer {

    private final Consumer<String> consoleWriter;
    private final Translator translator;
    public final String newline = System.getProperty("line.separator");


    public Writer(PrintStream printStream, Translator translator) {
        this.consoleWriter = printStream::print;
        this.translator = translator;
    }


    public void printlnViaTranslator(String messageCode){
        consoleWriter.accept(translator.messageByCode(messageCode) + newline);
    }

    public void printViaTranslator(String messageCode){
        consoleWriter.accept(translator.messageByCode(messageCode));
    }

    public void println(String string) { consoleWriter.accept(string + newline); }

    public void print(String string) { consoleWriter.accept(string); }

    public void addNewLine(){
        consoleWriter.accept(newline);
    }

    public Translator getTranslator(){
        return translator;
    }

}
