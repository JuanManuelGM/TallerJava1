package nttdata.javat1.game;

/**
 * Taller practico java T1
 * 
 * Clase game (bloque principal)
 * 
 * @author Juan Manuel Gil Medina
 *
 */

public class Game {

	/**
	 * Declaracion de score para guardar la puntuacion de la partida
	 */
	static int score = 0;

	/**
	 * Declaracion del mapa (Array bidimensional de strings) para simular el mapa de
	 * juego
	 */
	static String[][] map = new String[18][18];

	/**
	 * Metodo createMap para definir y rellenar la estructura del mapa
	 */
	public static void createMap() {

		// Rellena el mapa con espacios
		for (int x = 0; x < 17; x++) {
			for (int y = 0; y < 18; y++) {
				map[x][y] = " ";
			}
		}

		// Borde inferior
		for (int i = 0; i < 18; i++) {
			map[17][i] = "_";
		}
		// Borde Izquierdo
		for (int i = 0; i < 18; i++) {
			map[i][0] = "|";
		}
		// Borde Derecho
		for (int i = 0; i < 18; i++) {
			map[i][17] = "|";
		}
		// Borde Superior
		for (int i = 0; i < 18; i++) {
			map[0][i] = "_";
		}

		// Esquinas
		map[0][0] = " ";
		map[1][0] = " ";
		map[0][1] = " ";
		map[2][0] = "/";
		map[1][1] = "/";

		map[0][17] = " ";
		map[1][17] = " ";
		map[0][16] = " ";
		map[1][16] = "\\";
		map[2][17] = "\\";

		map[17][0] = " ";
		map[17][1] = "\\";
		map[16][0] = "\\";

		map[17][17] = " ";
		map[17][16] = "/";
		map[16][17] = "/";

		// Decoracion
		map[15][1] = "\\";
		map[15][2] = "_";
		map[15][3] = "_";
		map[15][4] = "_";
		map[15][5] = "_";
		map[16][6] = "\\";
		map[16][9] = "/";
		map[15][10] = "_";
		map[15][11] = "_";
		map[15][12] = "_";
		map[15][13] = "_";
		map[15][14] = "/";
		map[14][15] = "/";
		map[16][16] = "|";
		for (int i = 13; i >= 3; i--) {
			map[i][15] = "|";
		}

		// Jugabilidad
		map[4][3] = "/";
		map[14][12] = "/";
		map[9][14] = "/";
		map[14][8] = "/";

		map[12][1] = "\\";
		map[14][3] = "\\";
		map[8][6] = "\\";

		map[14][6] = "0";
		map[8][3] = "0";
		map[4][12] = "0";
		map[12][12] = "0";

		// Obejo O
		map[2][8] = "O";
		map[2][9] = ")";
		map[2][7] = "(";

	}

	/**
	 * Metodo locateBall, recoge x e y de Ball y pinta la bola en el mapa
	 */
	public static void locateBall() {
		map[Ball.getX()][Ball.getY()] = "o";
	}

	/**
	 * Metodo showMap recorre el Array y muestra por consola
	 */
	public static void showMap() {
		for (int x = 0; x < 18; x++) {
			for (int y = 0; y < 18; y++) {
				System.out.printf(map[x][y]);
			}
			// Espacio entre showMap y showMap
			System.out.println();
		}
		// Retrasa el showMap recrear velocidad de movimiento
		try {
			Thread.sleep(150);
		} catch (Exception e) {
			System.out.println("Error al esperar");
		}
		// Borrar la posicion de la bola una vez pintada para no dejar rastro en el
		// siguiente showMap
		map[Ball.getX()][Ball.getY()] = " ";
	}

