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
    public static class Position 
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
        
        /** Coordonnée en X */ public int Ligne ;
        /** Coordonnée en Y */ public int Colone; 
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

