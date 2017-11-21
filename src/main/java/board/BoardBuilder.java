package board;

import settings.BoardDimensions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardBuilder {
    private final BoardDimensions boardDimensions;


    public BoardBuilder(BoardDimensions boardDimensions) {
        this.boardDimensions = boardDimensions;
    }

    public List<Integer> viaList(){
        List<Integer> board = IntStream.range(0, boardDimensions.getElementsCount()).mapToObj(a->new Integer(a)).collect(Collectors.toList());
        return board;
    }

    public Board viaBoard(){
        return new Board(viaList(), boardDimensions);
    }


}
