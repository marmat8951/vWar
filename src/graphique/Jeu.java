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

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import musiques.Musiques;
import virtualWar.Char;
import virtualWar.Plateau;
import virtualWar.Robot;

public class Jeu extends Application{
	long nombreDeTours = 0;
	Plateau p=new Plateau(VirtualWar.x,VirtualWar.y,20);
	int equipejouant;
	Musiques sons=new Musiques();
	Label descriptEquipeD=new Label();
	BorderPane root=new BorderPane();
	Label labelRobotEnCourt=new Label("");
	Label valeurX=new Label("0");
	Label valeurY=new Label("0");
	Label etatDuJeu=new Label("");
	Main affichage=new Main();
	ArrayList<Robot> robotsEquipeJouant=new ArrayList<Robot>();
	Robot robotEnCourt;
	Button buttonActionTirer=new Button("Tirer");
	Button buttonActionPieger=new Button("Pieger");
	Button buttonActionExploser=new Button("Exploser");
	Button buttonActionDeplacer=new Button("Déplacer");
	HBox robSuivEtPrec=new HBox();
	Button boutonSelecteurXplus=new Button("+");
	Button boutonSelecteurXmoins=new Button("-");
	Button boutonSelecteurYplus=new Button("+");
	Button boutonSelecteurYmoins=new Button("-");
	
	ListView<Robot> listeRobots= new ListView<Robot>();
	Button RobotSuivant=new Button("►");
	Button RobotPrecedent=new Button("◄");
	
