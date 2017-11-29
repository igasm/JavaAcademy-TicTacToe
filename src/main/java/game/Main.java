package game;

import game.io.ConsoleReader;
import game.io.Writer;
import game.io.WriterBuilder;
import game.settings.Settings;
import game.settings.SettingsManager;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        ArgsManager argsManager = new ArgsManager(args);
        Writer consoleWriter = new WriterBuilder().byOutputName(argsManager.getOutputArg()).byLanguage(argsManager.getLanguageArg()).build();
        Consumer<Exception> exceptionHandler = e -> System.out.println("Exception occured " + e.getMessage());
        try(ConsoleReader consoleReader = new ConsoleReader(consoleWriter)) {
            consoleWriter.printlnViaTranslator("welcome");
            consoleWriter.addNewLine();
            Settings settings = new SettingsManager(consoleWriter, consoleReader).run();
            GameManager boardManager = new GameLoader(consoleWriter, consoleReader, settings).load();
            boardManager.runGame();
        } catch (Exception e) {
            exceptionHandler.accept(e);
        }
    }
}
