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

public class Piegeur extends Robot {
	private static int deplacement = 1;
	private static int coutAction = 2;
	private static int coutDep = 2;
	private static int degatTir = 2;
	private static int degatMine = 2;
	public static final int ENERGIEMAX=50;
	private static final String TYPE = "Piegeur";
	private int nombreMine;
	private static final int portee=1;
	//CONSTRUCTEUR(S)
	/**
	 * definit les caracteristiques du piegeur : son equipe, sa position et son id
	 * @param equipe equipe du robot
	 * @param x coordonnee en X
	 * @param y coordonnee en Y
	 * @param id son id
	 */
	public Piegeur(int equipe,int x,int y,int id){
		super(equipe,x,y,id,deplacement);
		if(equipe==1){
			this.setSprite(new Image("file:./images2/PiegeurTeamBleu.png"));
		}else{
			this.setSprite(new Image("file:./images2/PiegeurTeamRouge.png"));
		}
		//this.setSprite(new Image("file:./images/piegeur.png"));
		energie=ENERGIEMAX;
		nombreMine=10;
		ArrayList<Actions> actions=new ArrayList<Actions>();
		actions.add(Actions.PIEGER);
		actions.add(Actions.DEPLACER);
		super.setActions(actions);
	}

	// GET & SET
	
	/**
	 * 
	 * @return retourne le nombre de mines
	 */
	public int getNombreMine(){
		return nombreMine;
	}
	
	/**
	 * modifie le nombre de mines
	 * @param nbmine le nombre de mines posees 
	 */
	public void setNombreMine(int nbmine){
		this.nombreMine=nbmine;
	}
	/**
	 * 
	 * @return retourne le nombre de cases de deplacement possibles
	 */
	public int getDeplacement() {
		return deplacement;
	}
	
	/**
	 * 
	 * @return retourne le cout des actions en energie
	 */
	public  int getCoutAction() {
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
		Piegeur.deplacement = deplacement;
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
	 * Renvoie l'energie max de ce type d'unite
	 */
	public int getEnergieMax(){
		return ENERGIEMAX;
	}
	
	//METHODES

	/**
	 * Renvoie les particularites du Robot pour la fonction details de plateau
	 */
	public String particularite() {
		
		return "Nombre de mine:"+getNombreMine();
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
	 * Le Robot se prend les degats d'une mine
	 */
	public boolean sePrendMine() {
		energie=energie-degatMine;
		return true;
	}
	
	
	/**
	 * Le Robot prend les degats d'un tir
	 */
	
	public boolean sePrendTir(){
		energie=energie-degatTir;
		return true;
	}
	/**
	 * Attention, ce dernier ne verifie pas la présence d'un element sur une case
	 *@return Boolean qui indique si le robot peut ou non tirer a ces coordonnees
	 *@param deux entiers x et y correspondant aux coordonnees 
	 */
	
	public boolean peutTirer(int x,int y){
		if ((y==Plateau.getYMAX()-1&& x==Plateau.getXMAX()-1)||(y==0 && x==0)){
			System.out.println("Impossible de poser aux coordonées d'une base");
			return false;
		}else if(getX()==0 && getY()==0 || getX()==Plateau.getXMAX()-1 && getY()==Plateau.getYMAX()-1){
			System.out.println("impossible de poser depuis une base");
			return false;
		}else if(x==getX()&& y==getY()){
			System.out.println("impossible de se poser dessus");
			return false;
		}else if(y<0||y>Plateau.getYMAX()||x<0||x>Plateau.getXMAX()){
			System.out.println("Impossible de poser hors du plateau");
			return false;
		}else if(x-portee>getX()||x+portee <getX()||y+portee<getY()||y-portee>getY()){
			System.out.println("impossible de poser hors de portée");
			return false;
		}else{
			System.out.println("Pose OK");
			return true;
		}
	}
	/**
	 *
	 *@return Boolean qui indique si le robot peut ou non tirer a ces coordonnees
	 *@param deux entiers x et y correspondant aux coordonnees 
	 *@param Plateau de jeu pour verifier que la case n'est pas deja occupée
	 */
	

	
	public boolean peutTirer(int x, int y,Plateau p) {
		if(x>Plateau.getXMAX()|| y>Plateau.getYMAX()||
			y<0 || x<0 || p.caseOccupe(x, y) ||
			x-portee>getX() || y-portee<getY() || x+portee<getX() || y+portee<getY()){
			
			return false;
		}
		//Ancienne condition
				/* (y==Plateau.getYMAX()&& x==Plateau.getXMAX())||(y==0 && x==0)
				||getX()==0 && getY()==0||getX()==Plateau.getXMAX() && getY()==Plateau.getYMAX()
				||(x==getX()&& y==getY())||y<0||y>Plateau.getYMAX()-1
				||x<0||x>Plateau.getXMAX()-1||
				x-portee>getX()||x+portee <getX()||
				y+portee<getY()||y-portee>getY()){
			
			return false;
			*/
		if(x!=getX()||y!=getY()){ // Si ce n'est pas sur la case ou l'on est
			return true;
		}
		else{        //sinon ce n'est pas bon
			return false;
		}

	}
	/**
	 * @param x position en x
	 * @param y position en y
	 * Pose une mine
	 */
	public boolean tire(int x, int y){
		if(peutTirer(x,y)){
			this.setNombreMine(getNombreMine()-1);
			this.setEnergie(getEnergie()-getCoutAction());
			return true;
		}
		return false;
	}
	/**
	 * Regenere le robot
	 */
	public void regen(){
		nombreMine=10;
		if(energie<ENERGIEMAX-5){
			energie=energie+5;
		}else{
			energie=ENERGIEMAX;
		}
	}

	/**
	 * retourne P si equipe 1 sinon retourne p
	 */
	public String toString(){
		if (getEquipe() == 1) {
			return "P";
		} else {
			return "p";
		}
	
	}
	
	/**
	 * Renvoie les details pour le piegeur
	 */
	public String details(){
		String res="";
		res+="Robot "+TYPE+" Energie restante: " +getEnergie()+"/"+getEnergieMax();
		res+=" Coordonnées : "+getX()+","+getY();
		return res;
	}

	@Override
	public int getPortee() {
		
		return portee;
	}


	
	
}
