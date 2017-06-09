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
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import virtualWar.Actions;
import virtualWar.Base;
import virtualWar.Mine;
import virtualWar.Plateau;
import virtualWar.Robot;


public class Main extends Application{


	@Override
	public void start(Stage stage) throws Exception {
		VBox root=new VBox();

		//Affichage 1
		//Canvas canvas = new Canvas (300, 300);
		//GraphicsContext gc = canvas.getGraphicsContext2D();

		/*Plateau p=new Plateau(10,10);
		p.addBase(new Base(1,1,6));
		p.addBase(new Base(2,2,6));
		p.ajoutChar(1, 0, 0,01);
		p.ajoutPiegeur(1, 0, 0,01);
		p.ajoutTireur(1, 0, 0,01);
		p.ajoutCatmikaze(1, 0, 0,01);
		root.getChildren().add(affichePlateau(p));
*/

		
		// Affichage 2
		Plateau p=new Plateau(20,20);
		p.ajoutChar(1, 0, 0,01);
		p.ajoutPiegeur(1, 0, 0,01);
		p.ajoutTireur(1, 0, 0,01);
		p.ajoutCatmikaze(1, 0, 0,01);
		root.getChildren().add(AffichageUnite(p,1));
		 
		Scene scene = new Scene(root);	
		stage.setTitle("Virtual War");
		stage.setScene(scene);
		stage.show();

	}

	 public class actionEventClickBouttonSelectionDirection implements EventHandler<ActionEvent> {
		Robot r;
		Plateau p;
		
		actionEventClickBouttonSelectionDirection(){
			//p.deplacerAll(rob, rob.getX(), rob.getY()-rob.getDistanceDeplacement());
		}
		public void handle(ActionEvent event) {
			event.getSource();

		}
	}
	

	public void generationBoutonsActions(Robot r,Button buttonActionTirer,Button buttonActionPieger,Button buttonActionExploser, Button buttonActionDeplacer, Plateau p){
		buttonActionTirer.setVisible(false);
		buttonActionPieger.setVisible(false);
		buttonActionExploser.setVisible(false);
		buttonActionDeplacer.setVisible(false);
		if(r!=null){
			if(r.isPeutAgir()&& r.getEnergie()>0){
				ArrayList<Actions> actionsduRobot=r.getActions();

				if(actionsduRobot.contains(Actions.DEPLACER)){
					buttonActionDeplacer.setVisible(true);
				}
				if(!p.estSurBase(r)){
					if(actionsduRobot.contains(Actions.EXPLOSER)){
						buttonActionExploser.setVisible(true);
					}
					if(actionsduRobot.contains(Actions.PIEGER)){
						buttonActionPieger.setVisible(true);
					}
					if(actionsduRobot.contains(Actions.TIRER)){
						buttonActionTirer.setVisible(true);
					}
				}
			}

		}else{

		}
	}
	public Node affichePlateau(Plateau p){
		GridPane root=new GridPane();
		Image imgVide=new Image("file:./images2/Sol.png");
		ImageView[][] images=new ImageView[Plateau.getXMAX()][Plateau.getYMAX()];
		for(int colone=0;colone<Plateau.getYMAX();colone++){
			for(int ligne=0;ligne<Plateau.getXMAX();ligne++){
				virtualWar.Element e=p.elementEn(ligne, colone);
				ImageView imgV1=new ImageView();
				
				if(e!=null){
					imgV1.prefHeight(40);
					imgV1.prefWidth(40);
					imgV1.maxWidth(40);
					imgV1.maxHeight(40);
					imgV1.setImage(e.getSprite());
					/*if(e.getEquipe()!=1){
						ColorAdjust modif = new ColorAdjust();
						modif.setSaturation(-0.9);
						modif.setContrast(3);

						imgV1.setEffect(new SepiaTone(1));
					}
	*/
				}else{
					imgV1.setImage(imgVide);
				}
				
				images[ligne][colone]=imgV1;



			}
		}

		for(int i=0;i<images.length;i++){
			for(int j=0;j<images[0].length;j++){
				root.add(images[i][j], i, j);

			}

		}

		/*
		 * Adapter les cellules a un style
		for(Node n:root.getChildren()){

		}
		 */
		return root;
	}


