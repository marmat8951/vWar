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

public class Catmikaze extends Robot {
	private static int deplacement = 1;
	private static int coutAction = 2;
	private static int coutDep = 0;
	private static final String TYPE="Catmikaze";
	public static final int ENERGIEMAX = 50;
	private static int degatMine = ENERGIEMAX;
	private static int degatTir = ENERGIEMAX;
	private static int portee=1;
	
	// CONSTRUCTEUR(S)
	/**
	 * definit les caracteristiques du catmikaze : son equipe, sa position et son id
	 * @param equipe equipe du robot
	 * @param x coordonnee en X
	 * @param y coordonnee en Y
	 * @param id son id
	 */
	public Catmikaze(int equipe,int x,int y,int id){
		super(equipe,x,y,id,deplacement,new Image("file:./images/catmikaze.png"));
		Image newImage;
		if(equipe==1){
			newImage=(new Image("file:./images2/CatmikazeTeamBleu.png"));
		}else{
			newImage=(new Image("file:./images2/CatmikazeTeamRouge.png"));
		}
		super.setSprite(newImage);
		ArrayList<Actions> actions=new ArrayList<Actions>();
		actions.add(Actions.EXPLOSER);
		actions.add(Actions.DEPLACER);
		super.setActions(actions);
		energie=50;
		
	}

	// GET & SET
	
	/**
	 * 
	 * @return retourne le nombre de cases de deplacement possible
	 */
	public int getDeplacement() {
		return deplacement;
	}
	
	/**
	 * 
	 * @return retourne le cout des actions en energie
	 */
	public int getCoutAction() {
		return coutAction;
	}
	
	/**
	 * 
	 * @return retourne le cout de deplacement en energie
	 */
	public int getCoutDep() {
		return coutDep;
	}
	
	/**
	 * 
	 * @return retourne les degats infliges par un tir
	 */
	public int getDegatTir() {
		return degatTir;
	}
	
	/**
	 * 
	 * @return retourne les degats infliges par une mine
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
		Catmikaze.deplacement = deplacement;
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

	/**
	 * 
	 * @return retourne l'énergie max de l'unité courante 
	 */
	public int getEnergieMax() {
		return ENERGIEMAX;
	}
	
	
	/**
	 * 
	 * @return Determine si le Catmikaze peut exploser
	 */
	public boolean peutTirer(int x,int y){

		if ((y==Plateau.getYMAX()-1&& x==Plateau.getXMAX()-1)||(y==0 && x==0)){
			System.out.println("Impossible de poser aux coordonées d'une base (on ne pete pas dans une base voyons!)");

			return false;
		}else if((getX()==0 && getY()==0) || (getX()==Plateau.getXMAX()-1 && getY()==Plateau.getYMAX()-1)){
			System.out.println("impossible de peter depuis une base (voyons!)");
			return false;
		}else if(x==getX()&& y==getY()){
			System.out.println("impossible de se peter dessus (en plus c'est inutile et c'est du suicide)");
			return false;
		}else if(y<0||y>Plateau.getYMAX()||x<0||x>Plateau.getXMAX()){
			System.out.println("Impossible de peter hors du plateau");
			return false;
		}else if(x-portee>getX()||x+portee <getX()||y+portee<getY()||y-portee>getY()){
			System.out.println("impossible de peter hors de portée");
			return false;
		}else{
			System.out.println("OK pour PETER!");
			return true;
			}
		}
	
	
	/**
	 * @return Renvoie les particularites du Robot
	 */
	public String particularite(){
		return "";
	}
	
	/**
	 * retourne K si equipe 1 sinon retourne a
	 */
	public String toString(){
		if (getEquipe() == 1) {
			return "K";
		} else {
			return "k";
		}
	}
	
	/**
	 *  @param position en X Y voulue
	 *  
	 *  @return TRUE si la position est atteignable, FALSE sinon
	 *  
	 *  Attention: ne deplace pas le Robot, mais fait baisser son energie proportionellement  un deplacement
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
	 * Tire en X Y
	 * Attention: n'inflige pas les degats. pour cela il faut recuperer le Robot en X et Y et lui infliger des degats avec degatsTir().
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean tire(int x, int y){
		if(peutTirer(x,y)){
			setEnergie(0);
			return true;
		}
		return false;
	}
	
	/**
	 * Methode qui applique les degats d'un tir.
	 */
	public boolean sePrendTir(){
		energie=energie-degatTir;
		return true;
	}
	//Tests

	/**
	 * Le catmikaze Regenere instantanement;
	 */
	public void regen(){
		energie=ENERGIEMAX;
	}
	
	/**
	 * @return retourne les details du Catmikaze
	 */
	public String details(){
		String res="";
		res+="Robot "+TYPE+" Energie restante: " +getEnergie()+"/"+getEnergieMax() +particularite();
		res+=" Coordonnees : "+getX()+","+getY();
		return res;
	}


	/**
	 * Le catmikaze meurt si il se prend une mine car il explose!
	 */
	
	public boolean sePrendMine() {
		// TODO Auto-generated method stub
		energie=energie-degatMine;
		return true;
	}

	@Override
	public int getPortee() {
		return portee;
	}


}
