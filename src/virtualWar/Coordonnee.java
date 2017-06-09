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

public class Coordonnee {
	private int largeur;
	private int hauteur;
	
	/**
	 * constructeur qui place des coordonnees X, Y
	 * @param largeur
	 * @param hauteur
	 */
	public Coordonnee(int largeur, int hauteur) {
		super();
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
	
	/**
	 * 
	 * @return retourne la largeur X
	 */
	public int getLargeur() {
		return largeur;
	}
	
	/**
	 * 
	 * @return retourne la hauteur Y
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * retourne les coordonnees
	 */
	public String toString() {
		return "Coordonnee [largeur=" + largeur + ", hauteur=" + hauteur + "]";
	}
	

	public Coordonnee ajout(Coordonnee coord){
		return new Coordonnee(coord.getLargeur()+largeur,coord.getHauteur()+hauteur);
	}
	
	
}
