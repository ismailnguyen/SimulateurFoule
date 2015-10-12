package ui.model.square;

/**
 * Created by Ismail on 12-10-2015.
 */
public class Wall implements ISquareType {
    @Override
    public Type getType() {
        return Type.Wall;
    }
}
