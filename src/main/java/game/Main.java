package game;

import io.ConsoleReader;
import io.Writer;
import io.WriterBuilder;
import settings.Settings;
import settings.SettingsManager;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        ConsoleReader consoleReader = new ConsoleReader();
        ArgsManager argsManager = new ArgsManager(args);
        Writer consoleWriter = new WriterBuilder().byTargetName(argsManager.getOutputArg());
        Consumer<Exception> exceptionHandler = e -> System.out.println("Exception occured " + e.getMessage());
        consoleWriter.accept("Witaj w grze kółko i krzyżyk\n");
        Settings settings = new SettingsManager(consoleWriter, consoleReader).run();
        GameManager boardManager = new GameLoader(consoleWriter, consoleReader, settings).load();
        boardManager.runGame();
    }
}
