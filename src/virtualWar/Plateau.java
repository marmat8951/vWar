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

public class Plateau {
	private ArrayList<Base> bases;
	private ArrayList<Robot> robots;
	private ArrayList<Mine> mines;
	private ArrayList<Obstacle> obstacles;
	private static int XMAX;
	private static int YMAX;

	/**
	 * 
	 * @param b une base
	 * @return retire la base mise en paramètre
	 */
	boolean enleveBase(Base b){
		return bases.remove(b);
	}

	/**
	 * 
	 * @param m une mine
	 * @return enlève la mine mise en paramètre
	 */
	boolean enleveMine(Mine m){
		return mines.remove(m);
	}

	/**
	 * 
	 * @param r un robot
	 * @return enlève le robot mis en paramètre
	 */
	boolean enleveRobot(Robot r){
		return robots.remove(r);
	}

	/**
	 * 
	 * @param o un obstacle
	 * @return enlève l'obstacle mis en paramètre
	 */
	boolean enleveObstacle(Obstacle o){
		return obstacles.remove(o);
	}

	/**
	 * 
	 * @return le maximum atteignable par X
	 */
	public static int getXMAX() {
		return XMAX;
	}

	/**
	 * 
	 * @return le maximum atteignable par Y
	 */
	public static int getYMAX() {
		return YMAX;
	}
	/**
	 * Nettoie le plateau en supprimant tous les robots sans vie
	 * @return Renvoie le nombre d'éléments supprimés
	 */
	public int nettoyage(){
		int res=0;
		ArrayList<Robot> robotsAVirer=new ArrayList<Robot>();
		for (Robot robot : robots) {
			if (robot.getEnergie()<1){
				robotsAVirer.add(robot);
				res++;
			}
		}
		for(Robot r : robotsAVirer){
			robots.remove(r);
		}
		return res;
	}
	/**
	 * Constructeur plateau sans obstacle
	 */
	public Plateau(int xmax, int ymax) {
		this(xmax,ymax,0);
	}
	/**
	 * constructeur pour le plateau
	 * @param xmax
	 * @param ymax
	 * @param taux d'obstacles en pourcentage
	 */
	public Plateau(int xmax, int ymax,int tauxObstacle) {
		bases=new ArrayList<Base>();
		robots=new ArrayList<Robot>();
		mines=new ArrayList<Mine>();

		XMAX=xmax;
		YMAX=ymax;
		obstacles=Obstacle.generateObstacles(tauxObstacle);

	}


	/**
	 * 
	 * @param m une mine
	 * @return ajoute la mine mise en paramètre
	 */
	public boolean addMine(Mine m){
		return mines.add(m);
	}

	/**
	 * 
	 * @param r un robot
	 * @return ajoute le robot mis en paramètre
	 */
	public boolean addRobot(Robot r){
		return robots.add(r);
	}

	/**
	 * 
	 * @param b une base
	 * @return ajoute la base mise en paramètre
	 */
	public boolean addBase(Base b){
		return bases.add(b);
	}

	/**
	 * 
	 * @param o un obstacle
	 * @return ajoute l'obstacle mis en paramètre
	 */
	boolean addObstacle(Obstacle o){
		return obstacles.add(o);
	}
	/**
	 * 
	 * @param x coordonnée X
	 * @param y coordonnée Y
	 * @return Vrai si la case est déja occupée, Faux si elle est vide
	 */
	public boolean caseOccupe(int x, int y){
		return elementEn(x,y)!=null;
	}

	/**
	 * Renvoie un detail des Robots de l'équipe passée en paramètre
	 * @param equipe
	 * @return Un détail complet de l'équipe
	 */
	public String details(int equipe){
		ArrayList<Robot> r= getRobots(equipe);
		String res="Equipe : "+equipe+"\n";
		res+="Nombre de robots restants:"+nbRobotEquipe(equipe)+"\n";
		res+="Bases:"+infoBases(equipe);
		for(Robot re:r){
			res+=re.details();
			res+=" est sur base: "+estSurBase(re)+" ";
			res+=re.particularite()+"\n\n";
		}
		for(Mine m:getMines(equipe)){
			res+="Mine en: "+m.getX()+","+m.getY();
		}
		return res;
	}

