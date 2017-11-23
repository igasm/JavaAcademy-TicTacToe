package settings;

import io.ConsoleReader;

import java.util.function.Consumer;

public class SettingsManager {

    private final String settingsFilePath = "./src/main/resources/settings.json";
    private final Consumer<String> consoleWriter;
    private final ConsoleReader consoleReader;

    public SettingsManager(Consumer<String> consoleWriter, ConsoleReader consoleReader) {
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
    }

    public Settings run(){
        Settings settings = load();
        consoleWriter.accept("Czy chcesz modyfikować ustawienia planszy? [T/N]");

        if(consoleReader.getString().toUpperCase().equals("T")){
            consoleWriter.accept(System.getProperty("line.separator"));
            settings = reconfigure(consoleReader, consoleWriter);
        }
        return settings;
    }

    Settings load(){
        String newline = System.getProperty("line.separator");
        String message;
        Settings settings = new SettingsReader(settingsFilePath).load();

        if(settings != null){
            message = "Wczytane ustawienia planszy:" +
                    newline + "\tWymiary planszy: "
                    + settings.getBoardDimensions().getWidth() + "x" + settings.getBoardDimensions().getWidth() +
                    newline + "\tWarunek wygranej: " + settings.getWinningCondition() + " znaków" + newline;
        }else{
            settings =  new Settings(new BoardDimensions(3, 3), 3);
            message = "Wczytywanie domyślnych ustawień" +
                    newline + "\tWymiary planszy: 3x3." +
                    newline + "\tWarunek wygranej: 3 znaki" + newline;
        }

        consoleWriter.accept(message);
        return settings;
    }

    Settings reconfigure(ConsoleReader consoleReader, Consumer<String> consoleWriter) {
        consoleWriter.accept("Podaj szerkość planszy");
        int width = consoleReader.getInt();
        consoleWriter.accept("Podaj wysokość planszy");
        int height = consoleReader.getInt();
        BoardDimensions boardDimensions = new BoardDimensions(width, height);
        consoleWriter.accept("Podaj warunek wygranej (liczba znaków w linii)");
        int winningCondition = consoleReader.getInt();
        Settings settings = new Settings(boardDimensions, winningCondition);
        SettingsWriter settingsWriter = new SettingsWriter(settingsFilePath);
        if(settingsWriter.save(settings)){
            consoleWriter.accept("Ustawienia zostały zapisane");
        }else{
            consoleWriter.accept("Zapisywanie ustawień nie powiodło się");
        }
        return settings;
    }

}
