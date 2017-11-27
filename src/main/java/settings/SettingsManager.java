package settings;

import io.ConsoleReader;
import io.Writer;

import java.util.function.Consumer;

public class SettingsManager {

    private final Integer minBorderDimension = 3;
    private final Integer maxBorderDimension = 101;
    private final Integer minWinningCondition = minBorderDimension;
    private final String settingsFilePath = "./src/main/resources/settings.json";
    private final Writer consoleWriter;
    private final ConsoleReader consoleReader;

    public SettingsManager(Writer consoleWriter, ConsoleReader consoleReader) {
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

    Settings reconfigure(ConsoleReader consoleReader, Writer consoleWriter) {
        int width = 3, height = 3, winningCondition = 3;
        boolean validValue = false;
        SettingsValidator settingsValidator = new SettingsValidator(minBorderDimension, maxBorderDimension, minWinningCondition);
        consoleWriter.accept("Minimalna szerokość/wysokość planszy  to " + minBorderDimension);
        consoleWriter.accept("Maksymalna szerokość/wysokość planszy  to " +  maxBorderDimension);

        consoleWriter.accept("");
        while (!validValue){
            consoleWriter.accept("Podaj szerokość planszy");
            width = consoleReader.getInt();
            validValue = settingsValidator.validBorderDimension(width);
        }

        consoleWriter.accept("");
        validValue = false;
        while (!validValue){
            consoleWriter.accept("Podaj wysokość planszy");
            height = consoleReader.getInt();
            validValue = settingsValidator.validBorderDimension(height);
        }

        BoardDimensions boardDimensions = new BoardDimensions(width, height);

        consoleWriter.accept("");
        validValue = false;
        while (!validValue){
            consoleWriter.accept("Podaj warunek wygranej (liczba znaków w linii)");
            consoleWriter.accept("(Maksymalny dopuszczalny warunek zwycięstwa to "
                    + boardDimensions.getMinDimension() + " znaki)");
            winningCondition = consoleReader.getInt();
            validValue = settingsValidator.validWinningCondition(boardDimensions, winningCondition);
        }

        Settings settings = new Settings(boardDimensions, winningCondition);
        SettingsWriter settingsWriter = new SettingsWriter(settingsFilePath);
        if(settingsWriter.save(settings)){
            consoleWriter.accept("Ustawienia zostały zapisane");
        }else{
            consoleWriter.accept("Zapisywanie ustawień nie powiodło się");
        }
        consoleWriter.accept("");
        return settings;
    }

}
