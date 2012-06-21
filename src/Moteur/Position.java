package Moteur;

import Moteur.Constantes.Direction;

/**
 * Classe de positionnement pour le moteur
 * @author Vincent BELLEC
 */
public class Position 
{ 
    /**
     * Constructeur de la classe
     * @param ligne Coordonnée X de la position
     * @param colone Coordonnée Y de la position
     */
    public Position(int ligne, int colone) 
    { this.Ligne = ligne; this.Colone = colone; } 
    
    /**
     * Permet de calculer une position a partir d'une direction
     * @param direction Direction du déplacement
     * @return Retourne une nouvelle Position
     * @see Direction
     */
    public Position addPosition(Direction direction)
    {
        Position p = new Position(this.Ligne, this.Colone);
        switch (direction)
        {
            case Haut : p.Ligne --; break;
            case Bas : p.Ligne++; break;
            case Gauche : p.Colone--; break;
            case Droite : p.Colone++; break;
        }
        return p;
    }
    /**
     * Retourne si la position passée en parramêtre est dans l'une des 8 cases autour de celle ci
     * @param p Position à tester
     * @return Retourne un booléen
     */
    public boolean contact(Position p)
    { return ((Math.abs(p.Ligne-this.Ligne) < 2) && (Math.abs(p.Colone-this.Colone) < 2)); }
    
    @Override public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.Colone;
        result = prime * result + this.Ligne;
        return result;
    }
    @Override public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Position other = (Position)obj;
        if (this.Colone == other.Colone && this.Ligne == other.Ligne) return true;
        return false;
    }

    /** Coordonnée en X */ public int Ligne ;
    /** Coordonnée en Y */ public int Colone; 
}
