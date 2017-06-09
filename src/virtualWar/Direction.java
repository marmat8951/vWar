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

public enum Direction {

	H(-1,0),B(1,0),G(0,-1),D(0,1),HG(-1,-1),HD(-1,1),BG(1,-1),BD(1,1),CTR(0,0);
	
	int x,y;
	
	Direction(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public Coordonnee getDirection(Direction d){
		return new Coordonnee(x,y);
	}
	
}
