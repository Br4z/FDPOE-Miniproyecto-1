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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import modelos.*;


/**
 *  CLASE:     VentanaJuego   
 *  INTENCION: Ser la ventana donde transcurre un juego del casino
 *  RELACION:  NINGUNA 
 */

public class VentanaJuego extends JFrame {
    // Para el cronometro
    private Timer   tiempo;      
    private int     h = 0, m = 0, s = 0;
    private int     lanzamientoAnterior = 0; // Para llevar el control sobre los empates
    private int     turno = 1; // Empieza el jugador 1
    private Ronda   ronda;
    private JLabel  lblTiempo;    
    private JLabel  lblLanzamientosRonda;
    private JLabel  lblLanzamientosRestantes;
    private JLabel  lblJugadorLanzador;
    private JLabel  lblLanzamientosEmpate;
    private JLabel  lblDado1;
    private JLabel  lblDado2;
    private JLabel  lblParcialGanador;
    private JLabel  lblLanzamientosHechos1;
    private JLabel  lblLanzamientosHechos2;
    private JLabel  lblSumatoriaJugador1;
    private JLabel  lblSumatoriaJugador2;
    private JButton btnLanzar;
    private JPanel  panelPrincipal;
    private JPanel  panelAlto;
    private JPanel  panelMedio;
    private JPanel  panelBajo;
    private JPanel  panelDados;
    private Container contenedorJuego;

    
    public VentanaJuego(Ronda ronda){
        this.ronda = ronda;
        iniciarComponentes();
        setSize(720, 480);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Casino Univalle");
        tiempo = new Timer(1000, null);
        tiempo.start();
        
        tiempo.addActionListener((ActionEvent e) -> {
            lblTiempo.setText("Tiempo transcurrido: " + m + " min, " + s + " segs");
            // La asignación se puede empaquetar en una función
            s++;
            if(s == 60){
                s = 0;
                ++m;
            }
            if(m == 60){
                m = 0;
                h++;                 
            }
        });   
    }
    
