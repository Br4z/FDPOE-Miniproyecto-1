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
 *  CLASE:     Jugador
 *  INTENCION: Representar a un jugador (tirador) del casino
 *  RELACION:  NINGUNA 
 */

public class Jugador {
    private String  nombre;
    private int     puntaje = 0;
    private int     lanzamientosHechos = 0;
    
    public Jugador(String nombre) { 
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getPuntaje() {
        return puntaje;
    }
    
    public void aumentarPuntaje(int puntaje) {
        this.puntaje += puntaje;
    }
    
    public int getLanzamientosHechos() {
        return lanzamientosHechos;
    }

    public void aumentarODisminuirLanzamientosHechos(int numero) {
        lanzamientosHechos += numero;
    }
}
