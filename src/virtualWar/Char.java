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

public class Char extends Robot{
	public static final int ENERGIEMAX = 60;
	private static int deplacement = 2;
	private static int coutAction = 5;
	private static int coutDep = 3;
	private static int degatTir = 6;
	private static int degatMine = 6;
	private static int portee=10;
	private static final String TYPE="Char";

	//CONSTRUCTEUR
	
	/**
	 * definit les caracteristiques du char : son equipe, sa position et son id
	 * @param equipe equipe du robot
	 * @param x coordonnee en X
	 * @param y coordonnee en Y
	 * @param id son id
	 */
	public Char(int equipe,int x,int y,int id){
		super(equipe,x,y,id,deplacement);
		if(equipe==1){
			this.setSprite(new Image("file:./images2/CharTeamBleu.png"));
		}else{
			this.setSprite(new Image("file:./images2/CharTeamRouge.png"));
		}
		
		energie=ENERGIEMAX;
		ArrayList<Actions> actions=new ArrayList<Actions>();
		actions.add(Actions.TIRER);
		actions.add(Actions.DEPLACER);
		super.setActions(actions);
		
	}
	//GET & SET
	
	/**
	* 
	* @return retourne le nb de cases de déplacement possible
	*/
	public int getDeplacement() {
		return deplacement;
	}
	

	/**
	 * 
	 * @return retourne le coût en énergie d'une action
	 */
	public int getCoutAction() {
		return coutAction;
	}

	/**
	 * 
	 * @return retourne les dégâts infligés par un tir
	 */
	public int getDegatTir() {
		return degatTir;
	}
	/**
	 * 
	 * @return retourne les dégâts infligés par une mine
	 */
	public int getDegatMine() {
		return degatMine;
	}
	/**
	 * 
	 * @return retourne l'affichage du type du robot
	 */
	public String getType() {
		return TYPE;
	}
	/**
	 * Permet de définir le nombre de cases de déplacement
	 * @param deplacement
	 */
	public void setDeplacement(int deplacement) {
		Char.deplacement = deplacement;
	}

	/**
	 * 
	 * @return retourne l'énergie courante de l'unité
	 */
	public int getEnergie() {
		return energie;
	}
	/**
	 * Permet de modifier l'énergie courante de l'unité
	 * @param energie 
	 */
	public void setEnergie(int energie) {
		this.energie = energie;
	}
	
	
	//METHODES
	
	/**
	 * retourne C si equipe 1 sinon retourne c
	 */
	public String toString(){
		if(getEquipe() == 1){
			return "C";
		} else {
			return "c";
		}
	}
	
	/**
	 * @param x coordonee X
	 * @param y coordonee Y
	 * @return retourne vrai s'il peut tirer en x et y
	*/
	public boolean peutTirer(int x, int y){
		if((y==Plateau.getYMAX()&& x==Plateau.getXMAX())||(y==0 && x==0)
				||getX()==0 && getY()==0||getX()==Plateau.getXMAX() && getY()==Plateau.getYMAX()
				||(x==getX()&& y==getY())||y<0||y>Plateau.getYMAX()-1
				||x<0||x>Plateau.getXMAX()-1||
				x-portee>getX()||x+portee <getX()||
				y+portee<getY()||y-portee>getY()){
			
			return false;
		}
		if(x==getX()&&y!=getY()||y==getY()&&x!=getX()){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * @return Renvoie l'Energie maximum du Char
	 */
	public int getEnergieMax() {
		
		return ENERGIEMAX;
	}

	/**
	 * @return Renvoie le cout d'un deplacement
	 */
	public int getCoutDep() {
		
		return coutDep;
	}
	
	/**
	 * @return Renvoie les particularites du Robot
	 */
	public String particularite(){
		return "";
	}
	
	/**
	 *  @param position en X Y voulue
	 *  
	 *  @return TRUE si la position est atteignable, FALSE sinon
	 *  
	 *  Attention: ne deplace pas le Robot, mais fait baisser son energie proportionellement a un deplacement
	 *  pour deplacer physiquement le robot il faut le faire au niveau de l'Element
	 */
	
	public boolean deplacer(int x, int y) {
		int xactuel=super.getX();
		int yactuel=super.getY();
	
		if(x>xactuel+1 || y>yactuel+1 || y<yactuel-1 || x<xactuel-1){
			return false;
		}
		energie=energie-coutDep;
		return true;
		
	}
	
	/**
	 * Methode si applique les degats d'une mine.
	 */
	public boolean sePrendMine() {
		energie=energie-degatMine;
		return true;
	}
	/**
	 * Methode qui applique les degats d'un tir.
	 */
	public boolean sePrendTir(){
		energie=energie-degatTir;
		return true;
	}
	/**
	 * Tire en X Y
	 * Attention: n'inflige pas les degats. pour cela il faut recuperer le Robot en X et Y et lui infliger des degats avec degatsTir().
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean tire(int x, int y){
		if(!peutTirer(x,y)){
			return false;
		}
		energie=energie-coutAction;
		return true;
	}


	
	/**
	 * Regenere pour un tour le robot
	 */
	public void regen(){
		if(energie<ENERGIEMAX-5){
			energie=energie+5;
		}else{
			energie=ENERGIEMAX;
		}
		
		
	}
	/**
	 * @return retourne les details du char
	 */
	public String details(){
		String res="";
		res+="Robot "+TYPE+" Energie restante: " +getEnergie()+"/"+getEnergieMax() +particularite();
		res+=" Coordonnées : "+getX()+","+getY();
		return res;
	}

	@Override
	public int getPortee() {
		
		return portee;
	}

	
}
