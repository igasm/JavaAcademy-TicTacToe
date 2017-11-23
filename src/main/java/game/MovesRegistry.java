package game;

import java.util.*;

public class MovesRegistry {

    private Map<Integer, MarkType> moves = new HashMap<>();

    void addMove(int fieldNumber, MarkType mark){
        moves.put(fieldNumber, mark);
    }

    public MarkType getMove(int fieldNumber) {
        return moves.get(fieldNumber);
    }

    public boolean moveExists(int fieldNumber){
        return moves.containsKey(fieldNumber);
    }

    void clear(){
        moves.clear();
    }

    int movesCount(){
        return moves.size();
    }
}
