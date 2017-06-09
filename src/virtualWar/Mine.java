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
package virtualWar;

import javafx.scene.image.Image;

public class Mine extends Element {
	public Mine(int equipe, int x,int y) {
		super(equipe,x,y);
		this.setSprite(new Image("file:./images2/Mine.png"));
	}


	/**
	 * @param equipe
	 * permet de modifier l'appartenance de la Mine
	 */
	void modifierEquipe(int equipe){
		setEquipe(equipe);
	}
	
	/**
	 * affiche M pour l'equipe 1 sinon m
	 */
	public String toString(){
		if(getEquipe()==1){
			return "M";
		}else{
			return "m";
		}
	}
	/**
	 * Pour le moment on ne peut pas deplacer une mine
	 */


	@Override
	public boolean isObstacle() {
		return false;
	}


	@Override
	public boolean isBase() {
		return false;
	}
	
	





}
