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
package musiques;
import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Musiques {
	private static HashMap<String,MediaPlayer> musiques=new HashMap<String,MediaPlayer>();;
	private static Media intro;
	private static Media charTir;
	private static Media tireurTir;
	private static Media mineExplo;
	private static Media minepose;
	private static Media go;
	private static Media dead;
	private static Media start;
	private static Media t1win;
	private static Media t2win;
	private static Media draw;
	private static Media catmikazeExplo;
	public Musiques(){
		
		try{
		start=new Media("http://virtualwar.marmat.ovh/start.mp3");
		intro=new Media("http://virtualwar.marmat.ovh/main.mp3");
		charTir=new Media("http://virtualwar.marmat.ovh/charTir.mp3");
		tireurTir=new Media("http://virtualwar.marmat.ovh/tireurTir.mp3");
		mineExplo=new Media("http://virtualwar.marmat.ovh/mine.mp3");
		minepose=new Media("http://virtualwar.marmat.ovh/c4_plant.wav");
		go=new Media("http://virtualwar.marmat.ovh/go.mp3");
		dead=new Media("http://virtualwar.marmat.ovh/1Dead.wav");
		t1win=new Media("http://virtualwar.marmat.ovh/ctwin.wav");
		t2win=new Media("http://virtualwar.marmat.ovh/terwin.wav");
		draw=new Media("http://virtualwar.marmat.ovh/rounddraw.wav");
		catmikazeExplo=new Media("http://virtualwar.marmat.ovh/CatmikazeExplo.mp3");
		musiques.put("musique", new MediaPlayer(intro));
		musiques.put("Char Tir", new MediaPlayer(charTir));
		musiques.put("Tireur Tir", new MediaPlayer(tireurTir));
		musiques.put("Mine Explo",new MediaPlayer(mineExplo));
		musiques.put("Mine Pose", new MediaPlayer(minepose));
		musiques.put("go", new MediaPlayer(go));
		musiques.put("start", new MediaPlayer(start));
		musiques.put("Dead", new MediaPlayer(dead));
		musiques.put("t1", new MediaPlayer(t1win));
		musiques.put("t2", new MediaPlayer(t2win));
		musiques.put("draw", new MediaPlayer(draw));
		musiques.put("catmikazeExplo", new MediaPlayer(catmikazeExplo));
		}catch(Exception e){
			System.err.println("Exception sur la préparation des sons et musiques:");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @return Les musiques du jeu
	 */
	public HashMap<String,MediaPlayer> getMusiques(){
		return musiques;
	}
	
	/**
	 * 
	 * @param s nom de la musique
	 * @return la musique associée à ce nom
	 */
	public static MediaPlayer getMediaPlayer(String s){
		
		return musiques.get(s);
	}
	
}