    private void iniciarComponentes(){
        
        Toolkit pantalla = Toolkit.getDefaultToolkit(); 
        // Para establecer el icono en la aplicación
        Image icono = pantalla.getImage("src/Imagenes/icon.png");
	setIconImage(icono);
        
        btnLanzar = new JButton("Lanzar");
        
        // Vamos a usar mucho a los jugadores, entonces tengamoslos en variables
        
        Jugador jugador1 = ronda.getJugador(1);
        Jugador jugador2 = ronda.getJugador(2);

        // Por defecto dejamos la imagen del dado 1
        // Dado 1
        
        lblDado1 = new JLabel("");
        lblDado1.setIcon(new ImageIcon("src\\Imagenes\\Dado\\1.png"));
        
        // Dado 2
        
        lblDado2 = new JLabel("");       
        lblDado2.setIcon(new ImageIcon("src\\Imagenes\\Dado\\1.png"));

        lblLanzamientosRonda = new JLabel("Lanzamientos de la ronda: "+ ronda.getLanzamientos());
        lblLanzamientosRestantes = new JLabel("Lanzamientos restantes: " + ronda.getLanzamientosRestantes());
        lblLanzamientosEmpate = new JLabel("Lanzamientos con empate: " + ronda.getEmpates());

        lblTiempo = new JLabel("Tiempo transcurrido: ");
        
        lblJugadorLanzador = new JLabel("Tu turno: " + ronda.getJugador(turno));

        lblParcialGanador = new JLabel("Parcial ganador: " + ronda.getNombreGanador(jugador1, jugador2));
        
        lblSumatoriaJugador1 = new JLabel("Total puntos jugador 1: " + jugador1.getPuntaje());
        lblSumatoriaJugador2 = new JLabel("Total puntos jugador 2: " + jugador2.getPuntaje());

        lblLanzamientosHechos1 = new JLabel("Lanzamientos hechos jugador 1: " + jugador1.getLanzamientosHechos());
        lblLanzamientosHechos2 = new JLabel("Lanzamientos hechos jugador 2: " + jugador2.getLanzamientosHechos());
        
        panelAlto = new JPanel();
        panelAlto.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
        panelAlto.add(lblTiempo);
        panelAlto.add(lblJugadorLanzador);
        panelAlto.add(lblLanzamientosRonda);
        panelAlto.add(lblLanzamientosRestantes);
        panelAlto.add(lblParcialGanador);
        
        panelDados = new JPanel();
        panelDados.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelDados.add(lblDado1);
        panelDados.add(lblDado2);

        panelMedio = new JPanel();     
        panelMedio.setLayout(new BorderLayout());     
        panelMedio.add(panelDados,BorderLayout.CENTER);
        panelMedio.add(btnLanzar,BorderLayout.SOUTH);   

        panelBajo = new JPanel();
        panelBajo.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 0));
        panelBajo.add(lblSumatoriaJugador1);
        panelBajo.add(lblLanzamientosEmpate);
        panelBajo.add(lblSumatoriaJugador2);
        panelBajo.add(lblLanzamientosHechos1);
        panelBajo.add(lblLanzamientosHechos2);
        
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3, 1));
        panelPrincipal.add(panelAlto);
        panelPrincipal.add(panelMedio);
        panelPrincipal.add(panelBajo);

        contenedorJuego = getContentPane();
        
        add(panelPrincipal);
        
        btnLanzar.addActionListener(new manejadorEventos());
    }

    private int tirarDados() {
        int puntuacion = 0;
        int aleatorio = (int)(1 + Math.random() * 6); // Arroja un numero aleatorio entre 1 - 6
        lblDado1.setIcon(new ImageIcon("src\\Imagenes\\Dado\\" + aleatorio + ".png"));
        puntuacion += aleatorio;
        aleatorio = (int)(1 + Math.random() * 6);
        lblDado2.setIcon(new ImageIcon("src\\Imagenes\\Dado\\" + aleatorio + ".png"));
        puntuacion += aleatorio;
        
        return puntuacion;         
    }       
    
    private class manejadorEventos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent elemento) {
            // Actualizar toda la información
            Jugador jugador1 = ronda.getJugador(1);
            Jugador jugador2 = ronda.getJugador(2);

            // Primero quitamos los lanzamientos
            if(ronda.getModo() == 0) {
                ronda.disminuirOAumentarLanzamientosRestantes(-0.5f); // Cada lanzamiento de un jugador es la mitad de un "lanzamiento"
            } else {
                ronda.disminuirOAumentarLanzamientosRestantes(-1); // Este si en un lanzamiento completo
            }                  

            // Empezamos las tiradas con el jugador 1
            // Comprobamos en que modo estamos                
                
            if(ronda.getModo() == 0) { // Modo jugador vs jugador
                int dados = tirarDados();
                
                Jugador tirador = ronda.getJugador(turno);
                Jugador tiradorAnterior;
                
                tirador.aumentarPuntaje(dados);
                    
                System.out.println("Nombre jugador : " + tirador.getNombre());
                System.out.println("Puntaje jugador : " + tirador.getPuntaje());
                System.out.println("Lanzamientos hechos: " + tirador.getLanzamientosHechos());
                    
                // Dependiendo del turno en el que estaba, establece el siguiente
                if(turno == 1) {
                    turno = 2; // Si el turno estaba en 1, en la siguiente pasa a 2
                    tiradorAnterior = ronda.getJugador(2);
                } else {
                    turno = 1; // Si el turno estaba en 2, en la siguiente pasa a 1
                    tiradorAnterior = ronda.getJugador(1);
                }
                    
                if(lanzamientoAnterior == dados) { // La variable se inicializa en cero, porque
                    // es imposible sacar cero en un lanzamiento
                    ronda.aumentarEmpates();
                    ronda.disminuirOAumentarLanzamientosRestantes(1f); // Si hubo un empate agregamos otra ronda
                    // para invalidar la actual
                    tiradorAnterior.aumentarODisminuirLanzamientosHechos(-1); // Invalidamos el lanzamiento del
                    // tirador anterior y no contabilizamos el del actual
                    JOptionPane.showMessageDialog(null, "Hubo un empate, este lanzamiento no se contabilizara");
                        
                } else {
                    
                    tirador.aumentarODisminuirLanzamientosHechos(1);
                    System.out.println("Lanzamientos hechos: " + tirador.getLanzamientosHechos());
                }
                    lanzamientoAnterior = dados;
            } else { // Modo jugador vs maquina
                // En este no necesitamos la variable de control "turnos"
                int dadosJugador1 = tirarDados();
                jugador1.aumentarPuntaje(dadosJugador1); // La tirada del jugador
                    
                int dadosJugador2 = tirarDados();
                jugador2.aumentarPuntaje(dadosJugador2); // acciona la de la maquina                                       
                    
                if(dadosJugador1 == dadosJugador2) {
                    ronda.aumentarEmpates();
                    ronda.disminuirOAumentarLanzamientosRestantes(1);
                } else {
                    jugador1.aumentarODisminuirLanzamientosHechos(1);
                    jugador1.aumentarODisminuirLanzamientosHechos(1);
                }
            }
                // Queremos que nuestra informacion salga ya "manipulada"
                lblLanzamientosRestantes.setText("Lanzamientos de la ronda: "+ ronda.getLanzamientos());
                lblLanzamientosEmpate.setText("Lanzamientos con empate: " + ronda.getEmpates());
                lblJugadorLanzador.setText("Tu turno: " + (ronda.getJugador(turno).getNombre()));
                lblParcialGanador.setText("Parcial ganador: " + ronda.getNombreGanador(jugador1, jugador2));
                lblSumatoriaJugador1.setText("Total puntos jugador 1: " + jugador1.getPuntaje());
                lblSumatoriaJugador2.setText("Total puntos jugador 2: " + jugador2.getPuntaje());
                lblLanzamientosHechos1.setText("Lanzamientos hechos jugador 1: " + jugador1.getLanzamientosHechos());
                lblLanzamientosHechos2.setText("Lanzamientos hechos jugador 2: " + jugador2.getLanzamientosHechos());
                // La comprobación para finalizar la ronda se hace de ultimo
                if(ronda.getLanzamientosRestantes() == 0) {
                    tiempo.stop();
                    dispose(); // Cerramos la ventana
                    VentanaResultados ventanaResultados = new VentanaResultados(ronda);
                }  
            }   
        }
}    
