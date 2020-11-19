import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
	int i;
	int j;

	public ActionBoton(VentanaPrincipal vPrincipal,int i,int j) {
		ventana = vPrincipal;
		this.i = i;
		this.j = j;
	}
	
	/**
	 *Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		ventana.mostrarNumMinasAlrededor(i,j);	
		

	}

}
