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

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import musiques.Musiques;

public class VirtualWar extends Application {
	static int x;
	static int y;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// primary stage

		VBox rootPrinc = new VBox();
		HBox ssrootPrinc = new HBox();
		HBox buttonsX = new HBox();
		HBox buttonsY = new HBox();
		VBox ssrootX = new VBox();
		VBox ssrootY = new VBox();
		Label affichX = new Label("5");
		Label affichY = new Label("5");
		Button ok = new Button("OK");
		
		Musiques sons=new Musiques();
		MediaPlayer mpFond=sons.getMediaPlayer("start");
		mpFond.play();
		MediaPlayer go=sons.getMediaPlayer("go");

		ok.setMaxWidth(Double.MAX_VALUE);
		ok.setAlignment(Pos.CENTER);
		ok.setOnMouseClicked(e -> {
			go.play();
			mpFond.stop();
			if (Integer.valueOf(affichX.getText()) > 0 && Integer.valueOf(affichY.getText()) > 0) {
				x = Integer.valueOf(affichX.getText());
				y = Integer.valueOf(affichY.getText());
				primaryStage.close();
				try {
					new Jeu().start(primaryStage);
				} catch (Exception e1) {
					System.err.println("Le jeu n'a pas pu se lancer pour la cause suivante:");
					e1.printStackTrace();
				}

			}

		});
		// partie X

		Button moinsX = new Button("  -  ");
		moinsX.setOnMouseClicked(e -> {
			int current = Integer.valueOf(affichX.getText());
			if (current > 5) {
				affichX.setText("" + (current - 1));
			}
		});
		Button plusX = new Button("  +  ");
		plusX.setOnMouseClicked(e -> {
			int current = Integer.valueOf(affichX.getText());
			if (current <20) {
				affichX.setText("" + (current + 1));
			}
		});

		affichX.setAlignment(Pos.CENTER);
		affichX.setMaxWidth(Double.MAX_VALUE);

		buttonsX.getChildren().addAll(moinsX, plusX);

		// partie Y

		Button moinsY = new Button("  -  ");
		moinsY.setOnMouseClicked(e -> {
			int current = Integer.valueOf(affichY.getText());
			if (current > 5) {
				affichY.setText("" + (current - 1));
			}
		});
		Button plusY = new Button("  +  ");
		plusY.setOnMouseClicked(e -> {
			int current = Integer.valueOf(affichY.getText());
			if (current < 20) {
				affichY.setText("" + (current + 1));
			}
		});
		/////////////////////////////////////////////

		affichY.setMaxWidth(Double.MAX_VALUE);
		affichY.setAlignment(Pos.CENTER);

		buttonsY.getChildren().addAll(moinsY, plusY);

		ssrootX.getChildren().addAll(affichX, buttonsX);

		ssrootY.getChildren().addAll(affichY, buttonsY);

		Label text1 = new Label("Choisissez la taille du plateau");
		text1.setStyle("-fx-font-size:15px;");
		text1.setMaxWidth(Double.MAX_VALUE);
		text1.setAlignment(Pos.CENTER);
		text1.setTextFill(Color.RED);

		ssrootPrinc.setMaxWidth(Double.MAX_VALUE);
		ssrootPrinc.setAlignment(Pos.CENTER);

		ssrootPrinc.getChildren().addAll(ssrootX, ssrootY);
		rootPrinc.getChildren().addAll(text1, ssrootPrinc, ok);

		Scene scene1 = new Scene(rootPrinc);

		primaryStage.setScene(scene1);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

}
