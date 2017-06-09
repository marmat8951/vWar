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

import java.util.ArrayList;

public class TestJeu {

	public static void main(String[] args) {
		Plateau p=new Plateau(10,10,50);
		p.ajoutObstacle(0,2);
		p.ajoutBase(1, 0, 0);
		p.ajoutBase(2, 9, 9);
	/*	p.ajoutCatmikaze(1, 1, 1,01);
		p.ajoutTireur(1, 0, 0,01);
		p.ajoutChar(1,2,2,01);
		p.ajoutChar(2, 4, 4,01);
		p.ajoutMine(2, 6, 6);
		*/
		System.out.println(p);
		/*System.out.println(p.details(1));
		ArrayList<Robot> equipe1=p.getRobots(1);
		for (Robot r : equipe1){
			r.sePrendMine();
		}
		p.nettoyage();
	*/	
		
		System.out.println(p.details(1));
	
	}

}
