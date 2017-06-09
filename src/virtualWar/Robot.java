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

import javafx.scene.image.Image;

public abstract class Robot extends Element {

	protected int energie;
	private final int id;
	private boolean peutAgir=true;
	private int Deplacement;
	private ArrayList<Actions> actions;
	/**
	 * 
	 * @return retourne l'énergie courante de l'unité
	 */
	public int getEnergie() {
		return energie;
	}
	
	/**
	 * 
	 * @return retourne si le robot peut agir
	 */
	public boolean isPeutAgir() {
		return peutAgir;
	}

	/**
	 * défini si le robot peut agir
	 * @param peutAgir 
	 */
	public void setPeutAgir(boolean peutAgir) {
		this.peutAgir = peutAgir;
	}


	/**
	 * 
	 * @return la distance que le robot peut faire
	 */


	

	public int getDeplacement() {
		return Deplacement;
	}

	/**
	 * défini la distance que le robot peut faire
	 * @param distanceDeplacement
	 */
	public void setDeplacement(int Deplacement) {
		this.Deplacement = Deplacement;
	}


	/**
	 * 
	 * @param largeur coordonnee x du robot
	 * @param hauteur coordonnee y du robot
	 * @param equipe equipe auquel appartient le robot
	 */
	
	public Robot(int equipe,int x,int  y,int id,int DistDeDep){
		super(equipe,x,y);
		actions=new ArrayList<Actions>();
		this.id=id;
		Deplacement=DistDeDep;
	}
	
	/**
	 * 
	 * @return une liste d'actions
	 */
	public ArrayList<Actions> getActions() {
		return actions;
	}

	/**
	 * défini la liste d'actions du robot
	 * @param actions
	 */
	public void setActions(ArrayList<Actions> actions) {
		this.actions = actions;
	}

	/**
	 * 
	 * @param equipe l'équipe du robot
	 * @param x la coordonnée X du robot
	 * @param y la coordonnée Y du robot
	 * @param id l'id du robot
	 * @param sprite le sprite du robot
	 */
	public Robot(int equipe,int x,int  y,int id,int deplacm,Image sprite){
		this(equipe,x,y,deplacm,id);
		this.setSprite(sprite);
	}
	
	/**
	 * definit l'energie que possedera le robot
	 * @param energie energie du robot
	 */
	public void setEnergie(int energie) {
		this.energie = energie;
	}
	
	/**
	 * 
	 * @return retourne l'énergie max de l'unité
	 */
	public abstract int getEnergieMax();
	
	/**
	 * @return Cout du deplacement
	 */
	public abstract int getCoutDep();
	
	/**
	 * 
	 * @return retourne les dégâts infligés par un tir
	 */
	public abstract int getDegatTir();
	
	/**
	 * 
	 * @return retourne les dégâts infligés par une mine
	 */
	public abstract int getDegatMine();
	
	/**
	 * Renvoie les Details pour le tireur
	 */
	public abstract String details();
	
	/**
	 * Renvoie les particularites du Robots pour la fonction details de plateau
	 */
	public abstract String particularite();
	
	/**
	 * 
	 * @return retourne vrai s'il peut tirer 
	 */
	public abstract boolean peutTirer(int x, int y);
	
	/**
	 * 
	 * @return retourne l'affichage du type du robot
	 */
	public abstract String getType();
	
	/**
	 * Tire a une coordonnee
	 * @param x Coordonnee en X
	 * @param y coordonnee en Y
	 * @return True si atteignable, False sinon
	 */
	public abstract boolean tire(int x, int y);
	
	/**
	 * Le Robot se prend les dégats d'une mine
	 */
	public abstract boolean sePrendMine();
	
	/**
	 *  @param position en X Y voulue
	 *  
	 *  @return TRUE si la position est atteignable, FALSE sinon
	 *  
	 *  Attention: ne deplace pas le Robot, mais fait baisser son energie proportionellement a un deplacement
	 *  pour deplacer physiquement le robot il faut le faire au niveau de l'Element
	 */
	public abstract boolean deplacer(int x, int y);
	
	/**
	 * Le Robot se prend les degats d'un tir
	 */
	public abstract boolean sePrendTir();
	
	/**
	 * Regeneration d'un tour
	 */
	public abstract void regen();
	
	/**
	 * retourne l'equipe du robot et son energie
	 */
	public String toString() {
		return "Robot: de l'equipe"+getEquipe()+" Energie:"+energie;
	}

	/**
	 * @param equipe
	 * permet de modifier l'appartenance du Robot
	 */
	void modifierEquipe(int equipe){
		setEquipe(equipe);
	}
	
	/**
	 * défini le sprite
	 */
	
	public void setSprite(Image sprite){
		super.setSprite(sprite);
	}
	
	/**
	 * 
	 * @return l'id (non utilisé pour le moment)
	 */
	public int getId(){
		return id;
	}
	/**
	 * 
	 * @return portée du Robot
	 */
	public abstract int getPortee();
	/**
	 * 
	 * @return retourne le coût en énergie d'une action
	 */
	public abstract int getCoutAction();
	
	public boolean isObstacle(){
		return false;
	}
	public boolean isBase(){
		return false;
	}
}


