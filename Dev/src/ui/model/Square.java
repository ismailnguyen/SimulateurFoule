package ui.model;

import ui.model.square.*;

/**
 * Created by Ismail on 12-10-2015.
 */
public class Square {
    private SpecialPoint position;
    private ISquare.Type type;
    private Integer nbPeople;

    public Square(){
        this.position = new SpecialPoint();
        this.type = ISquare.Type.Space;
    }

    public void setPosition(SpecialPoint _position){
        this.position = _position;
    }

    public SpecialPoint getPosition(){
        return this.position;
    }

    public void setType(ISquare _type){
        this.type = _type.getType();
    }

    public ISquare.Type getType(){
        return this.type;
    }

    public static ISquare getType(char c)
    {
        switch (c)
        {
            case '*': return new Wall();
            case 'G': return new Grass();
            case 'D': return new Departure();
            case 'A': return new Arrival();
            case ' ':
            default: return new Space();
        }
    }

    public Integer getNbPeople() {
        return nbPeople;
    }

    public void setNbPeople(Integer _nbPeople) {
        this.nbPeople = _nbPeople;
    }
}
