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
import java.util.Scanner;
import verifSaisie.EntierPositifNonVide;
import javafx.application.Application;
import javafx.stage.Stage;

public class Jeu extends Application {
		
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		Plateau p=new Plateau(15,15,0);
		p.ajoutBase(1, 0, 0);
		p.ajoutBase(2, Plateau.getXMAX()-1, Plateau.getYMAX()-1);
		long nombreDeTours = -1; // Incremente a chaque tour de jeu
		p.ajoutChar(1, 0, 0,01);
		p.addMine(new Mine(2,0,1));
		p.addMine(new Mine(2,1,2));
		p.addMine(new Mine(2,2,2));
		p.ajoutPiegeur(1, 0, 0,01);
		p.ajoutTireur(1, 0, 0,01);
		p.ajoutCatmikaze(1, 0, 0,01);
		p.ajoutChar(2, Plateau.getXMAX()-1, Plateau.getYMAX()-1,01);
		p.ajoutPiegeur(2, Plateau.getXMAX()-1 , Plateau.getYMAX()-1,01);
		p.ajoutTireur(2, Plateau.getXMAX()-1, Plateau.getYMAX()-1,01);
		p.ajoutCatmikaze(2, Plateau.getXMAX()-1, Plateau.getYMAX()-1,01);
		//p.ajoutObstacle(1, 1);

		
		do{
			
			nombreDeTours=nombreDeTours+1;
			System.out.println(p);
			int equipeJouant=(int)(nombreDeTours%2)+1;
			ArrayList<Robot> listeRobotsEquipe=p.getRobots(equipeJouant);
			System.out.println("\n\n\n");
			System.out.println("Au tour de l'équipe: "+equipeJouant);
			System.out.println(p.details(equipeJouant));
			
			for(Robot r : listeRobotsEquipe){  // Action pour chaque robot de l'équipe
				
				if(p.estSurBase(r)){ 		   // Verifier s'il est sur une base de l'équipe
					r.regen();				   // Si c'est le cas, il se régénère
					
				}
				
				System.out.println(r.details()+r.particularite());   // On affiche ses détails
				String choix;
				String choix2;
				String choix3;
				
				do{
				System.out.println(" \n Que Voulez vous faire?\n"        //1er niveau de choix
						+"1 - Deplacer \n"
						+"2 - Autre (dépend du type de robot)\n"
						+"3 - Ne rien faire");
				choix=sc.nextLine();
				}while(!(EntierPositifNonVide.entre(choix, 1, 3))); //verification de ce qui a été entré
				
				if(choix.charAt(0)=='1'){ //Deplacer 
					int x;
					int y;
					do{
						do{
						System.out.println("Le robot "+r.getType()+" est actuellement en "+r.getX()+","+r.getY()+" ou voulez vous le deplacer en X?");
						choix2=sc.nextLine();
						}while(!EntierPositifNonVide.entre(choix2, 0, Plateau.getXMAX()-1));
						do{
						System.out.println("Le robot "+r.getType()+" est actuellement en "+r.getX()+","+r.getY()+" ou voulez vous le déplacer en Y?");
						choix3=sc.nextLine();
						}while(!EntierPositifNonVide.entre(choix3, 0, Plateau.getYMAX()-1));
						x=Integer.valueOf(choix2);
						y=Integer.valueOf(choix3);
						
					}while(!p.deplacerAll(r,x,y));
					
					
					
				}else if(choix.charAt(0)=='2'){  //Autre
					
					if(r.getType().equals("Char") || r.getType().equals("Tireur")){
						
						do{
							System.out.println("1- Tirer\n"
									+ "2 - Ne rien faire");
							choix2=sc.nextLine();
						}while(!(EntierPositifNonVide.entre(choix2, 1, 2)));
						
						if(choix2.charAt(0)=='1'){
							int x=Integer.MIN_VALUE;
							int y=Integer.MIN_VALUE;
							boolean tirAnnule=false;
							do{
							System.out.println("Coordonnées du tir voulu en x (Robot actuel en "+r.getX()+","+r.getY()+" ) (-1 pour annuler le tir)");
							choix3=sc.nextLine();
							}while(!((EntierPositifNonVide.entre(choix3, 0, Plateau.getYMAX()-1)) || choix3.equals("-1")));
							tirAnnule=(choix3.equals("-1")); //possibilité d'annuler le tir
							
								if(!tirAnnule){ //Si le tir n'est pas annulé
									x=Integer.valueOf(choix3);
									choix3=null;
									do{
									System.out.println("Coordonnées du tir voulu en y (Robot actuel en "+r.getX()+","+r.getY()+" ) (-1 pour annuler le tir)");
									choix3=sc.nextLine();
									}while(!((EntierPositifNonVide.entre(choix3, 0, Plateau.getYMAX()-1)) || choix3.equals("-1")));
									tirAnnule=(choix3.equals("-1"));
									if(!tirAnnule){
										y=Integer.valueOf(choix3);
									}
								}
							if(tirAnnule){
								System.out.println("Le tir a été annulé");
							}else if(!r.tire(x,y)){
								System.out.println("Le robot n'a pas pu tirer en "+x+","+y);
							}else{
								System.out.println("Le tir en "+x+","+y+" a ete un succes!");
								if(p.robotEn(x, y)!=null){
									p.robotEn(x, y).sePrendTir();
									System.out.println("Robot en "+x+","+y+" Touché ! Caractéristiques: \n"+p.robotEn(x, y).details());
								}else{
									System.out.println("Pas de robot aux coordonnées"+x+","+y);
								}
								
							}
							
							
						}
						else{
								// Option niveau 2 "Ne rien faire"
						}
				
						
						
					}else if (r.getType().equals("Piegeur")){
						Piegeur pi=(Piegeur)r;
						if(pi.getNombreMine()>0){
							do{
								System.out.println("1- Pieger\n"
									+ "2 - Ne rien faire");
								choix2=sc.nextLine();
							}while(!(choix2.charAt(0)=='1'|| choix2.charAt(0)=='2'));
						}else{
							choix2="2";
							System.out.println("Plus de mine! Rentrez à la base pour faire le plein");
						}
						if(choix2.charAt(0)=='1'){
							int x;
							int y;
							do{
								do{
									r=(Piegeur)r;
									do{
									System.out.println("Coordonnées du piege voulu en X ( robot en:"+r.getX()+","+r.getY()+" )");
									choix3=sc.nextLine();
									}while(!(EntierPositifNonVide.entre(choix3, 0, Plateau.getYMAX()-1)));
									x=Integer.valueOf(choix3);
									do{
									System.out.println("Coordonnées du piege voulu en Y ( robot en:"+r.getX()+","+r.getY()+")");
									choix3=sc.nextLine();
									}while(!(EntierPositifNonVide.entre(choix3, 0, Plateau.getYMAX()-1)));
									y=Integer.valueOf(choix3);
									
									
								}while(!r.peutTirer(x, y));
								if(p.caseOccupe(x, y)){
									System.out.println("Case déja prise par un élément");
								}
							}while(p.caseOccupe(x, y));
							if(r.tire(x, y)){
							p.ajoutMine(r.getEquipe(), x, y);
							}
						}else{
							// Niveau 2 ne rien faire
						}
						
					}else if (r.getType().equals("Catmikaze")){
						do{
							
							System.out.println("1- Se faire Peter\n"
									+ "2 - Ne rien faire");
							choix2=sc.nextLine();
						}while(!EntierPositifNonVide.entre(choix2,1,2));
						if(choix2.charAt(0)=='1'){
							int x;
							int y;
							do{
								do{
								System.out.println("Coordonnées de l'explosion voulue en X ( robot en:"+r.getX()+","+r.getY()+")\n");
								choix3="";
								
								choix3=sc.nextLine();
								}while(!(EntierPositifNonVide.verifie(choix3)));
								x=Integer.valueOf(choix3);
								do{
								System.out.println("Coordonnées de l'explosion voulue en Y ( robot en:"+r.getX()+","+r.getY()+")\n");
								choix3="";
								choix3=sc.nextLine();
								}while(!(EntierPositifNonVide.verifie(choix3)));
								y=Integer.valueOf(choix3);
								
						
							}while(!r.tire(x, y));
							if(p.robotEn(x, y)!=null){
								p.robotEn(x, y).setEnergie(0); //Le robot qui se le prend meurt aussi, s'il y en a un
							}
						}else{  //ne rien faire
					
						}
						
					}else{
						 // si ce n'est aucune sorte de robot
					}
				}
			}
			
			
			p.nettoyage();
		}while(p.fini()<0);
		
		System.out.println(p);
		System.out.println("le vainqueur est:"+p.fini());
		sc.close();
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		main(null);
		
	}
}
