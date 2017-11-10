import com.sun.org.apache.xpath.internal.operations.String;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private final int width;
    private final int height;
    private List<Integer> board; //pola zawsze interfejsami!!!


    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        board = new ArrayList<Integer>(width*height); //-> to boardBuilder (?)
        board = IntStream.range(0,100).mapToObj(a->new Integer(a)).collect(Collectors.toList()); // -> populating to boardBuilder (?)
    }

}
