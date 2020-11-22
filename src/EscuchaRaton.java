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
    JButton boton;

    JButton botonBandera;
    ArrayList<Integer> lBanderaI;
    ArrayList<Integer> lBanderaJ;

    VentanaPrincipal ventana;
    int i;
    int j; 

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

    @Override
    public void mouseReleased(MouseEvent e) {
        boolean bandera  = false;
        
        
		if(SwingUtilities.isRightMouseButton(e)){
            for(int k=0 ; k<lBanderaI.size() ; k++){
                if((ventana.panelesJuego[i][j]) == (ventana.panelesJuego[lBanderaI.get(k)][lBanderaJ.get(k)])){
                    bandera = true;
                    lBanderaI.remove(k);
                    lBanderaJ.remove(k);
                }
            }
            
            
			if(bandera){
                
                ventana.panelesJuego[i][j].removeAll();
                ventana.panelesJuego[i][j].add(ventana.botonesJuego[i][j]);
            }else{

                lBanderaI.add(i);
                lBanderaJ.add(j);

                imageBandera = new ImageIcon("Imagenes/bandera.jpg");
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