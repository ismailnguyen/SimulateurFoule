package ui.model;

import ui.model.square.ISquareType;

/**
 * Created by Ismail on 12-10-2015.
 */
public class Square {
    private SpecialPoint position;
    private ISquareType.Type type;

    public void setPosition(SpecialPoint _position){
        this.position = _position;
    }

    public SpecialPoint getPosition(){
        return this.position;
    }

    public void setType(ISquareType _type){
        this.type = _type.getType();
    }

    public ISquareType.Type getType(){
        return this.type;
    }
}
