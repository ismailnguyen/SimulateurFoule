package ui.model.square;

/**
 * Created by Ismail on 12-10-2015.
 */
public class Departure implements ISquare {
    @Override
    public Type getType() {
        return Type.Departure;
    }

    @Override
    public String toString() {
        return "D";
    }
}
