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
package testPlateau;
import java.util.ArrayList;

import virtualWar.*;

public class EssaiPlateau {

	public static void main(String[] args) {

		
		
		Plateau p=new Plateau(20,20,0);
		
		System.out.println(p);
		
		// ajout equipe1
		p.addBase(new Base(1, 0, 0));
		p.ajoutCatmikaze(1,0,1,01);
		p.ajoutTireur(1, 0, 2,01);
		p.ajoutMine(1, 1, 0);
		p.ajoutChar(1, 0, 3, 01);
		p.ajoutPiegeur(1, 0, 4, 01);
		
		//ajout equipe 2
		p.addBase(new Base(2,19,19));
		p.ajoutCatmikaze(2,19,18,01);
		p.ajoutTireur(1, 19, 17,01);
		p.ajoutMine(1, 18, 19);
		p.ajoutChar(1, 19, 16, 01);
		p.ajoutPiegeur(1, 19, 15, 01);
		
		System.out.println(p);
	
		
		
	}

}
