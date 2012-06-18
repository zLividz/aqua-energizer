package Moteur.Vivant;

import Moteur.Constantes;

/**
 * Interface qui va d�finir les fonctions essentielles des �tres vivants
 * @author Petrolevb
 *
 */
public interface IEtreVivant
{
    /**
     * Fonction qui d�fini le d�placement
     * @param directionDeplacement Direction du d�placement de l'�tre vivant
     * @see Moteur.Constantes.Direction
     */
    public void deplacement(Constantes.Direction directionDeplacement);
}
