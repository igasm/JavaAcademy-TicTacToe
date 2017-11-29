package game.board;

import game.settings.BoardDimensions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardBuilder {
    private final BoardDimensions boardDimensions;


    public BoardBuilder(BoardDimensions boardDimensions) {
        this.boardDimensions = boardDimensions;
    }

    public List<Integer> viaList(){
        return IntStream.range(0, boardDimensions.getElementsCount()).mapToObj(Integer::new).collect(Collectors.toList());
    }

    public Board viaBoard(){
        return new Board(viaList(), boardDimensions);
    }


}
