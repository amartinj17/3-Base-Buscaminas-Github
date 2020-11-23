package codigo;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.*;
import java.util.ArrayList;
/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * Puede acceder a la ventana principal
 * @author AlbertoMartin
 * @since 1.0
 * @version 1.0
 * @see VentanaPrincipal
 */
public class EscuchaRaton implements MouseListener{
    
    /**Imagen de la bandera */
    ImageIcon imageBandera;
    /**Boton al que se añade la imagen de la bandera */
    JButton boton;
    /**Array que guarda la columna del boton pulsado, entra por parámetros*/
    ArrayList<Integer> lBanderaI;
    /**Array que guarda la fila del boton pulsado, entra por parámetros*/
    ArrayList<Integer> lBanderaJ;
    /**
	 * {@link VentanaPrincipal} que recibirá por parámetros para acceder al control
	 * de juego
	 */
	VentanaPrincipal ventana;
	/** Columna en la que se encuentra el botón */
	int i;
	/** Fila en la que se encuentra el botón */
	int j;
	
	/**
     * 
     * @param vp         objeto VentanaPrincipal 
     * @param i          numero de la columna
     * @param j          numero de la fila
     * @param lBanderaI  numero de la columna de la bandera
     * @param lBanderaJ  numero de la fila de la bandera
     */
    public EscuchaRaton(VentanaPrincipal vp , int i , int j,ArrayList<Integer> lBanderaI, ArrayList<Integer> lBanderaJ){
        this.ventana = vp;
        this.i = i;
        this.j = j; 
        this.lBanderaI = lBanderaI;
        this.lBanderaJ = lBanderaJ;
        
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * acción que se lleva a cabo al pulsar el clic derecho en una casilla que aún no está abierta
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        boolean bandera  = false;
        
		if(SwingUtilities.isRightMouseButton(e)){
            for(int k=0 ; k<lBanderaI.size() ; k++){
                if((ventana.panelesJuego[i][j]) == (ventana.panelesJuego[lBanderaI.get(k)][lBanderaJ.get(k)])){//Si el botón pulsado coincide con otro de una lista de botones con bandera
                    bandera = true;
                    lBanderaI.remove(k);
                    lBanderaJ.remove(k);
                }
            }
			if(bandera){ // Si tiene bandera, se la quita y se borra de la lista de banderas
                ventana.panelesJuego[i][j].removeAll();
                ventana.panelesJuego[i][j].add(ventana.botonesJuego[i][j]);
            }else{// Si no tiene bandera, se la pone y añade a las listas de botones con bandera esta posicion
                lBanderaI.add(i);
                lBanderaJ.add(j);

                imageBandera = new ImageIcon("src/codigo/Imagenes/bandera.jpg");
                boton = new JButton();
                boton.setIcon(imageBandera);
                boton.addMouseListener(new EscuchaRaton(ventana, i, j, lBanderaI, lBanderaJ));
                
                ventana.panelesJuego[i][j].removeAll();
                ventana.panelesJuego[i][j].add(boton);
            } 
            ventana.refrescarPantalla();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    
}