	/**
	 * @param equipe
	 * @return Renvoie les informations sur les bases de l'équipe
	 */

	public String infoBases(int equipe){
		String res="";
		for(Base b:getBases(equipe)){
			res+=b.info()+"\n";
		}
		return res;
	}
	/**
	 * renvoie la mine aux coordonnées x y
	 * @param x la coordonnée x
	 * @param y la coordonnée y
	 * @return
	 */
	private Mine getMine(int x, int y){
		Mine res=null;
		ArrayList<Mine> m=getMines();
		for(Mine mn : m){
			if(mn.getX()==x && mn.getY()==y){
				res=mn;
			}
		}
		return res;
	}
	/**
	 * Déplace le robot char mis en paramètre aux coordonnées indiquées
	 * @param r un robot
	 * @param x la coordonnée x
	 * @param y la coordonnée y
	 * @return 
	 */
	public boolean deplacerChar(Robot r, int x, int y){
		if((x==r.getX() && (y==r.getY()+2 || y==r.getY()-2) )||(y==r.getY() && (x==r.getX()+2 || x==r.getX()-2))){
			int xentre=(r.getX()+x)/2;
			int yentre=(r.getY()+y)/2;
			if(deplacer(r,xentre,yentre)){
				if(deplacer(r,x,y)){
					r.setEnergie(r.getEnergie()+r.getCoutDep());
				}
				return true;
			}
			return false;
		}
		return false;
	}
	/**
	 * Déplace le robot char mis en paramètre aux coordonnées indiquées
	 * @param r un robot char
	 * @param x la coordonnée x
	 * @param y la coordonnée y
	 * @return 2 si 2 cases de déplacement, 1 si une case de deplacement 0 si 0 cases de déplacement et -1 si coord impossible a atteindre
	 */
	public int deplacerCharInt(Robot r, int x, int y){
		if((x==r.getX() && (y==r.getY()+2 || y==r.getY()-2) )||(y==r.getY() && (x==r.getX()+2 || x==r.getX()-2))){
			int xentre=(r.getX()+x)/2;
			int yentre=(r.getY()+y)/2;
			if(deplacer(r,xentre,yentre)){
				if(deplacer(r,x,y)){
					r.setEnergie(r.getEnergie()+r.getCoutDep());
					return 2;
				}
				return 1;
			}
			return 0;
		}
		return -1;
	}

	/**
	 * 
	 * @param r un robot
	 * @param x la coordonnée x
	 * @param y la coordonnée y
	 * @return le déplacement du robot en paramètre
	 */
	public boolean deplacerAll(Robot r,int x,int y){

		if(r.getType()=="Char"){
			return deplacerChar(r,x,y);
		}

		return deplacer(r,x,y);


	}
	/**
	 * 
	 * @param r un robot
	 * @param x la coordonnée x
	 * @param y la coordonnée y
	 * @return le code de déplacement du robot en paramètre
	 */
	public int deplacerAllInt(Robot r,int x,int y){

		if(r.getType()=="Char"){
			return deplacerCharInt(r,x,y);
		}

		return deplacerInt(r,x,y);


	}
	/**
	 * 
	 * @param r le robot
	 * @param x la coordonnee x
	 * @param y la coordonnee y
	 * @return retourne true si le robot peut se deplacer sinon false
	 */


