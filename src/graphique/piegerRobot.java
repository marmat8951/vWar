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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import musiques.Musiques;
import virtualWar.Coordonnee;
import virtualWar.Direction;
import virtualWar.Piegeur;
import virtualWar.Plateau;
import virtualWar.Robot;

public class piegerRobot implements EventHandler {
	

	/**
	 * 
	 * @return le robot tireur en cours
	 */
	public Robot getrobotPiegeur() {
		return robotPiegeur;
	}

	/**
	 * 
	 * @param robotPiegeur
	 * change le robot en cours par celui en paramètre
	 */
	public void setrobotPiegeur(Robot robotPiegeur) {
		this.robotPiegeur = robotPiegeur;
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
	private Robot robotPiegeur;
	private Plateau p;
	private Label afficheur;
	Button robotPrecedent,robotSuivant;
	
	/**
	 * 
	 * @param valeurX
	 * @param valeurY
	 * @param robotEnCourt
	 * @param p
	 * @param etatDuJeu
	 * @param robotPrecedent
	 * @param robotSuivant
	 */
	public piegerRobot(Robot robotEnCourt, Plateau p,Label etatDuJeu, Button robotPrecedent, Button robotSuivant, String directAction) {
		this.p=p;
		this.robotPiegeur=robotEnCourt;
		afficheur=etatDuJeu;
		this.robotPrecedent=robotPrecedent;
		this.robotSuivant=robotSuivant;
	}

	@Override
	/**
	 * Gère les phases de jeu
	 */
	public void handle(Event event) {
		Button source;
		source=(Button)event.getSource();
		VBox parent=(VBox)source.getParent().getParent();
		parent.getChildren().clear();
		parent.setVisible(false);
		parent.setMaxSize(0,0);
		parent.setPrefSize(0, 0);
		if(robotPiegeur!=null){
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
		int posX=robotPiegeur.getX()+(coord.getHauteur());
		int posY=robotPiegeur.getY()+(coord.getLargeur());
		if(!robotPiegeur.peutTirer(posX, posY)){
			afficheur.setText("Le robot n'a pas pu poser une mine en "+posX+","+posY);
			robotPiegeur.setPeutAgir(true);
		}else{
			Piegeur pi=(Piegeur)robotPiegeur;
			if(pi.getNombreMine()<=0){
				robotPiegeur.setPeutAgir(true);
				afficheur.setText("Plus de mine! rentrez a la base pour refaire le plein");
			}else if(p.caseOccupe(posX, posY)){
				robotPiegeur.setPeutAgir(true);
				afficheur.setText("Impossible de poser une mine ici: la case est occupée");
			}else{
			
			
			Musiques.getMediaPlayer("Mine Pose").play();
			afficheur.setText("La pose de mine en "+posX+","+posY+" a été un succes!");
			pi.setPeutAgir(false);
			p.ajoutMine(pi.getEquipe(), posX, posY);
			pi.setNombreMine(pi.getNombreMine()-1);
			}
		}
	
		

	}else{
		afficheur.setText("Robot inconnu ...");
	}
		robotPrecedent.setVisible(true);
		robotSuivant.setVisible(true);
		source.setVisible(false);
		source.setMaxSize(0, 0);
		source.setPrefSize(0, 0);
	}
}
