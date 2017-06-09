/*******************************************************************************
 * Copyright (C) 2017 Marmat8951, A. DM. , A. B. , E. M., J. R.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package graphique;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.VBox;
import musiques.Musiques;
import virtualWar.Char;
import virtualWar.Coordonnee;
import virtualWar.Direction;
import virtualWar.Element;
import virtualWar.Plateau;
import virtualWar.Robot;

public class tirSurLePlateau implements EventHandler {

	/**
	 * 
	 * @return le robot de type Tireur en cours
	 */
	public Robot getRobotTireur() {
		return robotTireur;
	}

	/**
	 * 
	 * @param robotTireur
	 * Change le robot en cours pour le robot en paramètre
	 */
	public void setRobotTireur(Robot robotTireur) {
		this.robotTireur = robotTireur;
	}

	/**
	 * 
	 * @return le plateau
	 */
	public Plateau getP() {
		return p;
	}

	/**
	 * 
	 * @param p
	 *  change le plateau en cours par le plateau en paramètre
	 */
	public void setP(Plateau p) {
		this.p = p;
	}

	/**
	 * 
	 * @return l'afficheur d'où en est le jeu (le résultat de la dernière action en cours)
	 */
	public Label getAfficheur() {
		return afficheur;
	}

	/**
	 * 
	 * @param afficheur
	 * Change l'afficheur en cours par celui en paramètre
	 */
	public void setAfficheur(Label afficheur) {
		this.afficheur = afficheur;
	}
	private Robot robotTireur;
	private Plateau p;
	private Label afficheur;
	Button robotPrecedent,robotSuivant;

	/**
	 * Constructeur
	 * @param valeurX
	 * @param valeurY
	 * @param robotEnCourt
	 * @param p
	 * @param etatDuJeu
	 * @param robotPrecedent
	 * @param robotSuivant
	 */
	public tirSurLePlateau(Robot robotEnCourt, Plateau p,Label etatDuJeu, Button robotPrecedent, Button robotSuivant,String directAction) {
		this.p=p;
		this.robotTireur=robotEnCourt;
		afficheur=etatDuJeu;
		this.robotPrecedent=robotPrecedent;
		this.robotSuivant=robotSuivant;
	}

	@Override
	/**
	 * Gère les phases de jeu
	 */
	public void handle(Event event) {


		if(robotTireur!=null){
			Button source;
			source=(Button)event.getSource();
			VBox parent=(VBox)source.getParent().getParent();
			parent.getChildren().clear();
			parent.setVisible(false);
			parent.setMaxSize(0,0);
			parent.setPrefSize(0, 0);
			String choix=((Labeled) event.getTarget()).getText();
			Direction dir;
			switch(choix){
				case "↑":
					dir=Direction.H;
				break;
				
				case "↓":
					dir=Direction.B;
				break;
				
				case "←":
					dir=Direction.G;

				break;
				case "↖":
					dir=Direction.HG;
				break;
				case "↗":
					dir=Direction.HD;

				break;
				case "↙":
					dir=Direction.BG;
	
				break;
				case "↘":
					dir=Direction.BD;

				break;
				case "→":
					dir=Direction.D;
				break;
				default :
					dir=Direction.CTR;
				break;
			}
			if(dir!=Direction.CTR){
			System.out.println(robotTireur.toString());
			Musiques musiques=new Musiques();
			Element touche=p.prochainElementDansUneDirectionNonMineAvecPortee(robotTireur, dir);
			if(robotTireur instanceof Char){
				Musiques.getMediaPlayer("Char Tir").play();
			}else{
				Musiques.getMediaPlayer("Tireur Tir").play();
			}
			if(touche==null){
				robotTireur.setPeutAgir(false);
				afficheur.setText("Dommage, aucun robot touchable dans la Direction "+choix);
			}else{
				if(touche.isObstacle()){
					afficheur.setText("Dommage, vous avez tiré sur un obstacle, en "+touche.getX()+","+touche.getY());
				}else if(touche.isBase()){
					afficheur.setText("Vous avez tiré sur une base, les robots à l'interieur sont protégés");
				}else{
					Robot rTouche=(Robot)touche;
					afficheur.setText("Vous avez touché un Robot: "+rTouche.details());
				}
			}
			source=(Button)event.getSource();
			source.setVisible(false);
			source.setPrefSize(0, 0);
			source.setMaxSize(0, 0);
			}else{
				afficheur.setText("annulation!");
				robotTireur.setPeutAgir(true);
			}


		}else{
			afficheur.setText("Robot inconnu ...");
		}
		robotPrecedent.setVisible(true);
		robotSuivant.setVisible(true);
	}
}
