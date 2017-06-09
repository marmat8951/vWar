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

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import virtualWar.Plateau;
import virtualWar.Robot;

public class selecteurRobotEnCourt implements EventHandler {
	ArrayList<Robot> listeRobots;
	Robot current;
	boolean dir;
	Label affichage;
	Main afficheur;
	Plateau p;
	BorderPane racine;
	Button buttonActionTirer, buttonActionPieger, buttonActionExploser, buttonActionDeplacer;
	selecteurRobotEnCourt(Robot r, ArrayList<Robot> liste,boolean direction,Label affichageRobotEnCourt, Button buttonActionDeplacer, Button buttonActionTirer, Button buttonActionExploser, Button buttonActionPieger,Plateau p, BorderPane root){
		listeRobots=liste;
		racine=root;
		this.p=p;
		current=r;
		dir=direction;
		affichage=affichageRobotEnCourt;
		afficheur=new Main();
		afficheur.generationBoutonsActions(current, buttonActionTirer, buttonActionPieger, buttonActionExploser, buttonActionDeplacer,p);
		this.buttonActionDeplacer=buttonActionDeplacer;
		this.buttonActionExploser=buttonActionExploser;
		this.buttonActionPieger=buttonActionPieger;
		this.buttonActionTirer=buttonActionTirer;
	}
	@Override
	public void handle(Event event) {
		int indexActuel=listeRobots.indexOf(current);
		if(listeRobots.size()!=0){
			if(indexActuel==-1){
				current=listeRobots.get(0);
			}else{
				if(dir){
					if(indexActuel+1>=listeRobots.size()){
						current=listeRobots.get(0);
					}else{
						current=listeRobots.get(indexActuel+1);
					}
				}else{
					if(indexActuel-1<0){
						current=listeRobots.get(listeRobots.size()-1);
					}else{
						current=listeRobots.get(indexActuel-1);
					}
				}

			}
			afficheur.generationBoutonsActions(current, buttonActionTirer, buttonActionPieger, buttonActionExploser, buttonActionDeplacer,p);
			affichage.setText(current.details()+" "+current.particularite());
			String style=affichage.getStyle();
			if(current.getEquipe()==1){
				style+=";-fx-border-color: blue";
			}else{
				style+=";-fx-border-color: red";
			}
			affichage.setStyle(style);
			racine.setBottom(afficheur.AffichageUnite(p, current.getEquipe()));
		}else{
			affichage.setText("Aucun robot");
		}
		racine.setCenter(afficheur.affichePlateau(p));
		
	}


}