	public Node AffichageUnite(Plateau p,int equipe){
		HBox root=new HBox();
		Label l;
		int ctp=0;
		ArrayList<Robot> robots=p.getRobots(equipe);
		Label lequipe=new Label("");
		lequipe.setText("Equipe:" +equipe+ " \nnombre de Robots: "+p.getRobots(equipe).size());
		lequipe.setMaxWidth(180);
		lequipe.setWrapText(true);
		String styleEquipe="-fx-border-width: 2; -fx-border-style: solid; -fx-border-radius:5px; -fx-padding: 5px";
		if(equipe==1){
			styleEquipe +=";-fx-border-color: blue";
		}else{
			styleEquipe +=";-fx-border-color: red";
		}
		lequipe.setStyle(styleEquipe);
		root.getChildren().add(lequipe);
		for(Robot r:robots){
			VBox vb=new VBox();
			l=new Label(r.details()+"\n");
			vb.getChildren().add(l);
			vb.setStyle("-fx-border-width: 5; -fx-border-color: black; -fx-border-style: solid; -fx-border-radius:3px; -fx-padding: 5px;");
			l.setMaxWidth(180);
			l.setWrapText(true);
			final Canvas canvas = new Canvas(100,20);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setFill(Color.BLACK);
			gc.fillRect(0,0,100,20);
			gc.setFill(Color.RED);
			gc.fillRect(3,3,94,14);
			gc.setFill(Color.GREEN);
			gc.fillRect(3,3,((double)r.getEnergie()/(double)r.getEnergieMax())*(double)94,14);
			vb.getChildren().add(canvas);
			root.getChildren().add(vb);	
			ctp++;
			HBox.setMargin(root.getChildren().get(ctp), new Insets(10));
			
		
		}
		
		
		ArrayList<Mine> mines = p.getMines(equipe);

		Label labelMine = new Label();
		
		String listeMines = "Mine :\n";
		for(Mine m : mines){
			listeMines += "[" + m.getX() +","+ m.getY() + "] ";
		}
		labelMine.setText(listeMines);
		if (!mines.isEmpty()){
		HBox.setMargin(labelMine, new Insets(10));
		labelMine.setStyle("-fx-border-width: 5; -fx-border-color: black; -fx-border-style: solid; -fx-border-radius:3px; -fx-padding: 5px;");
		labelMine.setMaxWidth(180);
		labelMine.setWrapText(true);
		root.getChildren().add(labelMine);
		}
				
		
			
			
		String styleRoot="-fx-border-width: 5; -fx-border-color: black; -fx-border-style: solid; -fx-border-radius:3px; -fx-padding: 5px;";
		if(equipe==1){
			styleRoot +=";-fx-border-color: blue";
		}else{
			styleRoot +=";-fx-border-color: red";
		}
		root.setStyle(styleRoot);
		return root;
	}

	
	public static void main(String[] args) {
		Application.launch(args);
	}

	/**
	 * 
	 * @param e EventHandler appelé à la presison de la touche
	 * @param diagVis Si les diagonales sont visibles (true) ou non (false)
	 * @return VBOX contenant les boutons.
	 */
	public VBox generateSelecteur(EventHandler e,boolean diagVis){
		
		VBox root=new VBox();
		HBox ssroot1=new HBox();
		HBox ssroot2=new HBox();
		HBox ssroot3=new HBox();
		Button HG=new Button("↖");
		Button H=new Button("↑");
		Button HD=new Button("↗");
		Button D=new Button("→");
		Button G=new Button("←");
		Button CTR=new Button(" . ");
		Button BG=new Button("↙");
		Button B=new Button("↓");
		Button BD=new Button("↘");
		
		if(!diagVis){
			HG.setVisible(false);
			HD.setVisible(false);
			BG.setVisible(false);
			BD.setVisible(false);
		}
		HG.addEventHandler(ActionEvent.ACTION, e );
		HD.addEventHandler(ActionEvent.ACTION, e );
		H.addEventHandler(ActionEvent.ACTION, e );
		G.addEventHandler(ActionEvent.ACTION, e );
		D.addEventHandler(ActionEvent.ACTION, e );
		CTR.addEventHandler(ActionEvent.ACTION, e );
		BG.addEventHandler(ActionEvent.ACTION, e );
		B.addEventHandler(ActionEvent.ACTION, e );
		BD.addEventHandler(ActionEvent.ACTION, e );
		 
		ssroot1.getChildren().add(HG);
		ssroot1.getChildren().add(H);
		ssroot1.getChildren().add(HD);

		ssroot2.getChildren().add(G);
		ssroot2.getChildren().add(CTR);
		ssroot2.getChildren().add(D);

		ssroot3.getChildren().add(BG);
		ssroot3.getChildren().add(B);
		ssroot3.getChildren().add(BD);

		root.getChildren().add(ssroot1);
		root.getChildren().add(ssroot2);
		root.getChildren().add(ssroot3);

		return root;
	}
	

}
