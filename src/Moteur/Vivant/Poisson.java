package Moteur.Vivant;

import Exceptions.CaseException;
import Moteur.Constantes;
import Moteur.Constantes.Direction;
import Moteur.Constantes.Position;

/**
 * @author Vincent
 * @see Moteur.Vivant.IEtreVivant
 * @see Moteur.Constantes.Case
 */
public class Poisson extends Moteur.Case implements Moteur.Vivant.IEtreVivant
{
    /**
     * Constructeur de la classe
     * @param p Position du poisson
     * @param directionInitiale Direction initiale du poisson
     * @throws CaseException Excption levée si la position n'est pas correcte
     */
    public Poisson(Position p, Direction directionInitiale) throws CaseException
    { super(Constantes.Case.Poisson, p); this.m_DeplacementPrecedent = directionInitiale; }
    
    @Override
    public void deplacement(Direction directionDeplacement)
    {
        this.m_DeplacementPrecedent = directionDeplacement;
        this.m_Position = this.m_Position.addPosition(directionDeplacement);
    }
    
    /**
     * Récupère la direction du déplacement précédent du poisson
     * @return Enumération de direction du poisson
     * @see Direction
     */
    public Direction getDirectionPrecedente() { return this.m_DeplacementPrecedent; } 
    private Direction m_DeplacementPrecedent;
}
