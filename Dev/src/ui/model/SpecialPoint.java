package ui.model;

/**
 * Created by Ismail on 09-10-2015.
 */
public class SpecialPoint {
    private Integer x;
    private Integer y;

    public SpecialPoint(Integer _x, Integer _y)
    {
        x = _x;
        y = _y;
    }

    public SpecialPoint()
    {
        x = 0;
        y = 0;
    }

    public Integer getY() {
        return y;
    }

    public Integer getX() {
        return x;
    }

    public void setY(Integer _y) {
        y = _y;
    }

    public void setX(Integer _x) {
        x = _x;
    }

    /***
     *
     * @return [x, y]
     */
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(this.x);
        sb.append(", ");
        sb.append(this.y);
        sb.append("]");

        return sb.toString();
    }
}
