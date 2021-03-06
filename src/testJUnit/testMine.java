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

import virtualWar.Mine;
import virtualWar.Plateau;

public class testMine {
	
	Plateau p=new Plateau(20,20);
	Mine m1=new Mine(1,0,3);
	Mine m2=new Mine(2,16,15);
	
	@Test
	// On vérifie que les mines aient la bonne syntaxe en fonction de l'équipe
	public void test_Mine(){
		Assert.assertEquals(m1.toString(),"M");
		Assert.assertEquals(m2.toString(),"m");
	}

}
