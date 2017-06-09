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

import javafx.scene.layout.HBox;
import virtualWar.Actions;
import virtualWar.Robot;

/**
 * Hbox qui affiche les différentes actions possibles
 *
 */
public class HboxBouttonsActions {
	ArrayList<Actions> actionspossibles;
	HBox res;
	HboxBouttonsActions(Robot r,HBox boite){
		actionspossibles=r.getActions();
		res=boite;
		boite.getChildren().clear();
	}
	
}
