package Moteur.Vivant;

import Moteur.Constantes;

/**
 * Interface qui va définir les fonctions essentielles des êtres vivants
 * @author Petrolevb
 *
 */
public interface IEtreVivant
{
    /**
     * Fonction qui défini le déplacement
     * @param directionDeplacement Direction du déplacement de l'être vivant
     * @see Moteur.Constantes.Direction
     */
    public void deplacement(Constantes.Direction directionDeplacement);
}
