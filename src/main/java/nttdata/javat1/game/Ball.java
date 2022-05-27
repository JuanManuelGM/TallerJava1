package nttdata.javat1.game;

/**
 * Taller práctico java T1
 * 
 * Clase Ball para el objeto bola
 * 
 * @author Juan Manuel Gil Medina
 *
 */

public class Ball {

	static int x =15;
	static int y =16;
	static String dir = "up";
	
	
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	public static void setX(int x) {
		Ball.x = x;
	}
	
	public static void setY(int y) {
		Ball.y = y;
	}
	
	public static String getDir() {
		return dir;
	}
	
	public static void setDir(String dir) {
		Ball.dir = dir;
	}
}
