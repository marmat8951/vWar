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
package verifSaisie;

public class EntierPositifNonVide {
	
	public static boolean verifie(String s){
		if(s.length()==0 || s.equals("")){
			return false;
		}else{
			for(int i=0;i<s.length();i++){
				if(s.charAt(i)>'9'||s.charAt(i)<'0'){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 
	 * @param s
	 * @param min minimum inclu du chiffre en question
	 * @param max
	 * @return Vrai si inclu, faux sinon ou si non entier
	 */
	public static boolean entre(String s,int min,int max){
		if(verifie(s)){
			if(Integer.valueOf(s)<=max && Integer.valueOf(s)>=min){
				return true;
			}
		}
		return false;
	}
}
