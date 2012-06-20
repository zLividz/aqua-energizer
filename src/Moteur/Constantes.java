package Moteur;

/**
 * Classe d�finissant les diverses constantes utilis�es pour le moteur
 * @author Petrolevb
 *
 */
public class Constantes
{
    /**
     * Enum�ration de direction pour le moteur
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
         * @param ligne Coordonn�e X de la position
         * @param colone Coordonn�e Y de la position
         */
        public Position(int ligne, int colone) 
        { this.Ligne = ligne; this.Colone = colone; } 
        
        /**
         * Permet de calculer une position a partir d'une direction
         * @param direction Direction du d�placement
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
         * Retourne si la position pass�e en parram�tre est dans l'une des 8 cases autour de celle ci
         * @param p Position � tester
         * @return Retourne un bool�en
         */
        public boolean contact(Position p)
        { return ((Math.abs(p.Ligne-this.Ligne) < 2) && (Math.abs(p.Colone-this.Colone) < 2)); }
        
        /** Coordonn�e en X */ public int Ligne ;
        /** Coordonn�e en Y */ public int Colone; 
    }
    
    /**
     * Enum�ration des cases possibles
     */
    @SuppressWarnings("javadoc") public static enum Case { Vide, 
                                                           Indestructible, Pierre, Sable,
                                                           Bombe, BalleRouge, BalleBleue,
                                                           Porte, Clef, Sortie,
                                                           Joueur, Crabe, Poisson }
    
}