	public boolean deplacer(Robot r,int x,int y){
		boolean ok=true;
		int xActuel=r.getX();
		int yActuel=r.getY();




		if (x>XMAX-1 || y>YMAX-1 || x<0 || y<0){
			//Si la case est hors du plateau on ne peut pas s'y deplacer
			System.out.println("La case saisie est Hors du plateau!");
			ok=false;
		}

		if (robotEn(x,y)!=null && baseEn(x,y)==null){
			//Si il y a deja un robot sur la case on ne peut pas s'y déplacer!
			System.out.println("La case est déja prise par un robot!");
			ok=false;
		}


		if(baseEn(x,y)!=null && baseEn(x,y).getEquipe()!=r.getEquipe() ){
			//Si c'est une base de l'équipe adverse on ne peut pas s'y déplacer!
			System.out.println("La case est déja prise par une base Adverse!!");
			ok=false;
		}
		//Si on a verifié les conditions précédentes
		if(obstacleEn(x,y)!=null){
			System.out.println("Il y a un obstacle a la case indiquee");
			ok=false;
		}


		if(ok){
			if (mineEn(x,y)!=null){ //Si il y a une mine sur la case,
				if (r.deplacer(x, y)){    //Et que l'on peut s'y déplacer
					r.sePrendMine();
					System.out.println("Le robot se prend une mine!");
					enleveMine(getMine(x,y));
					r.setX(x);
					r.setY(y);
				}else{
					ok=false;
				}
			}
			// si il n'y a pas de mine
			else{
				if(r.deplacer(x, y)){ //si on peut s'y deplacer
					r.setX(x);
					r.setY(y);
					System.out.println("Le robot s'est déplacé sans problème");
				}else{
					ok=false;
				}
			}


		}

		System.out.println("\n");
		return ok;
	}

