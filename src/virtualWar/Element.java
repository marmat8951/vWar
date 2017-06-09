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
import javafx.scene.image.ImageView;

public abstract class Element {
	private int x;
	private int y;
	private int equipe;
	private Image sprite;
	
	
	/**
	 * definit l'equipe a laquelle appartiendra le robot
	 * @param equipe equipe du robot
	 */
	public void setEquipe(int equipe) {
		this.equipe = equipe;
	}

	/**
	 * 
	 * @return retourne l'equipe du robot
	 */
	public int getEquipe(){
		return equipe;
	}
	
	/**
	 * 
	 * @return retourne la position X
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * definit le X du robot
	 * @param x 
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * 
	 * @return retourne la position Y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * definit le Y du robot
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * constructeur pour définir l'equipe et la position
	 * @param equipe
	 * @param x
	 * @param y
	 */
	public Element(int equipe,int x,int y) {
		this.x=x;
		this.y=y;
		this.equipe=equipe;
		sprite=null;
	}
	/**
	 * Constructeur pour définir l'équipe, la position et le sprite de l'élément
	 * @param equipe
	 * @param x
	 * @param y
	 * @param sprite
	 */
	public Element(int equipe,int x,int y,String sprite) {
		this.x=x;
		this.y=y;
		this.equipe=equipe;
		this.sprite=new Image(sprite);
	}

	/**
	 * 
	 * @return le sprite correspondant à l'élément
	 */
	public Image getSprite() {
		return sprite;
	}

	/**
	 * 
	 * @param sprite
	 * Permet de modifier le sprite de l'élément
	 */
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	/**
	 * 
	 * @return true Si C'est un obstacle, false sinon
	 */
	public abstract boolean isObstacle();

	/**
	 * 
	 * @return true Si C'est une base, false sinon
	 */
	public abstract boolean isBase();
}
