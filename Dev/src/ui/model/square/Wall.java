package ui.model.square;

/**
 * Created by Ismail on 12-10-2015.
 */
public class Wall implements ISquare {
    @Override
    public Type getType() {
        return Type.Wall;
    }

    @Override
    public String toString() {
        return "W";
    }
}
