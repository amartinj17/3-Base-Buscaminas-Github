package codigo;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.*; 

/* import sun.launcher .resources.launcher; DA ERROR*/

/**
 * Clase gestora del tablero de juego.
 * Guarda una matriz de enteros representado el tablero.
 * Si hay una mina en una posición guarda el número -1
 * Si no hay una mina, se guarda cuántas minas hay alrededor.
 * Almacena la puntuación de la partida
 * @author AlbertoMartin
 * @since 1.0
 * @version 1.0
 */
public class ControlJuego {
	//Atributo que guarda un objetoVentanaPrincipal
	private VentanaPrincipal ventana;
	/**"Marca" de que hay una mina en la casilla */
	private final static int MINA = -1;
	/** Atributo que guarda el valor del lado del tablero*/
	int LADO_TABLERO;
	/**Número de minas iniciales */
	final int MINAS_INICIALES; 

	//atributo que guarda una matriz de valores
	private int [][] tablero;
	//Atributo que guarda la puntuación 
	private int puntuacion;

	ArrayList<Integer> listaMinasI;//Array que guarda la I de las minas 
	ArrayList<Integer> listaMinasJ;//Array que guarda la J de las minas 

	ArrayList<Integer> lista0I;//Array que guarda la I de los 0 
	ArrayList<Integer> lista0J;//Array que guarda la J de los 0

	/**
	 * @return el objeto tablero que se usará durante toda la partida
	 */
	public int[][] getTablero() {
		return tablero;
	}
	
	/**
	 * Método que genera valores para todas las constantes de la clase ControlJuego
	 * @param v
	 * @param tam
	 */
	public ControlJuego(VentanaPrincipal v,int tam) {
		//Creamos el tablero:
		LADO_TABLERO = tam;
		MINAS_INICIALES = Math.round((float)((LADO_TABLERO*LADO_TABLERO)*0.2));
		tablero = new int[LADO_TABLERO][LADO_TABLERO];
		
		//Guardar VentanaPrincipal
		ventana = v;


		//Inicializamos una nueva partida
		inicializarPartida();
		depurarTablero(); 
	}
	
	
	/**Método para generar un nuevo tablero de partida:
	 * @pre: La estructura tablero debe existir. 
	 * @post: Al final el tablero se habrá inicializado con tantas minas como marque la variable MINAS_INICIALES. 
	 * 			El resto de posiciones que no son minas guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida(){
		Random r = new Random();
		boolean hayMina;
		int iAux,jAux;
		listaMinasI = new  ArrayList<>();
		listaMinasJ = new  ArrayList<>();
		
		//poner todas las posiciones del tablero a 0
		for(int l=0 ; l<LADO_TABLERO ; l++){
			for(int m=0 ; m<LADO_TABLERO ; m++){
				tablero[l][m] = 0; 
			}
		}
		//poner puntuacion a 0
		puntuacion = 0;
		//Colocar minas de forma aleatoria donde no haya ninguna ya
		for(int k=0 ; k<MINAS_INICIALES ; k++){
			do{
				iAux = (r.nextInt(LADO_TABLERO));
				jAux = (r.nextInt(LADO_TABLERO));

				if(tablero[iAux][jAux] == MINA){//Si hay mina calcula otra poscicion
					hayMina = true;
				}else{//Si no hay mina, añade una y sale del bucle
					hayMina = false;
					tablero[iAux][jAux] = MINA; 
					listaMinasI.add(iAux);     
					listaMinasJ.add(jAux);        
				}
			}while(hayMina);
		}

		//Al final del método hay que guardar el número de minas para las casillas que no son mina:
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA){
					tablero[i][j] = calculoMinasAdjuntas(i,j);
				}
			}
		}
	}
	
	/**Cálculo de las minas adjuntas: 
	 * Para calcular el número de minas tenemos que tener en cuenta que no nos salimos nunca del tablero.
	 * Por lo tanto, como mucho la i y la j valdrán LADO_TABLERO-1.
	 * Por lo tanto, como poco la i y la j valdrán 0.
	 * @param i: posición vertical de la casilla a rellenar
	 * @param j: posición horizontal de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int i, int j){
		int minas = 0;
		int vertical,horizontal;
		for(vertical = (i-1); vertical <= (i+1); vertical++){
			for(horizontal = (j-1); horizontal <=(j+1) ; horizontal++){
				
				if(vertical>=0 && vertical<=LADO_TABLERO-1 && horizontal>=0 && horizontal<=LADO_TABLERO-1){
					if(tablero[vertical][horizontal] == MINA){
						minas++;
					}
				}
			}
		}
		
		return minas;
	}
		
	/**
	 * Método que nos permite 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j){
		if(tablero[i][j] != MINA){// NO hay bomba
			puntuacion ++;
			return true;
		}else{//SI hay bomba
			return false;
		}
	}

	/**
	 * Método que checkea si se ha terminado el juego porque se han abierto todas las casillas.
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son minas.
	 **/
	public boolean esFinJuego(){
		int ptosTotales = (LADO_TABLERO*LADO_TABLERO)-MINAS_INICIALES;
		if(getPuntuacion() == ptosTotales){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza para depurar
	 */
	public void depurarTablero(){
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuación: "+puntuacion);
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta calcularlo, símplemente consultarlo
	 * @param i : posición vertical de la celda.
	 * @param j : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return tablero[i][j];
	}
	
	/**
	 * Método que devuelve la puntuación actual
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Muestra todas las minas con su imagen
	 */
	public void mostrarSoloMinas(){

		ImageIcon imageMina = new ImageIcon("src/codigo/Imagenes/mina.png"); 
		JLabel jLmina;

		for(int i=0 ; i<listaMinasI.size() ; i++){ 
			jLmina = new JLabel(imageMina,SwingConstants.CENTER);
			ventana.panelesJuego[listaMinasI.get(i)][listaMinasJ.get(i)].removeAll();
			ventana.panelesJuego[listaMinasI.get(i)][listaMinasJ.get(i)].add(jLmina); 
		}
		ventana.refrescarPantalla(); 
	}
}
