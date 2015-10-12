package utils;

import ui.model.Ground;
import ui.model.Square;
//Problem ???
//import ui.model.SpecialPoint;
import ui.model.square.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Ismail on 12-10-2015.
 */
public class MapUtil {
    public static Ground mapMaker(String file) throws FileNotFoundException {

        // Name of the ground will be part of string before file extension
        Ground ground = new Ground(file.substring(0, file.indexOf('.')));

        String ligne = null;
        ArrayList<String> ar = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(file));

        try {
            while (( ligne = br.readLine()) != null) {
                ar.add(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        ground.setSize(ar.size(), ar.get(0).length());

        Square[][] map = new Square[ground.getLength()][ground.getWidth()];

        for (int i = 0; i < ar.size(); i++)
            for (int j = 0; j < ar.get(i).length(); j++) {
                //Problem : won't find SpecialPoint class ???
                //ground.setSquare(new SpecialPoint(i, j), Square.getType(ar.get(i).charAt(j)));

                if (Square.getType(ar.get(i).charAt(j)) == new Departure())
                    map[i][j].setNbPeople(Integer.parseInt(ConfigUtil.getConfiguration("starters")));
            }

        return ground;
    }
}
