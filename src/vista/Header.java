/*
                 *´¨) 
                ¸.•´ ¸.•´¸.•*´¨) ¸.•*¨) 
                (¸.•´ (¸.•` ¤ Brandon Calderón Prieto  
       .---.         
      /     \   calderon.brandon@correounivalle.edu.co     
      \.@-@./                 202125974 
      /`\_/`\      jose.palma@correounivalle.edu.co    
     //  _  \\                202125182
    | \     )|_        Ingeniería de sistemas
   /`\_`>  <_/ \
   \__/'---'\__/
 */

package vista;

import java.awt.*;
import javax.swing.*;


/**
 *  CLASE:     Header
 *  INTENCION: Representar un header(cabecera)
 *  RELACION:  NINGUNA 
 */

class Header extends JPanel {
    ImageIcon imagen;
    String nombre;

    public Header(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void paint(Graphics g) {
        imagen = new ImageIcon(getClass().getResource(nombre));
        g.drawImage(imagen.getImage(), 0, 0, null);
        setOpaque(false);
        super.paint(g);
    }
}
