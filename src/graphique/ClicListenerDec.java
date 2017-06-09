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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class ClicListenerDec implements EventHandler<ActionEvent>{
	Label label;
	/**
	 * 
	 * @param lbl un label
	 */
	public ClicListenerDec(Label lbl){
		label=lbl;
	}
	/**
	 * handler qui diminue de 1 tant que la valeur est supérieur à 0
	 */
	@Override
	public void handle(ActionEvent event) {
		if(Integer.parseInt(label.getText())>0){
			int newValue=Integer.parseInt(label.getText()) -1;
			label.setText(""+newValue);
		}

	}

}
