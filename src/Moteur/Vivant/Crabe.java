package Moteur.Vivant;

import Exceptions.CaseException;
import Moteur.Constantes;
import Moteur.Constantes.Direction;
import Moteur.Constantes.Position;

/**
 * @author Vincent BELLEC
 * @see Moteur.Vivant.IEtreVivant
 */
public class Crabe extends Moteur.Case implements Moteur.Vivant.IEtreVivant
{
    /**
     * Constructeur de la classe
     * @param p Position du crabe
     * @throws CaseException Excption levée si la position n'est pas correcte
     */
    public Crabe(Position p) throws CaseException
    { super(Constantes.Case.Crabe, p); }
    
    @Override
    public void deplacement(Direction directionDeplacement)
    { this.m_Position = this.m_Position.addPosition(directionDeplacement); }

}
