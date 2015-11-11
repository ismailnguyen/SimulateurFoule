/**
 * Simulateur de foule
 * 
 * Kevin DHORNE, Fabien GAMELIN, Ismaïl NGUYEN
 * 
 * ESGI 2015/2016
 */

package ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import ui.controller.Panneau;
import ui.model.Ground;
import utils.ConfigUtil;

@SuppressWarnings("serial")
public class MaFenetre extends JFrame {

	private JComponent panel;
	private JPanel container;
	private JPanel ground;
	private JPanel panelInformations;
	private JPanel panelGameOption;
	private JButton begin;
	private JLabel round;
	private JLabel move;
	private JLabel inMove;
	private JLabel arrive;
	public JLabel porte;
	private JLabel speed;
	private List<JTextField> portes = new ArrayList<JTextField>();
	private Ground g;
	private Timer timer;
	private JTextField speedChoice = new JTextField();
	private int delay;
	private int greatNumbers = 0;
	private boolean letsplay = false;
	private boolean goodSpeedValue = false;

	public MaFenetre() {
		try {
			g = new Ground(ConfigUtil.getConfiguration("ground"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// Panneau principal
		panel = new Panneau(g);
		ground = new JPanel();
		ground.setLayout(new BorderLayout());
		ground.setPreferredSize(new Dimension((int) (g.getWidth() * 26), (int) (g.getlength() * 26)));
		ground.add(panel);

		begin = new JButton("LANCER");
		round = new JLabel("TOUR " + g.getTour(), SwingConstants.CENTER);
		move = new JLabel("DEPLACEMENTS " + g.getNbDeplacementsTotal(), SwingConstants.CENTER);
		inMove = new JLabel("SOURIS EN DEPLACEMENT " + (g.getSouris_sorties() - g.getScore()), SwingConstants.CENTER);
		arrive = new JLabel("SOURS ARRIVEES " + g.getScore(), SwingConstants.CENTER);

		panelGameOption = new JPanel();
		panelGameOption.setLayout(new GridLayout(1, 2));
		panelGameOption.setPreferredSize(new Dimension((int) (g.getWidth() * 26), 50));

		panelGameOption.add(new JLabel("PORTE 1", SwingConstants.CENTER));
		portes.add(new JTextField(Integer.toString(g.getCaseByType(ConfigUtil.getCharOf("departure")).get(0).getNb_souris_restant()), 3));
		panelGameOption.add(portes.get(0));
		
		panelGameOption.add(new JLabel("PORTE 2", SwingConstants.CENTER));
		portes.add(new JTextField(Integer.toString(g.getCaseByType(ConfigUtil.getCharOf("departure")).get(1).getNb_souris_restant()), 3));
		panelGameOption.add(portes.get(1));
			
		speedChoice.setText(ConfigUtil.getConfiguration("speed"));
		speed = new JLabel("VITESSE ", SwingConstants.CENTER);
		panelGameOption.add(speed);
		panelGameOption.add(speedChoice);
		panelGameOption.add(begin);

		panelInformations = new JPanel();
		panelInformations.setLayout(new GridLayout(1, 4));
		panelInformations.setPreferredSize(new Dimension((int) (g.getWidth() * 26), 50));
		panelInformations.add(move);
		panelInformations.add(inMove);
		panelInformations.add(arrive);
		panelInformations.add(round);

		container = new JPanel();
		container.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 4;
		container.add(ground, gbc);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 4;
		container.add(panelGameOption, gbc);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = 4;
		container.add(panelInformations, gbc);

		ActionListener action_lancer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning()) {
					begin.setText("Reprendre");
					timer.stop();
				} else {
					for (int i = 0; i < portes.size(); i++) {
						if (!java.util.regex.Pattern.matches("\\d+", portes
								.get(i).getText())) {
							JOptionPane.showMessageDialog(null,
									"Nombre de minions par porte incorrect !");
							greatNumbers--;
						} else {
							if (g.getTour() == 0) {
								g.getCaseByType(ConfigUtil.getCharOf("departure")).get(i).setNb_souris_restant(
										Integer.parseInt(portes.get(i)
												.getText()));
								portes.get(i).setEditable(false);
							}
							
							greatNumbers++;
						}

						if (greatNumbers == portes.size())
							goodSpeedValue = true;
					}
					if (!java.util.regex.Pattern.matches("\\d+",
							speedChoice.getText())) {
						JOptionPane.showMessageDialog(null,
								"Vitesse (ms) incorrect !");
					} else {
						letsplay = true;
						timer.setDelay(Integer.parseInt(speedChoice.getText()));
					}
					if (letsplay && goodSpeedValue) {
						timer.start();
						begin.setText("Pause");
					}
				}
			}
		};

		begin.addActionListener(action_lancer);

		ActionListener tache = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.tourSuivant();
				round.setText("TOUR : " + g.getTour());
				move.setText("DEPLACEMENTS " + g.getNbDeplacementsTotal());
				inMove.setText("SOURIS EN DEPLACEMENT " + (g.getSouris_sorties() - g.getScore()));
				arrive.setText("SOURS ARRIVEES " + g.getScore());
				panel.repaint();

				 if (g.getNbSourisSurMap() == 0)
				 { // Fini
					 timer.stop();
					 try {
						 g = new Ground(ConfigUtil.getConfiguration("ground"));
						 ((Panneau) panel).setGround(g);
						 begin.setText("Recommencer");
						 for (int i = 0; i < portes.size(); i++) {
							portes.get(i).setEditable(true);
						}
						 panel.repaint();
					 } catch (FileNotFoundException e1) {
						 e1.printStackTrace();
					 } // On réinitialise la map
				 }
			}
		};
		timer = new Timer(delay, tache);
		
		this.setTitle(ConfigUtil.getConfiguration("title"));
		
		this.setSize(1265, 700);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(container);
		this.setResizable(false);
		this.setVisible(true);
	}

}