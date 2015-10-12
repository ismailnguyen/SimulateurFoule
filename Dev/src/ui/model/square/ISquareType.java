package ui.model.square;

/**
 * Created by Ismail on 12-10-2015.
 */
public interface ISquareType {
    public enum Type {
        Wall,
        Grass,
        Space,
        Departure,
        Arrival
    }

    public Type getType();
}
