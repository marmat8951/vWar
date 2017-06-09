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
import virtualWar.*;
public class EssaiMine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nbTest=2;
		int nbTestCorrect=0;
		
		Plateau p=new Plateau(20,20,0);
		Mine m1=new Mine(1,0,3);
		Mine m2=new Mine(2,16,15);
		
		if(m1.toString()=="M"){
			System.out.println("Affichage Mine 1 : correct"+'\n');
			nbTestCorrect++;
		}
		
		
		if(m2.toString()=="m"){
			System.out.println("Affichage Mine 2 : correct"+'\n');
			nbTestCorrect++;
		}
		
		System.out.println(""+'\n'+nbTestCorrect+"/"+nbTest+"  corrects");
		
	}

}
