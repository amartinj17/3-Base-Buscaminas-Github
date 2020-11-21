import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import java.awt.event.*;
import java.util.ArrayList;

public class EscuchaRaton implements MouseListener{
    

    ImageIcon imageBandera;
    JLabel jLbandera;
    ArrayList<Integer> lBanderaI = new ArrayList<>();
    ArrayList<Integer> lBanderaJ = new ArrayList<>();
    int numeroBanderas;


    VentanaPrincipal ventana;
    int i;
    int j; 

    public EscuchaRaton(VentanaPrincipal vp , int i , int j){
        this.ventana = vp;
        this.i = i;
        this.j = j; 
        numeroBanderas = ventana.juego.MINAS_INICIALES;//Habrá un máximo de banderas igual al número de mians totales
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        boolean bandera  = false;
        JButton botonBandera;
		if(SwingUtilities.isRightMouseButton(e)){
            for(int k=0 ; k>lBanderaI.size() ; k++){
                if((ventana.panelesJuego[i][j]) == (ventana.panelesJuego[lBanderaI.get(i)][lBanderaJ.get(j)])){
                    bandera = true;
                    lBanderaI.remove(i);
                    lBanderaJ.remove(j);
                }else{
                    bandera = false;
                }
            }

			if(bandera){
                ventana.panelesJuego[i][j].removeAll();
                ventana.panelesJuego[i][j].add(ventana.botonesJuego[i][j]);
            }else{
                imageBandera = new ImageIcon("Imagenes/bandera.jpg");
			    jLbandera = new JLabel(imageBandera,SwingConstants.CENTER);
			    ventana.panelesJuego[i][j].removeAll();
                ventana.panelesJuego[i][j].add(jLbandera); 
                lBanderaI.add(i);
                lBanderaJ.add(j);
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