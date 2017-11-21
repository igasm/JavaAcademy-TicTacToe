package settings;

import io.ConsoleReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class Settings {

    private JSONParser jsonParser;
    private final Consumer<Exception> exceptionHandler;
    private BoardDimensions boardDimensions;
    private String settingsFilePath = "./src/main/resources/settings.json";
    private int winningCondition;

    public Settings(Consumer<Exception> exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        jsonParser = new JSONParser();
    }

    public Settings(Consumer<Exception> exceptionHandler, BoardDimensions boardDimensions, int winningCondition){
        this.exceptionHandler = exceptionHandler;
        this.boardDimensions = boardDimensions;
        this.winningCondition = winningCondition;
    }


    public String loadFromJSONFile(){
        String newline = System.getProperty("line.separator");
        String message;
        try {
            Object obj = jsonParser.parse(new FileReader(settingsFilePath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObjectDimensions = (JSONObject) jsonObject.get("board_dimensions");
            Long width = (Long) jsonObjectDimensions.get("width");
            Long height = (Long) jsonObjectDimensions.get("height");
            boardDimensions = new BoardDimensions(width.intValue(), height.intValue());
            winningCondition = ((Long) jsonObject.get("winning_condition")).intValue();
            message = "Wczytane ustawienia planszy:" +
                    newline + "\tWymiary planszy: " + width + "x" + height +
                    newline + "\tWarunek wygranej: " + winningCondition + " znaków" + newline;
        }catch (Exception e){
            exceptionHandler.accept(e);
            boardDimensions = new BoardDimensions(3, 3);
            winningCondition = 3;
            message = "Wczytywanie domyślnych ustawień" +
                    newline + "\tWymiary planszy: 3x3." +
                    newline + "\tWarunek wygranej: 3 znaki" + newline;
        }

        return message;
    }

    public BoardDimensions getBoardDimensions() {
        return boardDimensions;
    }

    public int getWinningCondition() {
        return winningCondition;
    }

    public int getBoardElementsCount() { return boardDimensions.getWidth() * boardDimensions.getHeight(); }

    public void reconfigure(ConsoleReader consoleReader, Consumer<String> consoleWriter) {
        consoleWriter.accept("Podaj szerkość planszy");
        int width = consoleReader.getInt();
        consoleWriter.accept("Podaj wysokość planszy");
        int height = consoleReader.getInt();
        boardDimensions = new BoardDimensions(width, height);
        consoleWriter.accept("Podaj warunek wygranej (liczba znaków w linii)");
        winningCondition = consoleReader.getInt();
        JSONObject mainObj = new JSONObject();
        JSONObject boarDimensionsObj = new JSONObject();
        boarDimensionsObj.put("width", width);
        boarDimensionsObj.put("height", height);
        mainObj.put("board_dimensions", boarDimensionsObj);
        mainObj.put("winning_condition", winningCondition);

        try (FileWriter file = new FileWriter(settingsFilePath)) {

            file.write(mainObj.toJSONString());
            file.flush();

        } catch (IOException e) {
            exceptionHandler.accept(e);
        }

        consoleWriter.accept("Ustawienia zostały zapisane");
    }
}
