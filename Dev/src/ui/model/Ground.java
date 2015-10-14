package ui.model;

import ui.model.square.ISquare;

/**
 * Created by Ismail on 09-10-2015.
 */
public class Ground {
    private String name;
    private Square[][] map;
    private Integer length;
    private Integer width;

    public Ground(String _name)
    {
        this.name = _name;
        this.map = new Square[][]{};
    }

    public Square[][] getMap() {
        return map;
    }

    public void setMap(Square[][] _map) {
        this.map = _map;
    }

    public Integer getLength(){
        return this.length;
    }

    public void setLength(Integer _length){
        this.length = _length;
    }

    public Integer getWidth(){
        return this.width;
    }

    public void setWidth(Integer _width){
        this.width = _width;
    }

    public void setSize(Integer _length, Integer _width){
        this.length = _length;
        this.width = _width;
    }

    public Square getSquare(SpecialPoint _position) {
        return this.map[_position.getX()][_position.getY()];
    }

    public void setSquare(SpecialPoint _position, ISquare _type){
        Square square = new Square();
        square.setPosition(_position);
        square.setType(_type);

        this.map[_position.getX()][_position.getY()] = square;
    }
}
