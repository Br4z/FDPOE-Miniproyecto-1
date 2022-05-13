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
 *  CLASE:     VentanaConfiguracion   
 *  INTENCION: Ser ventana inicial(donde se configura todo) del casino
 *  RELACION:  NINGUNA 
 */

public class VentanaConfiguracion extends JFrame {
    private int          modoDeJuego;
    private String       nombreJugador1;
    private String       nombreJugador2 = "Maquina";
    private Header       jpHeader;
    private JLabel       lblModo;
    private JLabel       lblLanzamientos;
    private JLabel       lblNombreJugador1;
    private JTextField   txtJugador1;
    private JLabel       lblNombreJugador2;
    private JTextField   txtJugador2;    
    private JSpinner     spinnerRonda;
    private JRadioButton btnJugadorVsJugador;
    private JRadioButton btnJugadorVsMaquina;
    private JButton      btnJugar;
    private JPanel       panelJugadores;
    private JPanel       panelLanzamientos;
    private JPanel       panelModo;
    private JPanel       panelBoton;
    private JPanel       panelCentro;
    
    public VentanaConfiguracion() { // Constructor
        iniciarComponentes();        
        setSize(450, 400);
        setTitle("Casino Univalle - Configuración");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Posición de la ventana en el centro
        setResizable(false);
    }
    
    private void iniciarComponentes() {
        
        Toolkit pantalla = Toolkit.getDefaultToolkit(); 
        // Para establecer el icono en la aplicación
        Image icono = pantalla.getImage("src/Imagenes/icon.png");
	setIconImage(icono);


        // Configuración ventana       
        panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(5, 1)); // Vamos a agrupar paneles dentro de un "grid"

        jpHeader = new Header("/Imagenes/Header.jpg");
        jpHeader.setSize(400, 200);
        
        lblModo           = new JLabel("Modo de juego: ");

        lblLanzamientos   = new JLabel("Numero de lanzamientos en la ronda: ");

        lblNombreJugador1 = new JLabel("Nombre jugador 1: ");       
        lblNombreJugador2 = new JLabel("Nombre jugador 2: ");

        txtJugador1       = new JTextField(15);       
        txtJugador2       = new JTextField(15);

        SpinnerNumberModel modelo = new SpinnerNumberModel(1.0, 1.0, 100.0, 1.0);
        // En el modelo Default - lowest value - highest value - increment 
        spinnerRonda = new JSpinner(modelo);

        btnJugadorVsJugador = new JRadioButton("Jugador vs Jugador");
        btnJugadorVsMaquina = new JRadioButton("Jugador vs Maquina");
        
        btnJugar            = new JButton("Jugar");

        panelJugadores = new JPanel();
        panelJugadores.setLayout(new GridLayout(2, 2));
        panelJugadores.add(lblNombreJugador1);
        panelJugadores.add(txtJugador1);
        panelJugadores.add(lblNombreJugador2);
        panelJugadores.add(txtJugador2);

        panelLanzamientos = new JPanel();
        panelLanzamientos.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelLanzamientos.add(lblLanzamientos);
        panelLanzamientos.add(spinnerRonda);

        panelModo = new JPanel();
        panelModo.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelModo.add(lblModo);
        panelModo.add(btnJugadorVsJugador);
        panelModo.add(btnJugadorVsMaquina);
        
        panelBoton = new JPanel();
        panelBoton.setLayout(new BorderLayout());
        panelBoton.add(btnJugar, BorderLayout.SOUTH);
        
        // Lo agregamos en el orden que queramos que aparezca
        panelCentro.add(jpHeader);
        panelCentro.add(panelModo);
        panelCentro.add(panelLanzamientos);
        panelCentro.add(panelJugadores);
        panelCentro.add(panelBoton);

        add(panelCentro);  
        
        // Asignamos el listener
        btnJugadorVsJugador.addActionListener(new manejadorEventos());
        btnJugadorVsMaquina.addActionListener(new manejadorEventos());
        btnJugar.addActionListener(new manejadorEventos());
    }
    
    private class manejadorEventos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent elemento) {           
            // Vamos a hacer todas las comprobaciones para que el juego se inicie correctamente           
            // 1ero, si selecciona un modo de juego, que deshabilite el otro
            if(elemento.getSource() == btnJugadorVsJugador){
                if(!btnJugadorVsMaquina.isEnabled()) {
                    btnJugadorVsMaquina.setEnabled(true);
                } else {
                    modoDeJuego = 0;
                    btnJugadorVsMaquina.setEnabled(false);
                } 
            } else if(elemento.getSource() == btnJugadorVsMaquina) { // No pueden ser presionados al mismo tiempo
                if(!btnJugadorVsJugador.isEnabled()) {
                    btnJugadorVsJugador.setEnabled(true);
                    txtJugador2.setEnabled(true);
                } else {
                    modoDeJuego = 1;
                    btnJugadorVsJugador.setEnabled(false);
                    txtJugador2.setEnabled(false);
                }
            }
            
            if(elemento.getSource() == btnJugar) {
                // 2do que haya seleccionado un modo de juego
                if(btnJugadorVsJugador.isEnabled() && btnJugadorVsMaquina.isEnabled()) {
                    
                    JOptionPane.showMessageDialog(null, "Seleccione un modo de juego !");
                    
                } else {
                    nombreJugador1 = txtJugador1.getText();
                    
                    if(modoDeJuego == 0) {
                        nombreJugador2 = txtJugador2.getText();
                    }
                    
                    int numeroLanzamientos = (int)(double) spinnerRonda.getValue();
                    
                    Jugador jugador1 = new Jugador(nombreJugador1);
                    Jugador jugador2 = new Jugador(nombreJugador2);
                    Ronda ronda = new Ronda(modoDeJuego, jugador1, jugador2, numeroLanzamientos);
                    dispose(); // Cerrar la ventana
                    VentanaJuego ventanaJuego = new VentanaJuego(ronda);
                }
            }
        }
        
    }
}