	/**
	 * Metodo para asignar interaccion entre la bola y obstaculos (Automatiza el
	 * movimiento)
	 */
	public static void move() {
		while ((Ball.getX() != 16 && Ball.getY() != 7) || (Ball.getX() != 16 && Ball.getY() != 8)) {
			// Crea un bucle de movimiento hasta que la bola llegue a alguna de las
			// posiciones que terminan la partida

			// Suma 100 puntos al pasar por la posicion del objeto O
			if (Ball.getX() == 2 && Ball.getY() == 8) {
				score = score + 100;
			}

			// Recrea efectos de juego
			if (Ball.getX() == 14 && Ball.getY() == 16) {
				map[15][16] = "|";
			}
			if (Ball.getX() == 13 && Ball.getY() == 16) {
				map[14][16] = "|";
			}
			if (Ball.getX() == 12 && Ball.getY() == 16) {
				map[13][16] = "|";
			}
			if (Ball.getX() == 11 && Ball.getY() == 16) {
				map[15][16] = " ";
				map[14][16] = " ";
				map[13][16] = " ";
			}

			// Cierra el mapa una vez la bola es lanzada para encerrarla en el mapa
			if (Ball.getX() == 1 && Ball.getY() == 13) {
				map[2][15] = "\\";
				map[1][14] = "\\";
			}

			switch (Ball.getDir()) {
			// Primer switch, recoge la posibilidad de que la vola vaya en direccion up /
			// down / left / right

			case "up":
				// Caso dirección up

				switch (map[Ball.getX() - 1][Ball.getY()]) {
				// Segundo switch, dentro de esta direccion establece el movimiento de la bola
				// al chocar con / \ 0 O

				case " ":
					// Si choca con un espacio sigue adelante
					Ball.setX(Ball.getX() - 1);
					locateBall();
					showMap();
					break;

				case "\\":
					// Si choca con \ se mueve y cambia la direccion a left
					score = score - 1;
					Ball.setX(Ball.getX() - 1);
					Ball.setY(Ball.getY() - 1);
					Ball.setDir("left");
					locateBall();
					showMap();
					break;

				case "/":
					// Si choca con / se mueve y cambia la direccion a right
					score = score - 1;
					Ball.setX(Ball.getX() - 1);
					Ball.setY(Ball.getY() + 1);
					Ball.setDir("right");
					locateBall();
					showMap();
					break;

				case "_":
					// Si choca con _ se mueve y cambia la direccion a down
					Ball.setDir("down");
					locateBall();
					showMap();
					break;

				case "O":
					// Si choca con O cambia la direccion a down
					Ball.setDir("down");
					locateBall();
					showMap();
					break;

				case "0":
					// Caso 0 establece que al interactuar la bola con este objeto el "rebote" sea
					// aleatorio entre 3 posibilidades para crear aleatoriedad en las partidas
					score = score + 10;
					int randomNum = (int) (Math.random() * 3 + 1);
					switch (randomNum) {
					case 1:
						Ball.setX(Ball.getX() - 1);
						Ball.setY(Ball.getY() - 1);
						Ball.setDir("left");
						locateBall();
						showMap();
						break;
					case 2:
						Ball.setDir("down");
						locateBall();
						showMap();
						break;
					case 3:
						Ball.setX(Ball.getX() - 1);
						Ball.setY(Ball.getY() + 1);
						Ball.setDir("right");
						locateBall();
						showMap();
						break;
					}
					break;
				}
				break;

			case "down":
				// Caso direccion down

				switch (map[Ball.getX() + 1][Ball.getY()]) {
				// Segundo switch, dentro de esta direccion establece el movimiento de la bola
				// al chocar con / \ 0

				case " ":
					// Si choca con un espacio sigue adelante
					Ball.setX(Ball.getX() + 1);
					locateBall();
					showMap();
					break;

				case "\\":
					// Si choca con \ se mueve y cambia la direccion a right
					score = score - 1;
					Ball.setX(Ball.getX() + 1);
					Ball.setY(Ball.getY() + 1);
					Ball.setDir("right");
					locateBall();
					showMap();
					break;

				case "/":
					// Si choca con / se mueve y cambia la direccion a left
					score = score - 1;
					Ball.setX(Ball.getX() + 1);
					Ball.setY(Ball.getY() - 1);
					Ball.setDir("left");
					locateBall();
					showMap();
					break;

				case "_":
					// Si choca con _ se mueve y cambia la direccion a up
					Ball.setDir("up");
					locateBall();
					showMap();
					break;

				case "0":
					// Caso 0 establece que al interactuar la bola con este objeto el "rebote" sea
					// aleatorio entre 3 posibilidades para crear aleatoriedad en las partidas
					score = score + 20;
					int randomNum = (int) (Math.random() * 3 + 1);
					switch (randomNum) {
					case 1:
						Ball.setX(Ball.getX() + 1);
						Ball.setY(Ball.getY() - 1);
						Ball.setDir("left");
						locateBall();
						showMap();
						break;
					case 2:
						Ball.setDir("up");
						locateBall();
						showMap();
						break;
					case 3:
						Ball.setX(Ball.getX() + 1);
						Ball.setY(Ball.getY() + 1);
						Ball.setDir("right");
						locateBall();
						showMap();
						break;
					}
					break;
				}
				break;

			case "left":
				// Caso direccion left

				switch (map[Ball.getX()][Ball.getY() - 1]) {
				// Segundo switch, dentro de esta direccion establece el movimiento de la bola
				// al chocar con / \ 0 |

				case " ":
					// Si choca con un espacio sigue adelante
					Ball.setY(Ball.getY() - 1);
					locateBall();
					showMap();
					break;

				case "\\":
					// Si choca con \ se mueve y cambia la direccion a up
					score = score - 1;
					Ball.setX(Ball.getX() - 1);
					Ball.setY(Ball.getY() - 1);
					Ball.setDir("up");
					locateBall();
					showMap();
					break;

				case "/":
					// Si choca con / se mueve y cambia la direccion a down
					score = score - 1;
					Ball.setX(Ball.getX() + 1);
					Ball.setY(Ball.getY() - 1);
					Ball.setDir("down");
					locateBall();
					showMap();
					break;

				case "|":
					// Si choca con | cambia la direccion a right
					Ball.setDir("right");
					locateBall();
					showMap();
					break;

				case "0":
					// Caso 0 establece que al interactuar la bola con este objeto el "rebote" sea
					// aleatorio entre 3 posibilidades para crear aleatoriedad en las partidas
					score = score + 30;
					int randomNum = (int) (Math.random() * 3 + 1);
					switch (randomNum) {
					case 1:
						Ball.setX(Ball.getX() - 1);
						Ball.setY(Ball.getY() - 1);
						Ball.setDir("up");
						locateBall();
						showMap();
						break;
					case 2:
						Ball.setDir("right");
						locateBall();
						showMap();
						break;
					case 3:
						Ball.setX(Ball.getX() + 1);
						Ball.setY(Ball.getY() - 1);
						Ball.setDir("down");
						locateBall();
						showMap();
						break;
					}
					break;
				}
				break;

			case "right":
				// Caso direccion right

				switch (map[Ball.getX()][Ball.getY() + 1]) {
				// Segundo switch, dentro de esta direccion establece el movimiento de la bola
				// al chocar con / \ 0 |

				case " ":
					// Si choca con un espacio sigue adelante
					Ball.setY(Ball.getY() + 1);
					locateBall();
					showMap();
					break;

				case "\\":
					// Si choca con \ se mueve y cambia la direccion a down
					score = score - 1;
					Ball.setX(Ball.getX() + 1);
					Ball.setY(Ball.getY() + 1);
					Ball.setDir("down");
					locateBall();
					showMap();
					break;

				case "/":
					// Si choca con / se mueve y cambia la direccion a up
					score = score - 1;
					Ball.setX(Ball.getX() - 1);
					Ball.setY(Ball.getY() + 1);
					Ball.setDir("up");
					locateBall();
					showMap();
					break;

				case "|":
					// Si choca con | cambia la direccion a left
					Ball.setDir("left");
					locateBall();
					showMap();
					break;

				case "0":
					// Caso 0 establece que al interactuar la bola con este objeto el "rebote" sea
					// aleatorio entre 3 posibilidades para crear aleatoriedad en las partidas
					score = score + 40;
					int randomNum = (int) (Math.random() * 3 + 1);
					switch (randomNum) {
					case 1:
						Ball.setX(Ball.getX() - 1);
						Ball.setY(Ball.getY() + 1);
						Ball.setDir("up");
						locateBall();
						showMap();
						break;
					case 2:
						Ball.setDir("left");
						locateBall();
						showMap();
						break;
					case 3:
						Ball.setX(Ball.getX() + 1);
						Ball.setY(Ball.getY() + 1);
						Ball.setDir("down");
						locateBall();
						showMap();
						break;
					}
					break;
				}
				break;
			}
		}
		// Se muestra el mapa por ultima vez y la puntuacion de la partida
		showMap();
		System.out.println("-<Fin de la partida>-");
		System.out.println("Puntuación: " + score);
		// Fin del método move
	}

	/**
	 * Declaración del metodo launchAndStart para lanzar el juego desde Main
	 */
	public static void launchAndStart() {
		createMap();
		locateBall();
		showMap();
		move();
	}
}