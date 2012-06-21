package Moteur.Vivant;

import Exceptions.CaseException;
import Moteur.Constantes;
import Moteur.Constantes.Case;
import Moteur.Constantes.Position;

/**
 * Classe qui va d�finir le personnage
 * @author Vincent
 * @see Moteur.Vivant.IEtreVivant
 */
public class Personnage extends Moteur.Case implements Moteur.Vivant.IEtreVivant 
{
    /**
     * Constructeur de la classe
     * @param p Position de d�part du joueur
     * @param oxygene Oxyg�ne disponnible pour le joueur
     * @throws CaseException Excption lev�e si la position n'est pas correcte
     */
    public Personnage(Position p, int oxygene) throws CaseException
    { super(Case.Joueur, p); this.m_Oxygene = oxygene; }
    
    @Override public void deplacement(Constantes.Direction directionDeplacement)
    {
        switch(directionDeplacement)
        {
            case Haut : 
                this.m_Position.Colone++;
                break;
            case Bas : 
                this.m_Position.Colone--;
                break;
            case Droite : 
                this.m_Position.Ligne++;
                break;
            case Gauche : 
                this.m_Position.Ligne--;
                break;
        }
    }
    
    /**
     * D�cr�mente l'oxygene, Correspond � une respiration
     */
    public void decOxygene() { this.m_Oxygene--;}
    
    /**
     * Retourne l'oxyg�ne restant au joueur
     * @return
     */
    public int getOxygene() { return this.m_Oxygene; }
    private int m_Oxygene;
}
