package codigo;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Ventana principal del Buscaminas
 * 
 * @author AlbertoMartin
 */
public class VentanaPrincipal {
	int i, j;
	// La ventana principal, en este caso, guarda todos los componentes:
	JFrame ventana;
	JTextField numMinas;
	JPanel panelEmpezar;
	JPanel panelPuntuacion;
	JPanel panelJuego;
	ArrayList<Integer> lBanderaI;
    ArrayList<Integer> lBanderaJ;

	// Todos los botones se meten en un panel independiente.
	// Hacemos esto para que podamos cambiar después los componentes por otros
	JPanel[][] panelesJuego;
	JButton[][] botonesJuego;

	// Correspondencia de colores para las minas:
	Color correspondenciaColores[] = { Color.BLACK, Color.CYAN, Color.GREEN, Color.ORANGE, Color.RED, Color.RED,
			Color.RED, Color.RED, Color.RED, Color.RED };

	JButton botonEmpezar;
	JTextField pantallaPuntuacion;

	int seleccion;

	// LA VENTANA GUARDA UN CONTROL DE JUEGO:
	ControlJuego juego;
	Boolean esPrimerJuego;

	// Constructor, marca el tamaño y el cierre del frame
	public VentanaPrincipal(Boolean esPrimerJuego) {
		ventana = new JFrame("BuscaMinas");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(preguntarTamano()){ // si es un tamaño grande, pone en pantalla completa 
			ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}else{//Si no, un tamaño normal
			ventana.setBounds(150, 50, 1000, 700);
		}
		this.esPrimerJuego = esPrimerJuego;
	}

	/**
	 * Método que recoge el dato del tamaño del lado y controla posibles excepciones
	 * @return : Atributo que guarda si es un número muy grande o no
	 */
	private boolean preguntarTamano() {
		Boolean grande = false;
		try {
			seleccion = Integer.valueOf(JOptionPane.showInputDialog("Indica el tamaño del lado del tablero (un número)")); 
			if(seleccion < 2){//El tamaño mínimo es 2, para que no haya errores con el número de minas
				seleccion = 2;
				grande = false;
			}
			if(seleccion > 35){//Si el tamaño es muy grande 
				seleccion = 35;
				grande = true;
			}
			juego = new ControlJuego(this,seleccion);
		}catch(Exception e) {
			preguntarTamano();
		}
		return grande;
	}

	// Inicializa todos los componentes del frame
	public void inicializarComponentes() {

		// Definimos el layout:
		ventana.setLayout(new GridBagLayout());

		// Inicializamos componentes
		panelEmpezar = new JPanel();
		panelEmpezar.setLayout(new GridLayout(1, 1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1, 1));
		panelJuego = new JPanel();
		panelJuego.setLayout(new GridLayout(juego.LADO_TABLERO, juego.LADO_TABLERO));
		numMinas = new JTextField(juego.MINAS_INICIALES);

		botonEmpezar = new JButton("Go!");
		pantallaPuntuacion = new JTextField(juego.getPuntuacion());
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);

