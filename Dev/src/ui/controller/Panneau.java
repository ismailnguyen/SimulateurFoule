/**
 * Simulateur de foule
 * 
 * Kevin DHORNE, Fabien GAMELIN, Ismaïl NGUYEN
 * 
 * ESGI 2015/2016
 */

package ui.controller;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import ui.model.Ground;
import utils.ConfigUtil;

@SuppressWarnings("serial")
public class Panneau extends JPanel {

	private Ground ground;
	private Image tiles;
	
	public Panneau(Ground g) {
		ground = g;
		try {
			tiles = ImageIO.read(new File(ConfigUtil.getConfiguration("tiles")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension taille = new Dimension(ground.getWidth() * 26, ground.getlength() * 26);
		int im = taille.width / 26;
		int jm = taille.height / 26;

		for (int i = 0; i < im; i++) {
			for (int j = 0; j < jm; j++) {

				if (ground.ReadCase(j, i).getType() == ConfigUtil.getCharOf("wall")) {
					g.drawImage(tiles, 26 * i, 26 * j, 26 + (i * 26),
							26 + (j * 26), 0, 26, 26, 52, this);
				}

				if (ground.ReadCase(j, i).getType() == ConfigUtil.getCharOf("space")) {
					g.drawImage(tiles, 26 * i, 26 * j, 26 + (i * 26),
							26 + (j * 26), 0, 0, 26, 26, this);
				}
				
				if (ground.ReadCase(j, i).getType() == ConfigUtil.getCharOf("grass")) {
					g.drawImage(tiles, 26 * i, 26 * j, 26 + (i * 26),
							26 + (j * 26), 0, 52, 26, 78, this);
				}
				
				if (ground.ReadCase(j, i).getType() == ConfigUtil.getCharOf("departure")) {
					g.drawImage(tiles, 26 * i, 26 * j, 26 + (i * 26),
							26 + (j * 26), 0, 0, 26, 26, this);
					g.drawImage(tiles, 26 * i, 26 * j, 26 + (i * 26),
							26 + (j * 26), 0, 78, 26, 104, this);
				}
				
				if (ground.ReadCase(j, i).getType() == ConfigUtil.getCharOf("arrival")) {
					g.drawImage(tiles, 26 * i, 26 * j, 26 + (i * 26),
							26 + (j * 26), 0, 0, 26, 26, this);
					g.drawImage(tiles, 26 * i, 26 * j, 26 + (i * 26),
							26 + (j * 26), 0, 130, 26, 156, this);
				}
				
				if (ground.ReadCase(j, i).getMouse() != null) {
					g.drawImage(tiles, 26 * i, 26 * j, 26 + (i * 26),
							26 + (j * 26), 0, 104, 26, 130, this);
				}
			}
		}
	}

	public void setGround(Ground g) {
		ground = g;
	}
	
	public Image getImg(){
		return tiles;
	}
}