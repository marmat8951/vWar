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
import virtualWar.Coordonnee;
import virtualWar.Direction;
import virtualWar.Plateau;
import virtualWar.Robot;

public class KamikazeSurPlateau implements EventHandler {

	/**
	 * 
	 * @return le robot Catmikaze en cours
	 */
	public Robot getrobotKamikaze() {
		return robotKamikaze;
	}

	/**
	 * 
	 * @param robotKamikaze 
	 * change le robot en cours par celui en paramètre
	 */
	public void setrobotKamikaze(Robot robotKamikaze) {
		this.robotKamikaze = robotKamikaze;
	}

	/**
	 * 
	 * @return le plateau actuel
	 */
	public Plateau getP() {
		return p;
	}

	/**
	 * 
	 * @param p change le plateau pour celui en paramètre
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
	private Label x;
	private Label y;
	private Robot robotKamikaze;
	private Plateau p;
	private Label afficheur;
	Button robotPrecedent,robotSuivant;
	/**
	 * 
	 * @param valeurX le label coordonnée X
	 * @param valeurY le label coordonnée Y
	 * @param robotEnCourt le robot en actuel
	 * @param p le plateau de jeu
	 * @param etatDuJeu le message qui apparait en haut à gauche de l'écran pour indiquer un evenement 
	 * @param robotPrecedent le bouton pour passer au robot précédent
	 * @param robotSuivant le bouton pour passer au robot suivant
	 */
	public KamikazeSurPlateau(Robot robotEnCourt, Plateau p,Label etatDuJeu, Button robotPrecedent, Button robotSuivant, String directAction) {
		this.p=p;
		this.robotKamikaze=robotEnCourt;
		afficheur=etatDuJeu;
		this.robotPrecedent=robotPrecedent;
		this.robotSuivant=robotSuivant;
	}

	/**
	 * handler qui affiche un message :
	 * 	-Le robot n'a pas pu tirer
	 * 	-Le robot a réussi son tir
	 * Si le tir a été un succès :
	 * 	-Robot touché
	 * 	-Pas de robot ici  
	 */
	@Override
	public void handle(Event event) {
		Button source;
		source=(Button)event.getSource();
		VBox parent=(VBox)source.getParent().getParent();
		parent.getChildren().clear();
		parent.setVisible(false);
		parent.setMaxSize(0,0);
		parent.setPrefSize(0, 0);
		if(robotKamikaze!=null){
			String choix=((Labeled) event.getTarget()).getText();
			Coordonnee coord=new Coordonnee(0,0);
			Direction dir;
			switch(choix){
			case "↑":
				dir=Direction.H;
				coord=dir.getDirection(dir);
				break;

			case "↓":
				dir=Direction.B;
				coord=dir.getDirection(dir);
				break;

			case "←":
				dir=Direction.G;
				coord=dir.getDirection(dir);
				break;
			case "↖":
				dir=Direction.HG;
				coord=dir.getDirection(dir);
				break;
			case "↗":
				dir=Direction.HD;
				coord=dir.getDirection(dir);
				break;
			case "↙":
				dir=Direction.BG;
				coord=dir.getDirection(dir);
				break;
			case "↘":
				dir=Direction.BD;
				coord=dir.getDirection(dir);
				break;
			case "→":
				dir=Direction.D;
				coord=dir.getDirection(dir);
				break;
			}
			int tirX=robotKamikaze.getX()+(coord.getHauteur());
			int tirY=robotKamikaze.getY()+(coord.getLargeur());
			if(!robotKamikaze.tire(tirX,tirY)){
				afficheur.setText("Le robot n'a pas pu tirer en "+tirX+","+tirY);
				robotKamikaze.setPeutAgir(true);
			}else{
				afficheur.setText("Le tir en "+tirX+","+tirY+" a ete un succes!");
				Musiques.getMediaPlayer("catmikazeExplo").play();
				robotKamikaze.setPeutAgir(false);
				if(p.robotEn(tirX, tirY)!=null){
					p.robotEn(tirX, tirY).setEnergie(0);
					afficheur.setText(afficheur.getText()+" Robot Touché ! RIP! Caractéristiques: \n"+p.robotEn(tirX, tirY).details());
				}else{
					afficheur.setText(afficheur.getText()+"Dommage! Pas de robot aux coordonnées"+tirX+","+tirY+" Il faut pas se faire peter pour rien...");
				}
			}
		}else{
			afficheur.setText("Robot inconnu ...");
		}
		robotPrecedent.setVisible(true);
		robotSuivant.setVisible(true);
	}
}

