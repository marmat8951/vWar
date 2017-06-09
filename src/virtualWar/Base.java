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

public class Base extends Element{
	
	
	/**
	 * constructeur de la classe heritee
	 *@param int largeur, int hauteur,int equipe
	 */
	public Base(int equipe, int x1, int y1) {
		super(equipe,x1,y1);
		if(equipe==1){
			this.setSprite(new Image("file:./images2/Base.png"));
		}else{
			this.setSprite(new Image("file:./images2/Base.png"));
		}
	}
	
	/**
	 * 
	 * @return String retourne la position de la base
	 */
	public String info(){
		String res="";
		res+="Position en X: "+getX()+" Position en Y: "+getY();
		return res;
	}
	
	
	/**
	 * @param equipe
	 * permet de modifier l'appartenance de la Base
	 */
	void modifierEquipe(int equipe){
		setEquipe(equipe);
	}
	

	/**
	 * retourne B si equipe 1 sinon retourne b
	 */
	public String toString() {
		if(getEquipe()==1){
			return "B";
		} else {
			return "b";
		}
	}
	
	public boolean isObstacle(){
		return false;
	}

	@Override
	public boolean isBase() {
		return true;
	}
}
