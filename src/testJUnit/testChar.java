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

import org.junit.Assert;
import org.junit.Test;

import virtualWar.Char;
import virtualWar.Plateau;

public class testChar {
	Char c=new Char(1,0,3,01);
	
	Plateau p=new Plateau(20,20);
	
	@Test
	// On vérifie que les Get & Set fonctionnent normalement
	public void test__Modif_Get_Set(){
		Assert.assertEquals(c.getDeplacement(),2);
		Assert.assertEquals(c.getCoutAction(),5);
		Assert.assertEquals(c.getCoutDep(),5);
		Assert.assertEquals(c.getDegatTir(),6);
		Assert.assertEquals(c.getDegatMine(),6);
		Assert.assertEquals(c.getType(),"Char");
		Assert.assertEquals(c.getEnergieMax(),60);
		Assert.assertEquals(c.getEnergie(),60);	
		
		c.setEnergie(30);
		
		Assert.assertEquals(c.getEnergie(),30);
		Assert.assertEquals(c.getX(),0);
		Assert.assertEquals(c.getY(),3);
	}
	
	@Test
	//On vérifie la validité ou non des déplacements
	public void test_Deplacements(){
		Assert.assertFalse(p.deplacerAll(c, 0, 6));
		Assert.assertTrue(p.deplacerAll(c, 0, 5));
		Assert.assertTrue(p.deplacerAll(c, 2, 5));
		Assert.assertTrue(p.deplacerAll(c, 2, 3));
		Assert.assertFalse(p.deplacerAll(c, 2, 6));
	}
	
	@Test
	//On vérifie que les tirs soient possibles ou non selon les obstacles/team
	public void test_Tirs_Possibles(){
		c.deplacer(0, 3);
		
		Assert.assertTrue(c.peutTirer(0, 13));
		Assert.assertFalse(c.peutTirer(0, 14));
		Assert.assertFalse(c.peutTirer(11, 3));
		Assert.assertFalse(c.peutTirer(1, 4));
		Assert.assertFalse(c.peutTirer(Plateau.getXMAX(),Plateau.getYMAX()));
	}
	
	@Test
	//On vérifie que les dégâts se déduisent correctement
	public void test_Degats_Subis(){
		c.setEnergie(c.getEnergieMax());
		c.sePrendTir();
		
		Assert.assertEquals(c.getEnergie(),c.getEnergieMax()-c.getDegatTir());	
		
		c.setEnergie(c.getEnergieMax());
		c.sePrendMine();
		
		Assert.assertEquals(c.getEnergie(),c.getEnergieMax()-c.getDegatMine());	
	}
}
