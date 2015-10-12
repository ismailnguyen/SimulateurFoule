package ui.model;

import utils.MapUtil;

/**
 * Created by Ismail on 09-10-2015.
 */
public class Ground {
    private Square[][] ground;
    private Integer length;
    private Integer width;

    public Ground(Square[][] map)
    {
        this.ground = map;
    }

    public Square[][] getGround() {
        return ground;
    }

    public void setGround(Square[][] g) {
        this.ground = g;
    }
}
