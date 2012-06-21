package Moteur;

/**
 * Classe définissant les diverses constantes utilisées pour le moteur
 * @author Vincent BELLEC
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
     * Enumération des cases possibles
     */
    @SuppressWarnings("javadoc") public static enum Case { Vide, 
                                                           Indestructible, Pierre, Sable,
                                                           Bombe, BalleRouge, BalleBleue,
                                                           Porte, Clef, Sortie,
                                                           Joueur, Crabe, Poisson }
    
}