	public void start(Stage stage) throws Exception {
		//AJOUT Event Handler pour la selection des + et -  
		boutonSelecteurXplus.addEventHandler(ActionEvent.ACTION, new ClicListenerInc(valeurX,Plateau.getXMAX()));
		boutonSelecteurYplus.addEventHandler(ActionEvent.ACTION, new ClicListenerInc(valeurY,Plateau.getYMAX()));
		boutonSelecteurYmoins.addEventHandler(ActionEvent.ACTION, new ClicListenerDec(valeurY));
		boutonSelecteurXmoins.addEventHandler(ActionEvent.ACTION, new ClicListenerDec(valeurX));
		//Ajout des bases
		p.ajoutBase(1, 0, 0);
		p.ajoutBase(2, Plateau.getXMAX()-1, Plateau.getYMAX()-1);
		//Ajout des robots
		p.ajoutChar(1, 0, 0,01);
		p.ajoutPiegeur(1, 0, 0,01);
		p.ajoutTireur(1, 0, 0,01);
		p.ajoutCatmikaze(1, 0, 0,01);
		//p.ajoutTireur(1, 2, 2, 02);
		//p.ajoutTireur(2, 0, 2,01);
		p.ajoutChar(2, Plateau.getXMAX()-1, Plateau.getYMAX()-1,01);
		p.ajoutPiegeur(2, Plateau.getXMAX()-1 , Plateau.getYMAX()-1,01);
		p.ajoutTireur(2, Plateau.getXMAX()-1, Plateau.getYMAX()-1,01);
		p.ajoutCatmikaze(2, Plateau.getXMAX()-1, Plateau.getYMAX()-1,01);
		//placement des boutons d'actions
		HBox hboxButtonsActions=new HBox();
		buttonActionTirer.setVisible(true);
		buttonActionPieger.setVisible(true);
		buttonActionExploser.setVisible(true);
		buttonActionExploser.setVisible(true);
		hboxButtonsActions.getChildren().addAll(buttonActionTirer,buttonActionPieger,buttonActionExploser,buttonActionDeplacer);
		etatDuJeu.setStyle("-fx-text-alignment: center;-fx-content-display: center");
		labelRobotEnCourt.setStyle("-fx-border-width: 2; -fx-border-style: solid; -fx-border-radius:5px; -fx-padding: 5px;");
		equipejouant=(int)(nombreDeTours%2)+1;
		robotsEquipeJouant=p.getRobots(equipejouant);
		robotEnCourt=robotsEquipeJouant.get(0);
		selecteurRobotEnCourt selectRECP=new selecteurRobotEnCourt(robotEnCourt,robotsEquipeJouant, false,labelRobotEnCourt,buttonActionDeplacer,buttonActionTirer,buttonActionExploser,buttonActionPieger,p,root);
		selecteurRobotEnCourt selectRECS=new selecteurRobotEnCourt(robotEnCourt,robotsEquipeJouant, true,labelRobotEnCourt,buttonActionDeplacer,buttonActionTirer,buttonActionExploser,buttonActionPieger, p,root);
		RobotSuivant.addEventHandler(ActionEvent.ACTION, selectRECS );
		RobotPrecedent.addEventHandler(ActionEvent.ACTION, selectRECP);
		VBox coteDroit=new VBox();
		Button nextTeam=new Button("Fin de tour");
		nextTeam.setVisible(true);
		coteDroit.getChildren().add(nextTeam);
		coteDroit.getChildren().add(new Label(p.details(equipejouant)));
		root.setTop(etatDuJeu);
		etatDuJeu.setText("Début de la partie!");
		final MediaPlayer fond;
		
		
		fond=Musiques.getMediaPlayer("musique");
		fond.setAutoPlay(true);
		fond.play();
		
		nextTeam.addEventHandler(ActionEvent.ACTION, e ->{
			int robotsMorts=p.nettoyage();
			int fini=p.fini();
			RobotPrecedent.setVisible(true);
			RobotSuivant.setVisible(true);
			if(fini<0){
				/*
				RobotPrecedent.removeEventHandler(ActionEvent.ACTION,selectRECP);
				RobotSuivant.removeEventHandler(ActionEvent.ACTION, selectRECS );
				*/
				nombreDeTours++;
				ArrayList<Robot>anciensRobots=new ArrayList<Robot>();
				for(Robot r:robotsEquipeJouant){
					anciensRobots.add(r);
				}
				for(Robot r:anciensRobots){
					robotsEquipeJouant.remove(r);
				}
				equipejouant=(int)(nombreDeTours%2)+1;
				for(Robot r:p.getRobots(equipejouant)){
					robotsEquipeJouant.add(r);
					r.setPeutAgir(true);
					if(p.estSurBase(r)){
						r.regen();
					}
				}
				robotEnCourt=robotsEquipeJouant.get(0);
				labelRobotEnCourt.setText(robotEnCourt.details()+" "+robotEnCourt.particularite());
				String style=labelRobotEnCourt.getStyle();
				if(equipejouant==1){
					style+=";-fx-border-color: blue";
				}else{
					style+=";-fx-border-color: red";
				}
				labelRobotEnCourt.setStyle(style);
				coteDroit.getChildren().clear();
				coteDroit.getChildren().add(nextTeam);
				coteDroit.getChildren().add(new Label(p.details(equipejouant)));
				descriptEquipeD.setText(p.details(equipejouant));
				Node affichePlateau=affichage.affichePlateau(p);
				Node resAfficheUnite=affichage.AffichageUnite(p, equipejouant);
				affichage.generationBoutonsActions(robotEnCourt,buttonActionTirer,buttonActionPieger,buttonActionExploser,buttonActionDeplacer,p);
				resAfficheUnite.maxWidth(root.getWidth());
				robotEnCourt=robotsEquipeJouant.get(0);
				coteDroit.getChildren().add(labelRobotEnCourt);
				coteDroit.getChildren().add(hboxButtonsActions);
				
				coteDroit.getChildren().add(RobotPrecedent);
				coteDroit.getChildren().add(RobotSuivant);
				
				root.setBottom(resAfficheUnite);
				root.setRight(coteDroit);
				root.setCenter(affichePlateau);
			}else {
				Label messageCentre;
				fond.stop();
				if(fini==0){
					messageCentre=new Label("FINI! Egalite!");
					Musiques.getMediaPlayer("draw").play();
				}else{
					messageCentre=new Label("FINI! Le joueur "+fini+" Gagne!");
					if(fini==1){
						Musiques.getMediaPlayer("t1").play();
					}else{
						Musiques.getMediaPlayer("t2").play();
					}
				}
				
				root.setCenter(messageCentre);
				root.setTop(new Label("Fini!"));
				root.setRight(new VBox());
				root.setBottom(new Label("GGWP"));
			}
			if(robotsMorts>0){
				try{
				MediaPlayer robotMort;
				robotMort=Musiques.getMediaPlayer("Dead");
				robotMort.play();
				}catch(Exception exp){
					System.err.println("Erreur au moment de la gestion du son");
					
				}
			}
			etatDuJeu.setText("Robots Morts ce tour: "+robotsMorts);

		});

		VBox coteDroitRobotEnCourt=new VBox();
		coteDroitRobotEnCourt.getChildren().add(labelRobotEnCourt);
		
		
		for(Node n:hboxButtonsActions.getChildren()){
			n.maxWidth(Double.MAX_VALUE);
			HBox.setMargin(n, new Insets(2,10,2,10));
		}
		buttonActionTirer.addEventHandler(ActionEvent.ACTION, e -> {
			buttonActionDeplacer.setVisible(false);
			buttonActionExploser.setVisible(false);
			buttonActionPieger.setVisible(false);
			buttonActionTirer.setVisible(false);
			RobotSuivant.setVisible(false);
			RobotPrecedent.setVisible(false);
			Robot robotTireur;
			
			for(Robot r:p.getRobots()){
				if(labelRobotEnCourt.getText().equalsIgnoreCase(r.details()+" "+r.particularite())){
					robotEnCourt=r;
				}
			}
			robotTireur=robotEnCourt;
			valeurX.setText(robotTireur.getX()+"");
			valeurY.setText(robotTireur.getY()+"");
			//GridPane selecteur=affichage.generateSelecteur(valeurX, valeurY,boutonSelecteurXmoins,boutonSelecteurXplus,boutonSelecteurYmoins,boutonSelecteurYplus, new tirSurLePlateau(valeurX,valeurY,robotTireur,p,etatDuJeu,RobotPrecedent,RobotSuivant));
			
			VBox selecteur=affichage.generateSelecteur(new tirSurLePlateau(robotTireur,p,etatDuJeu,RobotPrecedent,RobotSuivant,((Labeled) e.getTarget()).getText()),false);
			
			
			
			
			coteDroit.getChildren().add(selecteur);
			
			
			
			/*
			if(robotEnCours.tire(posx, posy)){
				p.robotEn(posx, posy).sePrendTir();
			}
			*/
		});
		
		buttonActionDeplacer.addEventHandler(ActionEvent.ACTION, e -> {
			
			buttonActionDeplacer.setVisible(false);
			buttonActionExploser.setVisible(false);
			buttonActionPieger.setVisible(false);
			buttonActionTirer.setVisible(false);
			RobotSuivant.setVisible(false);
			RobotPrecedent.setVisible(false);
			
			Robot robotDeplace;
			
			for(Robot r:p.getRobots(equipejouant)){
				String valeursDuRobot=labelRobotEnCourt.getText();
				valeursDuRobot+=r.getEquipe();
				valeursDuRobot+=r.getId();
				
				if(valeursDuRobot.equalsIgnoreCase(r.details()+" "+r.particularite()+r.getEquipe()+r.getId())){
					robotEnCourt=r;
				}
			}
			robotDeplace=robotEnCourt;
			valeurX.setText(robotDeplace.getX()+"");
			valeurY.setText(robotDeplace.getY()+"");
			boolean ischar=(robotDeplace instanceof Char);
			VBox selecteur=affichage.generateSelecteur(new deplacerRobot(robotDeplace,p,etatDuJeu,RobotPrecedent,RobotSuivant,((Labeled) e.getTarget()).getText()),!ischar);
			
			
			
			
			coteDroit.getChildren().add(selecteur);
			
			
			/*
			if(robotEnCours.tire(posx, posy)){
				p.robotEn(posx, posy).sePrendTir();
			}
			*/
		});
		buttonActionExploser.addEventHandler(ActionEvent.ACTION, e -> {
			buttonActionDeplacer.setVisible(false);
			buttonActionExploser.setVisible(false);
			buttonActionPieger.setVisible(false);
			buttonActionTirer.setVisible(false);
			RobotSuivant.setVisible(false);
			RobotPrecedent.setVisible(false);
			Robot robotKamikaze;
			for(Robot r:p.getRobots(equipejouant)){
				String valeursDuRobot=labelRobotEnCourt.getText();
				valeursDuRobot+=r.getEquipe();
				valeursDuRobot+=r.getId();
				
				if(valeursDuRobot.equalsIgnoreCase(r.details()+" "+r.particularite()+r.getEquipe()+r.getId())){
					robotEnCourt=r;
				}
			}
			robotKamikaze=robotEnCourt;
			

			
			//GridPane selecteur=affichage.generateSelecteur(valeurX, valeurY,boutonSelecteurXmoins,boutonSelecteurXplus,boutonSelecteurYmoins,boutonSelecteurYplus, new KamikazeSurPlateau(valeurX,valeurY,robotKamikaze,p,etatDuJeu,RobotPrecedent,RobotSuivant));
		    //coteDroit.getChildren().add(selecteur);
			
			VBox selecteur=affichage.generateSelecteur(new KamikazeSurPlateau(robotKamikaze,p,etatDuJeu,RobotPrecedent,RobotSuivant,((Labeled) e.getTarget()).getText()),true);
			coteDroit.getChildren().add(selecteur);
			/*
			if(robotEnCours.tire(posx, posy)){
				p.robotEn(posx, posy).sePrendTir();
			}
			*/
		});
		buttonActionPieger.addEventHandler(ActionEvent.ACTION, e -> {
			buttonActionDeplacer.setVisible(false);
			buttonActionExploser.setVisible(false);
			buttonActionPieger.setVisible(false);
			buttonActionTirer.setVisible(false);
			RobotSuivant.setVisible(false);
			RobotPrecedent.setVisible(false);
			Robot robotPiegeur;
			for(Robot r:p.getRobots(equipejouant)){
				String valeursDuRobot=labelRobotEnCourt.getText();
				valeursDuRobot+=r.getEquipe();
				valeursDuRobot+=r.getId();
				
				if(valeursDuRobot.equalsIgnoreCase(r.details()+" "+r.particularite()+r.getEquipe()+r.getId())){
					robotEnCourt=r;
				}
			}
			robotPiegeur=robotEnCourt;
			valeurX.setText(robotPiegeur.getX()+"");
			valeurY.setText(robotPiegeur.getY()+"");
			//GridPane selecteur=affichage.generateSelecteur(valeurX, valeurY,boutonSelecteurXmoins,boutonSelecteurXplus,boutonSelecteurYmoins,boutonSelecteurYplus ,new piegerRobot(valeurX,valeurY,robotPiegeur,p,etatDuJeu,RobotPrecedent,RobotSuivant));
			
			VBox selecteur=affichage.generateSelecteur(new piegerRobot(robotPiegeur,p,etatDuJeu,RobotPrecedent,RobotSuivant,((Labeled) e.getTarget()).getText()),true);
			coteDroit.getChildren().add(selecteur);
			/*
			if(robotEnCours.tire(posx, posy)){
				p.robotEn(posx, posy).sePrendTir();
			}
			*/
		});
		coteDroitRobotEnCourt.getChildren().add(hboxButtonsActions);
		coteDroit.getChildren().add(coteDroitRobotEnCourt);
		coteDroit.getChildren().add(RobotPrecedent);
		coteDroit.getChildren().add(RobotSuivant);

		

		root.setCenter(affichage.affichePlateau(p));
		root.setBottom(affichage.AffichageUnite(p, equipejouant));
		VBox.setMargin(nextTeam, new Insets(15));
		root.setRight(coteDroit);
		Scene scene = new Scene(root);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("Hey! Appuyez sur Echap pour quitter le mode plein Ecran!");
		stage.setTitle("VirtualWar");
		stage.setScene(scene);
		stage.show();

	}


	
}
