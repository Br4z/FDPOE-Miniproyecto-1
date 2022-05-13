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

package modelos;

/**
 *  CLASE:     Ronda  
 *  INTENCION: Representar una ronda en el casino
 *  RELACION:  NINGUNA 
 */

public class Ronda {
    private int         modo;
    private Jugador     jugador1;
    private Jugador     jugador2;
    private int         numeroLanzamientos;
    private int         lanzamientosRestantes;
    private int         numeroEmpates = 0;
    
    public Ronda(int modo, Jugador jugador1, Jugador jugador2, int numeroLanzamientos) {
        this.modo = modo;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.numeroLanzamientos = numeroLanzamientos;
        lanzamientosRestantes = numeroLanzamientos;
    }
    
    public void setModo(int modo) {
        this.modo = modo;
    }
    
    public int getModo() {
        return modo;
    }
    
    public void setJugadores(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }
    
    public Jugador getJugador(int numero) {
        if(numero == 1) {
            return jugador1;
        } else {
            return jugador2;
        }
    }
    
    public void setLanzamientos(int numeroLanzamientos) {
        this.numeroLanzamientos = numeroLanzamientos;
    }
    
    public int getLanzamientos() {
        return numeroLanzamientos;
    }
    
    public int getEmpates() {
        return numeroEmpates;
    }
    
    public void aumentarEmpates() {
        numeroEmpates++;
    }
    
    public void setLanzamientosRestantes(int lanzamientosRestantes) {
        this.lanzamientosRestantes = lanzamientosRestantes;
    }
    
    public int getLanzamientosRestantes() {
        return lanzamientosRestantes;
    }

    public void disminuirOAumentarLanzamientosRestantes(float numero) {
        lanzamientosRestantes += numero;
    }
    
    public String getNombreGanador(Jugador jugador1, Jugador jugador2) {
        // Sabemos quien gano comparando puntuaciones
        if(jugador1.getPuntaje() > jugador2.getPuntaje()) {
            return jugador1.getNombre();
        } else if(jugador2.getPuntaje() > jugador1.getPuntaje()) {
            return jugador2.getNombre();
        } else {
            return "Hubo un empate";
        }
    }
}
