package settings;

public class BoardDimensions {

    private final int width;
    private final int height;


    public BoardDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getElementsCount(){
        return width * height;
    }

    public int getMinDimension(){
        if(width <= height){
            return width;
        }else {
            return height;
        }
    }
}
