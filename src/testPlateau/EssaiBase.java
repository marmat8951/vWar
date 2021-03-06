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

public class EssaiBase extends Test{
	   
	public static void main(String[] args){
		
		Plateau p=new Plateau(20,20,0);
		
		Base base1=new Base(1,0,0);
		Base base2=new Base(2,20,20);
		
		System.out.println("Affichage Base 1 : "+affichageTest(base1.toString()=="B")+'\n'+base1.info()+'\n');
			
		System.out.println("Affichage Base 2 : "+affichageTest(base2.toString()=="b")+'\n'+base2.info());
	}

}
