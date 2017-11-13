import java.util.Scanner;

public class GameLoader {
    public static void main(String[] args) {
        System.out.println("Witaj w grze kółko i krzyżyk");
        BoardDimensions boardDimensions = new BoardDimensions(3, 3);
        BoardBuilder boardBuilder = new BoardBuilder(boardDimensions);
        Board board = new Board(boardBuilder.buildBoardWithFieldNumbers(), boardBuilder.buildBoardWithMarks() ,boardDimensions);
        Scanner sc = new Scanner(System.in);
        MoveSupervisor moveSupervisor = new MoveSupervisor(board, 3);
        PlayersRegister playersRegister = new PlayersRegister(2);
        System.out.println("Podaj imię pierwszego gracza");
        playersRegister.registerPlayer(new Player(sc.nextLine(), "x"));
        System.out.println("Podaj imię drugiego gracza");
        playersRegister.registerPlayer(new Player(sc.nextLine(), "o"));
        PlayersQueue playersQueue = new PlayersQueue(playersRegister);
        BoardManager boardManager = new BoardManager(playersQueue, moveSupervisor, board);
        boardManager.runGame();
    }
}
