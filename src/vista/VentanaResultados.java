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
import java.awt.event.*;
import javax.swing.*;

import modelos.*;

/**
 *  CLASE:     VentanaResultados   
 *  INTENCION: Ventana donde se muestran los resultado de una ronda del casino
 *  RELACION:  NINGUNA 
 */
//jugar de nuevo 
//volver a jugar 

public class VentanaResultados extends JFrame {
    private Ronda     rondaJugada;
    private JLabel    lblEmpates;
    private JLabel    lblRondas;
    //private JLabel    lblNumeroRonda;
    private JLabel    lblGanador;
    private JLabel    lblPuntaje;
    private JLabel    lbllanzamientosGanador;
    private JLabel    lbllanzamientosPerdedor;
    private JLabel    lblSumaLanzamientosGanador;
    private JLabel    lblSumaLanzamientosPerdedor;  
    private Container contenedorFinal;
    private JPanel    panelPrincipal;
    private JPanel    panelBajo;
    private JPanel    panelMedio;
    private JPanel    panelMediobajo;
    private JPanel    panelAlto;
    private JButton   btnJugarDeNuevo;
    private JButton   btnNuevoJuego;
    
    
    public VentanaResultados (Ronda rondaJugada) {
        establecerDatos(rondaJugada);
        iniciarComponentes();
        setSize(600, 500);
        setTitle("Casino Univalle - final");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
    }
    
    private void iniciarComponentes() {
        
        Toolkit pantalla = Toolkit.getDefaultToolkit(); 
        // Para establecer el icono en la aplicacion
        Image icono = pantalla.getImage("src/Imagenes/icon.png");
	setIconImage(icono);

        panelAlto = new JPanel();   
        panelAlto.setLayout(new FlowLayout(FlowLayout.CENTER, 80,0));
        panelAlto.add(lblEmpates);
        panelAlto.add(lblRondas);
        //panelAlto.add(lblNumeroRonda);    

        panelMedio = new JPanel();    
        panelMedio.setLayout(new FlowLayout(FlowLayout.CENTER,60 ,0));
        panelMedio.add(lblGanador);
        panelMedio.add(lbllanzamientosGanador);
        panelMedio.add(lblPuntaje);

        panelMediobajo = new JPanel();
        panelMediobajo.setLayout(new FlowLayout(FlowLayout.CENTER,60 , 0));
        panelMediobajo.add(lblSumaLanzamientosPerdedor);
        panelMediobajo.add(lbllanzamientosPerdedor);
        panelMediobajo.add(lblSumaLanzamientosGanador);

        panelBajo = new JPanel();   
        panelBajo.setLayout(new FlowLayout(FlowLayout.CENTER,60 ,0));
        panelBajo.add(btnJugarDeNuevo);
        panelBajo.add(btnNuevoJuego);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(4,1));
        panelPrincipal.add(panelAlto);
        panelPrincipal.add(panelMedio);
        panelPrincipal.add(panelMediobajo);
        panelPrincipal.add(panelBajo);

        contenedorFinal = getContentPane();    
        contenedorFinal.add(panelPrincipal);

        btnJugarDeNuevo.addActionListener(new manejadorEventos());
        btnNuevoJuego.addActionListener(new manejadorEventos());  
    }
    
    private void establecerDatos(Ronda rondaJugada) {
        
        this.rondaJugada = rondaJugada;
        
        Jugador jugador1 = rondaJugada.getJugador(1);
        Jugador jugador2 = rondaJugada.getJugador(2);
        Jugador ganador, perdedor;
        
        if(jugador1.getPuntaje() > jugador2.getPuntaje()) {
            ganador = jugador1;
            perdedor = jugador2;
        } else {
            ganador = jugador2;
            perdedor = jugador1;
        }
        
        lblEmpates = new JLabel("Empates: " + rondaJugada.getEmpates());
        lblRondas = new JLabel("Lanzamientos realizados: " + rondaJugada.getLanzamientos());
        lblGanador = new JLabel("Ganador: " + ganador.getNombre());
        lblPuntaje = new JLabel("Puntaje: " + ganador.getPuntaje()) ;
        lbllanzamientosGanador = new JLabel("Lanzamientos ganador: " + ganador.getLanzamientosHechos()); 
        lbllanzamientosPerdedor = new JLabel("Lanzamientos perdedor: " + perdedor.getLanzamientosHechos());
        lblSumaLanzamientosGanador = new JLabel("Puntaje ganador: " + ganador.getPuntaje());
        lblSumaLanzamientosPerdedor = new JLabel("Puntaje perdedor: " + perdedor.getPuntaje());

        btnJugarDeNuevo = new JButton("Jugar de nuevo");
        btnNuevoJuego = new JButton("Nuevo juego");
    } 
        private class manejadorEventos implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent elemento) {
                dispose(); // Con cualquiera de los dos botones se cierra la ventana resultados
                
                if(elemento.getSource() == btnJugarDeNuevo) {
                    rondaJugada.setLanzamientosRestantes(rondaJugada.getLanzamientos()); // Reseteamos
                    // solo los lanzamientos restantes
                    VentanaJuego ventanaJuego = new VentanaJuego(rondaJugada);
                    
                } else { // Presiono nuevo juego
                    VentanaConfiguracion ventanaConfiguracion = new VentanaConfiguracion();
                }
            }
        }
}
