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
import javafx.scene.*;
import virtualWar.Coordonnee;
import virtualWar.Direction;
import virtualWar.Plateau;
import virtualWar.Robot;

public class deplacerRobot implements EventHandler {

	/**
	 * 
	 * @return le robot à déplacer
	 */
	public Robot getrobotADeplacer() {
		return robotADeplacer;
	}

	/**
	 * 
	 * @param robotADeplacer
	 */
	public void setrobotADeplacer(Robot robotADeplacer) {
		this.robotADeplacer = robotADeplacer;
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
	 * @param p le plateau
	 */
	public void setP(Plateau p) {
		this.p = p;
	}

	/**
	 * 
	 * @return afficheur
	 */
	public Label getAfficheur() {
		return afficheur;
	}

	/**
	 * 
	 * @param afficheur
	 */
	public void setAfficheur(Label afficheur) {
		this.afficheur = afficheur;
	}

	private Robot robotADeplacer;
	private Plateau p;
	private Label afficheur;
	Button robotPrecedent,robotSuivant;
	/**
	 * constructeur de déplacerRobot
	 * @param robotEnCourt le robot actuel
	 * @param p le plateau de jeu
	 * @param etatDuJeu le message qui apparait en haut à gauche de l'écran pour indiquer un evenement 
	 * @param robotPrecedent le bouton pour passer au robot précédent
	 * @param robotSuivant le bouton pour passer au robot suivant
	 */
	public deplacerRobot(Robot robotEnCourt, Plateau p,Label etatDuJeu, Button robotPrecedent, Button robotSuivant, String directAction) {
		this.p=p;
		this.robotADeplacer=robotEnCourt;
		this.robotPrecedent=robotPrecedent;
		this.robotSuivant=robotSuivant;
		afficheur=etatDuJeu;
	}
	
	private String messageDeplacement(int code,int posXdem,int posYdem){
		if(code==1) return "Le robot s'est déplacé d'une case vers "+posXdem+","+posYdem;
		else if(code==2)return "Le robot s'est déplacé de deux cases"+posXdem+","+posYdem;
		else if(code==3)return "Le robot s'est pris une mine en se déplaçant";
		else if(code==0)return "Impossible de se déplacer à la case :"+posXdem+","+posYdem;
		else if(code==-1)return "Coordonnée impossible a Atteindre :"+posXdem+","+posYdem;
		else if(code==-2)return "Coordonnée déja prise par un Robot :"+posXdem+","+posYdem;
		else if(code==-3)return "Coordonnée déja prise par une Base Adverse! "+posXdem+","+posYdem;
		else if(code==-4)return "Coordonnée déja prise par un obstacle! "+posXdem+","+posYdem;
		else{
			return "Inconnu";
		}
	}

	/**
	 * handler qui ,si le robot a réussi à se déplacer, affiche un message positif sinon affiche un message négatif et si le robot n'existe
	 * pas il ne fait rien
	 */
	@Override
	public void handle(Event event) {
		if(robotADeplacer!=null){
			Button source;
			source=(Button)event.getSource();
			/*
			 * Ancien code, laissé la au cas où.
<<<<<<< HEAD
			source.setVisible(false);
			source.setMaxSize(0, 0);
			source.setPrefSize(0, 0);
			int posX=Integer.parseInt(x.getText());
			int posY=Integer.parseInt(y.getText());
			int resDepl=p.deplacerAllInt(robotADeplacer, posX, posY);
			if(resDepl==1 || resDepl==2){
				afficheur.setText("Le Robot s'est déplacé de "+resDepl+" cases");
				robotADeplacer.setPeutAgir(false);
			}else if(resDepl==3){
				afficheur.setText("Le Robot s'est déplacé, mais s'est pris une mine!");
=======
*/
			VBox parent=(VBox)source.getParent().getParent();
			parent.getChildren().clear();
			parent.setVisible(false);
			parent.setMaxSize(0,0);
			parent.setPrefSize(0, 0);
			
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
			/*
			 * debug:
			System.out.println("Robot en cours: "+robotADeplacer.details());
			System.out.println("Touche enfonce: "+choix);
			System.out.println("Coord GetHauteur" +coord.getHauteur()+" coord GetLargeur" +coord.getLargeur());
			System.out.println("Valeur deplacementmax du Robot:"+robotADeplacer.getDeplacement());
			*/
			int posX=robotADeplacer.getX()+(coord.getHauteur()*robotADeplacer.getDeplacement());
			int posY=robotADeplacer.getY()+(coord.getLargeur()*robotADeplacer.getDeplacement());
			/* debug:
			System.out.println(posX);
			System.out.println(posY);
			System.out.println(robotADeplacer.details());
			*/
			int code=p.deplacerAllInt(robotADeplacer, posX, posY);
			
			if (code>0){
				robotADeplacer.setPeutAgir(false);
			}else{
				robotADeplacer.setPeutAgir(true);
			}
			afficheur.setText(messageDeplacement(code, posX, posY));
			
		}else{
			afficheur.setText("Robot inconnu ...");
		}
		System.out.println(robotADeplacer.details());
		robotPrecedent.setVisible(true);
		robotSuivant.setVisible(true);
	}
}
