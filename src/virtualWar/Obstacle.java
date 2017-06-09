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
import java.util.ArrayList;
import java.util.Random;


public class Obstacle extends Element{

	private int vie=10;
	
	public Obstacle(int x,int y) {
		super(0, x, y);
		//this.setSprite(new Image("file:./images/obstacle.png"));
		this.setSprite(new Image("file:./images2/Rocher.png"));
	}
	
	/**
	 * Fonction permettant la generation d'obstacle sur un plateau selon un taux
	 * @param taux represente en pourcentage le taux d'obstacle qui sera généré
	 * @return Un ensemble d'obstacle
	 */
	public static ArrayList<Obstacle> generateObstacles(int taux){
		ArrayList<Obstacle> array=new ArrayList<Obstacle>();
		Random r=new Random();
		
		for(int i=0;i<Plateau.getXMAX();i++){
			for(int j=0;j<Plateau.getYMAX();j++){
			
				if(i!= 0&& j!=0 && i!=Plateau.getXMAX()-1&&j!=Plateau.getYMAX()-1){
				
					if(r.nextInt(101)+1<=taux){
						array.add(new Obstacle(i,j));
					}
				}
			}
		}
		
		return array;
	}
	
	
	public String toString(){
		return "O";
	}

	@Override
	public boolean isObstacle() {
		return true;
	}

	@Override
	public boolean isBase() {
		return false;
	}
}
