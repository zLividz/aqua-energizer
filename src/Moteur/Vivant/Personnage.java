package Moteur.Vivant;

import Exceptions.CaseException;
import Moteur.Constantes;
import Moteur.Constantes.Case;
import Moteur.Constantes.Position;

/**
 * Classe qui va définir le personnage
 * @author Vincent
 * @see Moteur.Vivant.IEtreVivant
 */
public class Personnage extends Moteur.Case implements Moteur.Vivant.IEtreVivant 
{
    /**
     * Constructeur de la classe
     * @param p Position de départ du joueur
     * @throws CaseException Excption levée si la position n'est pas correcte
     */
    public Personnage(Position p) throws CaseException
    {
        super(Case.Joueur, p);
    }
    
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
     * Retourne la position du personnage sur la map actuelle
     * @return Retourne la position
     * @see Position
     */
    public Position getPosition() { return this.m_Position; }

}
