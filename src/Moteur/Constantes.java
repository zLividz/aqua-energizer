package Moteur;

/**
 * Classe définissant les diverses constantes utilisées pour le moteur
 * @author Petrolevb
 *
 */
public class Constantes
{
    /**
     * Enumération de direction pour le moteur
     * @author Petrolevb
     */
    @SuppressWarnings("javadoc") public static enum Direction { Haut, Bas, Gauche, Droite }
    
    /**
     * Structure de positionnement pour le moteur
     * @author Petrolevb
     */
    @SuppressWarnings("javadoc") public static class Position 
    { 
        public Position(int ligne, int colone) 
        { this.Ligne = ligne; this.Colone = colone; } 
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
        public int Ligne ; public int Colone; 
    }
    
    /**
     * Enumération des cases possibles
     */
    @SuppressWarnings("javadoc") public static enum Case { Vide, 
                                                           Indestructible, Pierre, Sable,
                                                           Bombe, BalleRouge, BalleBleue,
                                                           Porte, Clef, Sortie,
                                                           Joueur, Crabe, Poisson }
    
}