	/**
	 * 
	 * @param r le robot
	 * @param x la coordonnee x
	 * @param y la coordonnee y
	 * @return retourne -1 si case Hors Plateau, -2 si case déja prise par un robot, -3 si case est une base adverse, -4 si obstacle , 0 si on ne peut pas s'y deplacer, 3 si on se prend une mine, et 1 si deplacement sans problème.
	 */
	public int deplacerInt(Robot r,int x,int y){
		int xActuel=r.getX();
		int yActuel=r.getY();




		if (x>XMAX-1 || y>YMAX-1 || x<0 || y<0){
			//Si la case est hors du plateau on ne peut pas s'y deplacer
			System.out.println("La case saisie est Hors du plateau!");
			return -1;
		}

		if (robotEn(x,y)!=null && baseEn(x,y)==null){
			//Si il y a deja un robot sur la case on ne peut pas s'y déplacer!
			System.out.println("La case est déja prise par un robot!");
			return -2;
		}


		if(baseEn(x,y)!=null && baseEn(x,y).getEquipe()!=r.getEquipe() ){
			//Si c'est une base de l'équipe adverse on ne peut pas s'y déplacer!
			System.out.println("La case est déja prise par une base Adverse!!");
			return -3;
		}
		//Si on a verifié les conditions précédentes
		if(obstacleEn(x,y)!=null){
			System.out.println("Il y a un obstacle a la case indiquee");
			return -4;
		}


		if (mineEn(x,y)!=null){ //Si il y a une mine sur la case,
			if (r.deplacer(x, y)){    //Et que l'on peut s'y déplacer
				r.sePrendMine();
				System.out.println("Le robot se prend une mine!");
				enleveMine(getMine(x,y));
				r.setX(x);
				r.setY(y);
				return 3;
			}else{
				// 0 si le robot ne peut pas se deplacer
				return 0;
			}
		}
		// si il n'y a pas de mine
		else{
			if(r.deplacer(x, y)){ //si on peut s'y deplacer
				r.setX(x);
				r.setY(y);
				System.out.println("Le robot s'est déplacé sans problème");
				return 1;
			}else{
				return 0;
			}
		}


	}


/**
 * 
 * 
 * Methode qui renvoie un booleen qui est vrai si l'élément est sur la base de l'equipe, faux sinon.
 * 
 */
public boolean estSurBase(Element e){
	ArrayList<Base> r=getBases(e.getEquipe());
	boolean res=false;
	for(Base b:r){
		if(b.getX()==e.getX() && b.getY()==e.getY()){
			res=true;
		}
	}
	return res;
}

/**
 * Vide le plateau
 */
void clear(){
	bases.clear();
	robots.clear();
	mines.clear();
}
/**
 * Ajoute un tireur sur le plateau pour l'équipe "equipe" aux coordonnées x et y 
 * @param equipe
 * @param x la coordonnée x
 * @param y la coordonnée y
 */
public void ajoutTireur(int equipe,int x, int y,int id){
	Tireur t=new Tireur(equipe,x,y,id);
	robots.add(t);
}
/**
 * Ajoute un Char sur le plateau pour l'équipe "equipe" aux coordonnées x et y 
 * @param equipe
 * @param x la coordonnée x
 * @param y la coordonnée y
 */
public void ajoutChar(int equipe,int x, int y,int id){
	Char t=new Char(equipe,x,y,id);
	robots.add(t);
}

/**
 * Ajoute un Piegeur sur le plateau pour l'équipe "equipe" aux coordonnées x et y 
 * @param equipe
 * @param x la coordonnée x
 * @param y la coordonnée y
 */
public void ajoutPiegeur(int equipe,int x, int y,int id){
	Piegeur t=new Piegeur(equipe,x,y,id);
	robots.add(t);
}
/**
 * Ajoute un Catmikaze sur le plateau pour l'équipe "equipe" aux coordonnées x et y 
 * @param equipe
 * @param x la coordonnée x
 * @param y la coordonnée y
 */
public void ajoutCatmikaze(int equipe,int x, int y,int id){
	Catmikaze c=new Catmikaze(equipe,x,y,id);
	robots.add(c);
}
/**
 * Ajoute une Base sur le plateau pour l'équipe "equipe" aux coordonnées x et y 
 * @param equipe
 * @param x la coordonnée x
 * @param y la coordonnée y
 */
public void ajoutBase(int equipe, int x, int y){
	Base b=new Base(equipe, x, y);
	bases.add(b);
}
/**
 * Ajoute un abstacle aux coordonnées indiquées
 * @param x la coordonnée x
 * @param y la coordonnée y
 */
public void ajoutObstacle(int x, int y){
	Obstacle o=new Obstacle(x,y);
	obstacles.add(o);
}
/**
 * Ajoute une Mine sur le plateau pour l'équipe "equipe" aux coordonnées x et y 
 * @param equipe
 * @param x la coordonnée x
 * @param y la coordonnée y
 */
public void ajoutMine(int equipe, int x, int y){
	Mine m=new Mine(equipe, x, y);
	mines.add(m);
}
/**
 * @param i Equipe pour laquelle on veut le nb de robots
 * @return nombre de robots
 */
private int nbRobotEquipe(int i){
	int res=0;
	for(Robot r:robots){
		if(r.getEquipe()==i){
			res++;
		}
	}

	return res;
}





/**
 * recupere une ArrayList des bases du plateau
 * @return l'ArrayList des bases
 */
ArrayList<Base> getBases(int equipe){
	ArrayList<Base> res=new ArrayList<Base>();
	for(Base b : bases){
		if(b.getEquipe()==equipe){
			res.add(b);
		}
	}
	return res;
}

/**
 * recupere tous les robots du plateau
 * @return Liste des robots
 */

public ArrayList<Robot> getRobots(){
	return robots;

}
/**
 * recupere tous les robots du plateau d'une équipe
 * @param equipe
 * @return tous les robos d'une équipe
 */

public ArrayList<Robot> getRobots(int equipe){
	ArrayList<Robot> res=new ArrayList<Robot>();
	for(Robot r:robots){
		if(r.getEquipe()==equipe){
			res.add(r);
		}
	}
	return res;
}
/**
 * @param x la coordonnée x
 * @param y la coordonnée y
 * @return Renvoie l' obstacle en x et Y si il n'y en a pas, alors renvoie null
 */
private Obstacle obstacleEn(int x, int y){
	for (Obstacle o:obstacles){
		if(o.getX()==x && o.getY()==y){
			return o;
		}
	}
	return null;
}


/**
 * recupere une ArrayList des Mines du plateau
 * @return
 */

public ArrayList<Mine> getMines(){
	return mines;
}
/**
 * recupere une ArrayList des Mines du plateau d'une équipe
 * @return
 */
public ArrayList<Mine> getMines(int equipe){
	ArrayList<Mine> res=new ArrayList<Mine>();
	for(Mine m:getMines()){
		if(m.getEquipe()==equipe){
			res.add(m);
		}
	}
	return res;
}
/**
 * 
 * @return nombre d'obstacles sur le plateau
 */
public int nbObstacles(){
	return obstacles.size();
}



/**
 * 
 * @param x la coordonnée x
 * @param y la coordonnée y
 * @return Renvoie la base en x et Y si il n'y en a pas, alors renvoie null
 */
private Base baseEn(int x, int y){
	for(Base b:bases){
		if(b.getX()==x && b.getY()==y ){
			return b;
		}
	}
	return null;
}

/**
 * 
 * @param x la coordonnée x
 * @param y la coordonnée y
 * @return Renvoie le robot en x et Y si il n'y en a pas, alors renvoie null
 */
public Robot robotEn(int x, int y){
	for(Robot r:robots){
		if(r.getX()==x && r.getY()==y ){
			return r;
		}
	}
	return null;
}

/**
 * 
 * @param x la coordonnée x
 * @param y la coordonnée y
 * @return Renvoie la mine en x et Y si il n'y en a pas, alors renvoie null
 */
private Mine mineEn(int x, int y){
	for(Mine m:mines){
		if(m.getX()==x && m.getY()==y){
			return m;
		}
	}
	return null;
}
/**
 * 
 * @param x la coordonnée x
 * @param y la coordonnée y
 * @return retourne la base si il y en a une ou une mine ou un robot sinon null
 */
public Element elementEn(int x, int y){
	Base b=baseEn(x,y);
	if(b!=null){
		return b;
	}
	Obstacle o=obstacleEn(x,y);
	if(o!=null){
		return o;
	}
	Mine m=mineEn(x,y);
	if(m!=null){
		return m;
	}
	Robot r=robotEn(x,y);
	if(r!=null){
		return r;
	}
	return null;
}







/**
 * Affichage du plateau complet.
 */
public String toString(){
	String res="";
	for(int i=0;i<XMAX;i++){
		res+=" ";
		for(int k=0;k<YMAX;k++){
			res+="+---";
		}
		res+="+";
		res+="\n";
		for(int j=0;j<YMAX;j++){
			Element tmp=elementEn(i,j);
			if(tmp==null){
				res+=" | "+' ';
			}else{
				res+=" | "+elementEn(i,j).toString();
			}
		}
		res+=" | "+"\n";
	}
	res+=" ";
	for(int k=0;k<XMAX;k++){
		res+="+---";
	}
	res+="+";	
	return res;
}
/**
 * 
 * @return si renvoie un négatif alors le jeu n'est pas fini, sinon il renvoie le numero d'équipe du vainqueur, 0 si il n'y a plus de robot sur le plateau pour juger.
 */
public int fini(){
	nettoyage();
	ArrayList<Robot> liste=getRobots();
	int equipe=0;
	int i=0;
	for(Robot r : liste){
		if (i==0){
			equipe=r.getEquipe();
			i+=1;
		}else{
			if(r.getEquipe()!=equipe){
				return -1;
			}
		}
	}
	return equipe;



}
	/**
	 * Cette fonction parcourt Dans une direction le plateau et tire sur l'élement Hors Mine qu'il croise en premier.
	 * @param robotDeDepart Robot depuis lequel commencer le parcours
	 * @param D direction dans laquelle le on parcourt
	 * 
	 **/
	public Element prochainElementDansUneDirectionNonMineAvecPortee(Robot robotDeDepart,Direction D){
		int posX=robotDeDepart.getX();
		int posY=robotDeDepart.getY();
		int distance=0;
		
		do{
			//TODO fix ici une certaine confusion de direction
			posX+=D.y;
			posY+=D.x;
			distance+=1;
			if(baseEn(posX,posY)!=null){
				if(robotDeDepart.tire(posX, posY)){
					robotDeDepart.setPeutAgir(false);
					return baseEn(posX,posY);
				}
			}
			if(obstacleEn(posX, posY) != null){
				if(robotDeDepart.tire(posX, posY)){
					robotDeDepart.setPeutAgir(false);
					return obstacleEn(posX,posY);
				}
			}
			if(robotEn(posX, posY)!= null){
				if(robotDeDepart.tire(posX, posY)){
					robotDeDepart.setPeutAgir(false);
					robotEn(posX, posY).sePrendTir();
					return robotEn(posX,posY);
				}
			}
			
		}while(distance<robotDeDepart.getPortee());
		robotDeDepart.setPeutAgir(false);
		robotDeDepart.setEnergie(robotDeDepart.getEnergie()-robotDeDepart.getCoutAction());
		return null;
	}

}