		// Bordes y colores:
		numMinas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));

		// Colocamos los componentes:
		// AZUL
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		numMinas.setEditable(false);
		numMinas.setText("     "+juego.MINAS_INICIALES+" MINAS");
		ventana.add(numMinas, settings);
		// VERDE
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);
		// AMARILLO
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);
		// ROJO
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);

		// Paneles
		panelesJuego = new JPanel[juego.LADO_TABLERO][juego.LADO_TABLERO];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1, 1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}

		// Botones
		botonesJuego = new JButton[juego.LADO_TABLERO][juego.LADO_TABLERO];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}

		// BotónEmpezar:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);

		//Inicializo las variables lBanderaI y lBanderaJ;
		lBanderaI = new ArrayList<>() ;
		lBanderaJ = new ArrayList<>();

	}

	/**
	 * Método que inicializa todos los lísteners que necesita inicialmente el
	 * programa
	 */
	public void inicializarListeners() {
		
		ImageIcon carita = new ImageIcon("src/codigo/Imagenes/descarga.png");
		if(esPrimerJuego){
			botonEmpezar.addActionListener((f) -> {// Dar listeners a los botones en el momento en el que se da al botón de
												// empezar
				botonEmpezar.setIcon(carita);
				botonEmpezar.setText("");
				pantallaPuntuacion.setText(0+"");
				for (i = 0; i < juego.LADO_TABLERO; i++) { //Añade a los botones los dos listeners
					for (j = 0; j < juego.LADO_TABLERO; j++) {
						botonesJuego[i][j].addActionListener(new ActionBoton(this, i, j));
						botonesJuego[i][j].addMouseListener(new EscuchaRaton(this, i, j,lBanderaI,lBanderaJ));
					}
				}
			});
		}else{
			botonEmpezar.setIcon(carita);
			botonEmpezar.setText("");
			pantallaPuntuacion.setText(0+"");
			for (i = 0; i < juego.LADO_TABLERO; i++) { //Añade a los botones los dos listeners
				for (j = 0; j < juego.LADO_TABLERO; j++) {
					botonesJuego[i][j].addActionListener(new ActionBoton(this, i, j));
					botonesJuego[i][j].addMouseListener(new EscuchaRaton(this, i, j,lBanderaI,lBanderaJ));
				}
			}
		}
		
	}

	/**
	 * Pinta en la pantalla el número de minas que hay alrededor de la celda
	 * Saca el botón que haya en la celda determinada y añade un JLabel centrado y no editable con el número de minas alrededor.
	 * Se pinta el color del texto según la siguiente correspondecia (consultar la variable correspondeciaColor):
	 * - 0 : negro
	 * - 1 : cyan
	 * - 2 : verde
	 * - 3 : naranja
	 * - 4 ó más : rojo 
	 * @param i: posición vertical de la celda.
	 * @param j: posición horizontal de la celda.
	 */
	public void mostrarNumMinasAlrededor( int i , int j) { 
		JLabel label;
		int n;
		if(juego.abrirCasilla(i, j)){
			n = juego.getMinasAlrededor(i, j);
			label  = new JLabel(Integer.toString(n), SwingConstants.CENTER);
			
			label.setForeground(correspondenciaColores[n]);
			
			//seleccionar el panel[i][j] correspondiente
			//Eliminar todos los componentes
			panelesJuego[i][j].remove(botonesJuego[i][j]);
			//Añadir JLabel centrado 
			panelesJuego[i][j].add(label);
			actualizarPuntuacion();
			refrescarPantalla(); 
		}else{
			if(!juego.esFinJuego()){//Si es final de juego por mina, marca la mina con un borde rojo
				panelesJuego[i][j].setBorder(BorderFactory.createLineBorder(Color.red, 5));
				mostrarFinJuego(true);
			}
		}
		if(juego.esFinJuego()){//Si es fin de juego 
			mostrarFinJuego(false);
		}
	}

	/**
	 * Muestra una ventana que indica el fin del juego
	 * 
	 * @param porExplosion : Un booleano que indica si es final del juego porque ha
	 *                     explotado una mina (true) o bien porque hemos desactivado
	 *                     todas (false)
	 * @post : Todos los botones se desactivan excepto el de volver a iniciar el
	 *       juego.
	 */
	public void mostrarFinJuego(final boolean porExplosion) {
		juego.mostrarSoloMinas();
		if (porExplosion) {
			if ((JOptionPane.showConfirmDialog(ventana,"ERA UNA MINA :(\nPuntos: "+juego.getPuntuacion(),"¿Quieres volver a jugar?", JOptionPane.YES_NO_OPTION)) == 0) {
				ventana.dispose();
				VentanaPrincipal ventana = new VentanaPrincipal(false);
				ventana.inicializar();
			}else{
				ventana.dispose(); 
			}
		} else {
			if ((JOptionPane.showConfirmDialog(ventana,"GANASTE!! :)\nPuntos: "+juego.getPuntuacion(),"¿Quieres volver a jugar?", JOptionPane.YES_NO_OPTION)) == 0) {
				ventana.dispose();
				VentanaPrincipal ventana = new VentanaPrincipal(false);
				ventana.inicializar();
			}else{
				ventana.dispose();
			}
		}
	}
	

	/**
	 * Método que muestra la puntuación por pantalla.
	 */
	public void actualizarPuntuacion() {
		pantallaPuntuacion.setText(juego.getPuntuacion() + "");
	}

	/**
	 * Método para refrescar la pantalla
	 */
	public void refrescarPantalla() {
		ventana.revalidate();
		ventana.repaint();
	}

	/**
	 * Método que devuelve el control del juego de una ventana
	 * 
	 * @return un ControlJuego con el control del juego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * Método para inicializar el programa
	 */
	public void inicializar() {
		// IMPORTANTE, PRIMERO HACEMOS LA VENTANA VISIBLE Y LUEGO INICIALIZAMOS LOS COMPONENTES.
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();

	}

}
