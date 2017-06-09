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
package testJUnit;

import org.junit.Test;

import virtualWar.Base;
import virtualWar.Plateau;
import org.junit.Assert;


public class TestPlateau {
	Plateau p;
	
	@Test
	public void testPlacementObstacles(){
	/*	p=new Plateau(100,100,0);
		Assert.assertTrue(p.nbObstacles()==0);
		p=new Plateau(100,100,100);
		//Assert.assertTrue(p.nbObstacles()>p.getXMAX()*p.getYMAX()-2);
	*/
		
		Plateau p=new Plateau(20,20,0);
		
		// ajout equipe1
		p.addBase(new Base(1,0,0));
		p.ajoutCatmikaze(1,0,1,1);
		p.ajoutTireur(1,0,2,1);
		p.ajoutMine(1,1,0);
		p.ajoutChar(1,0,3,1);
		p.ajoutPiegeur(1,0,4,1);
		
		//ajout equipe 2
		p.addBase(new Base(2,19,19));
		p.ajoutCatmikaze(2,19,18,1);
		p.ajoutTireur(2,19,17,1);
		p.ajoutMine(2,18,19);
		p.ajoutChar(2,19,16,1);
		p.ajoutPiegeur(2,19,15,1);
		
		p.ajoutObstacle(1, 1);
		
		
		
		// test catmikaze sur obstacle
		System.out.println(p.deplacerAll(p.robotEn(0, 1), 1, 1));
		Assert.assertFalse(p.deplacerAll(p.robotEn(0, 1), 1, 1));
		
		// test tireur déplacement de 2
		System.out.println(p.deplacerAll(p.robotEn(0, 2), 2, 2));
		Assert.assertFalse(p.deplacerAll(p.robotEn(0, 2), 2, 2));
		
		// test tireur sur obstacle
		System.out.println(p.deplacerAll(p.robotEn(0, 2), 1, 1));
		Assert.assertFalse(p.deplacerAll(p.robotEn(0, 2), 1, 1));
		
		// test tireur déplacement de 1
		Assert.assertTrue(p.deplacerAll(p.robotEn(0, 2), 1, 2));
		
		// test tireur qui essaie d'aller sur une case déjà prise par un robot
		Assert.assertFalse(p.deplacerAll(p.robotEn(1, 2), 0, 3));
		
		System.out.println(p);
		System.out.println(p.details(1));
		System.out.println(p.details(2));
		
		
	}
	


	
}

