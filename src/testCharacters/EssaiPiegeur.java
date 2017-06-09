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
package testCharacters;

import virtualWar.Piegeur;
import virtualWar.Plateau;
import virtualWar.Test;

public class EssaiPiegeur extends Test{
	public static void main(String[] args){
		Piegeur c=new Piegeur(1,0,3,01);
		
		Plateau p=new Plateau(20,20,0);
		/*
		 * Controle des modifications/recuperation de donnees
		 * 
		 */
		
			System.out.println("Deplacement : "+affichageTest(c.getDeplacement()==1));
		
			System.out.println("Cout Action : "+affichageTest(c.getCoutAction()==2));
	
			System.out.println("Cout Deplacement : "+affichageTest(c.getCoutDep()==2));

			System.out.println("Degats Tir Recus: "+affichageTest(c.getDegatTir()==2));
		
			System.out.println("Degats Mine Recus : "+affichageTest(c.getDegatMine()==2));

			System.out.println("Type : "+affichageTest(c.getType()=="Piegeur"));
	
			System.out.println("Energie Max : "+affichageTest(c.getEnergieMax()==50));
					
			System.out.println("Energie : "+affichageTest(c.getEnergie()==50));
			
		
			c.setEnergie(30); // on modifie l'energie
		
			System.out.println("Energie Modif : "+affichageTest(c.getEnergie()==30));
			
			System.out.println("Coord X : "+affichageTest(c.getX()==0));
			
			System.out.println("Coord Y : "+affichageTest(c.getY()==3));
	
		
		/*
		 * Controle des deplacements
		 *	Avec le catmikaze en (0,3) comme position de depart 
		 */
		
			System.out.println("Deplacement impossible 1 : "+affichageTest(!c.deplacer(0, 6)));
			
			System.out.println("Deplacement impossible 2 : "+affichageTest(!c.deplacer(3, 5)));
		
			System.out.println("Deplacement impossible 3 : "+affichageTest(!c.deplacer(2, 5)));
			
			System.out.println("Deplacement Possible : "+affichageTest(c.deplacer(0, 4)));
			
			System.out.println("Deplacement Possible : "+affichageTest(c.deplacer(1, 4)));
		/*
		 * Controle des tirs possibles
		 * 
		 */
			//on remet en place pour la suite des tests 
			c.deplacer(0, 3);
		
			System.out.println("Tir Possible : "+affichageTest(c.peutTirer(0, 4)));
		
			System.out.println("Tir Impossible 1 : "+affichageTest(!c.peutTirer(0, 14)));
			
			System.out.println("Tir Impossible 2 : "+affichageTest(!c.peutTirer(11, 3)));
			
			System.out.println("Tir Impossible 3 : "+affichageTest(!c.peutTirer(Plateau.getXMAX(),Plateau.getYMAX())));
			
			System.out.println("Tir Possible 2 : "+affichageTest(c.peutTirer(1, 4)));
		

		
		/*
		 * Controle des degats subis
		 * 
		 */
			c.setEnergie(c.getEnergieMax());//on remet l'energie intiale pour tester
			
			c.sePrendTir();
					
			
			System.out.println("Subit Tir : "+affichageTest(c.getEnergie()==c.getEnergieMax()-c.getDegatTir()));

			
			c.setEnergie(c.getEnergieMax());//on remet l'energie intiale pour tester
		
			c.sePrendMine();
		
	
			System.out.println("Subit Mine : "+affichageTest(c.getEnergie()==c.getEnergieMax()-c.getDegatMine()));
	
	
		/*
		 * affichage en % de reussite des tests
		 * 
		 */
	
		System.out.println(""+'\n'+'\n'+nbTestCorrect+"/"+nbTest+"   Tests réussis");
	}
}
