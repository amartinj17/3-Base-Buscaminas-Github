import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * De alguna manera tendrá que poder acceder a la ventana principal.
 * Se puede lograr pasando en el constructor la referencia a la ventana.
 * Recuerda que desde la ventana, se puede acceder a la variable de tipo ControlJuego
 * @author AlbertoMartin
 **
 */
public class ActionBoton implements ActionListener{

	VentanaPrincipal ventana;

	public ActionBoton(VentanaPrincipal vPrincipal) {
		ventana = vPrincipal;
	}
	
	/**
	 *Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bPulsado = (JButton)e.getSource();
		int i=0,j=0;
		for(int k=0 ; k<ventana.panelesJuego.length ;k++){
			for(int l=0 ; l<ventana.panelesJuego.length ;l++){
				if(bPulsado == ventana.botonesJuego[k][l]){
					i = k;
					j = l;
				}
			}
		}
		ventana.mostrarNumMinasAlrededor(i,j);	

	}

}
