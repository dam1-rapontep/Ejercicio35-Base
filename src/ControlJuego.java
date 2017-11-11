import java.util.ArrayList;
import java.util.Random;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.omg.IOP.Codec;

/**
 * Clase gestora del tablero de juego. Guarda una matriz de enteros representado
 * el tablero. Si hay una mina en una posición guarda el número -1 Si no hay
 * una mina, se guarda cuántas minas hay alrededor. Almacena la puntuación de
 * la partida
 * 
 * @author jesusredondogarcia
 *
 */
public class ControlJuego {

	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;
	int contadorNoMinas = 0;
	private int[][] tablero;
	private int puntuacion = 0;

	public ControlJuego() {
		// Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];

		// Inicializamos una nueva partida
		inicializarPartida();
	}

	/**
	 * Método para generar un nuevo tablero de partida:
	 * 
	 * @pre: La estructura tablero debe existir.
	 * @post: Al final el tablero se habrá inicializado con tantas minas como
	 *        marque la variable MINAS_INICIALES. El resto de posiciones que no son
	 *        minas guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida() {
		ArrayList<String> posiciones = new ArrayList<>();
		Random randomI = new Random();
		Random randomJ = new Random();
		int minas;

		for (int i = 0; i < MINAS_INICIALES; i++) {
			int fila = randomI.nextInt(10);
			int columna = randomJ.nextInt(10);
			if (tablero[fila][columna] == -1) {
				fila = randomI.nextInt(10);
				columna = randomJ.nextInt(10);
				tablero[fila][columna] = -1;
			}
			tablero[fila][columna] = -1;

		}
		depurarTablero();

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {

				if (tablero[i][j] != -1) {
					minas = calculoMinasAdjuntas(i, j);
					tablero[i][j] = minas;
				}
			}

		}

	}

	/**
	 * Cálculo de las minas adjuntas: Para calcular el número de minas tenemos que
	 * tener en cuenta que no nos salimos nunca del tablero. Por lo tanto, como
	 * mucho la i y la j valdrán LADO_TABLERO-1. Por lo tanto, como mucho la i y la
	 * j valdrán como poco 0.
	 * 
	 * @param i:
	 *            posición verticalmente de la casilla a rellenar
	 * @param j:
	 *            posición horizontalmente de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int i, int j) {
		int numMinas = 0;
		for (int i2 = i - 1; i2 <= i + 1; i2++) {// Este bucle recorre desde los valores que les pasamos por parametro
			for (int j2 = j - 1; j2 <= j + 1; j2++) {
				try {
					if (tablero[i2][j2] == -1) {// y va contando las minas que se va encontrando y las devuelve cn
												// return
						numMinas++;
					}
				} catch (Exception e) {

				}

			}
		}
		return numMinas;
	}

	/**
	 * Método que nos permite
	 * 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por
	 *      el GestorJuego. Por lo tanto siempre sumaremos puntos
	 * @param i:
	 *            posición verticalmente de la casilla a abrir
	 * @param j:
	 *            posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j) {

		/*if (tablero[i][j] != -1) {
			puntuacion++;
			return true;

		}*/
		
		
		// si lo hago de esta manera si me pinta la mina de blanco
		for (int i2 = 0; i2 < tablero.length; i2++) {
			for (int k = 0; k < tablero[i2].length; k++) {
				if (tablero[i2][k] != -1) {
					puntuacion++;
					return true;
				}
				
			}
			
		}

		return false;

	}

	/**
	 * Método que checkea si se ha terminado el juego porque se han abierto todas
	 * las casillas.
	 * 
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son
	 *         minas.
	 **/
	public boolean esFinJuego() {

		if (puntuacion == MINAS_INICIALES) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza
	 * para depurar
	 */
	public void depurarTablero() {
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuación: " + puntuacion);
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * 
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta
	 *      calcularlo, símplemente consultarlo
	 * @param i
	 *            : posición vertical de la celda.
	 * @param j
	 *            : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		int mina = tablero[i][j];
		return mina;
	}

	/**
	 * Método que devuelve la puntuación actual
	 * 
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {

	}

}
