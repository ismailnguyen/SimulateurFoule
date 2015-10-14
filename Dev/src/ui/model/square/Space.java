package ui.model.square;

/**
 * Created by Ismail on 12-10-2015.
 */
public class Space implements ISquare {
    @Override
    public Type getType() {
        return Type.Space;
    }

    @Override
    public String toString() {
        return "S";
    }
}
