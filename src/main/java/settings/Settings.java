package settings;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import settings.BoardDimensions;

import java.io.FileReader;
import java.util.function.Consumer;

public class Settings {

    private JSONParser jsonParser;
    private final Consumer<Exception> exceptionHandler;
    private BoardDimensions boardDimensions;

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
            Object obj = jsonParser.parse(new FileReader("./src/main/resources/settings.json"));
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
}
