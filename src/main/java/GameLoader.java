public class GameLoader {
    public static void main(String[] args) {
        Board board = new Board();
        Player playerA = new Player("playerA");
        Player playerB = new Player("playerB");
        PlayersRegister playersRegister;

        Translator translator = new Translator();
        translator.registerLanguage("PL");
        translator.registerLanguage("ENG");
        translator.setCurrentLanguage("ENG");

        BoardManager boardManager =  new BoardManager(board, playerA, playerB, translator);
        boardManager.welcome();
        boardManager.runGame();

    }
}
