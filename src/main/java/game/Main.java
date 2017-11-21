package game;

import io.ConsoleReader;
import settings.Settings;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        ConsoleReader consoleReader = new ConsoleReader();
        Consumer<String> consoleWriter = System.out::println;
        Consumer<Exception> exceptionHandler = e -> System.out.println("Exception occured " + e.getMessage());
        consoleWriter.accept("Witaj w grze kółko i krzyżyk\n");
        Settings settingsLoader = new Settings(exceptionHandler);
        consoleWriter.accept(settingsLoader.loadFromJSONFile());
        consoleWriter.accept("Czy chcesz modyfikować ustawienia planszy? [T/N]");

        if(consoleReader.getString().toUpperCase().equals("T")){
            consoleWriter.accept(System.getProperty("line.separator"));
            settingsLoader.reconfigure(consoleReader, consoleWriter);
        }

        consoleWriter.accept(System.getProperty("line.separator"));

        GameLoader gameLoader = new GameLoader(consoleWriter, consoleReader, settingsLoader);
        GameManager boardManager = gameLoader.load();
        boardManager.runGame();
    }
}
