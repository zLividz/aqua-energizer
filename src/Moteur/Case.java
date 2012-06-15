package Moteur;

import Exceptions.CaseException;
import Moteur.Constantes;

/**
 * Classe qui va servir d'unité pour la map
 * @author Petrolevb
 *
 */
public class Case
{

    /**
     * Constructeur de la case
     * @param typeCase Type de la case, énumération de Moteur.Constantes
     * @param ligne Position de la case, ligne
     * @param colone Position de la case, colone
     * @throws CaseException Exception levée si le type de la case n'est pas trouvé
     */
    @SuppressWarnings("nls") 
    public Case(Constantes.Case typeCase, int ligne, int colone) throws CaseException
    {
        this.m_TypeCase = typeCase;
        
        this.m_Position.Ligne = ligne;
        this.m_Position.Colone = colone;
        switch (this.m_TypeCase)
        {
            case Vide : 
                this.m_EstDeplacable = false;
                this.m_EstDestructible = false;
                break;
            case Indestructible :
                this.m_EstDeplacable = false;
                this.m_EstDestructible = false;
                break;
            case  Pierre : 
                this.m_EstDeplacable = false;
                this.m_EstDestructible = true;
                break;
            case Sable : 
                this.m_EstDeplacable = false;
                this.m_EstDestructible = true;
                break;
            case Bombe : 
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
            case  BalleRouge : 
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
            case  BalleBleue : 
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
            case Porte : 
                this.m_EstDeplacable = false;
                this.m_EstDestructible = false;
                break;
            case Clef :
                this.m_EstDeplacable = false;
                this.m_EstDestructible = true;
                break;
            case Sortie : 
                this.m_EstDeplacable = false;
                this.m_EstDestructible = false;
                break;
            case Joueur :
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
            default : throw new CaseException("Type de la case inconnu");
        }
        
    }

    /**
     * Constructeur de la case
     * @param typeCase Type de la case, énumération de Moteur.Constantes
     * @param p Position de la case, structure Constantes.Position
     * @throws CaseException Exception levée si le type de la case n'est pas trouvé
     */
    @SuppressWarnings("nls") 
    public Case(Constantes.Case typeCase, Constantes.Position p) throws CaseException
    {
        this.m_TypeCase = typeCase;
        this.m_Position.Ligne = p.Ligne;
        this.m_Position.Colone = p.Colone;
        switch (this.m_TypeCase)
        {
            case Vide : 
                this.m_EstDeplacable = false;
                this.m_EstDestructible = false;
                break;
            case Indestructible :
                this.m_EstDeplacable = false;
                this.m_EstDestructible = false;
                break;
            case  Pierre : 
                this.m_EstDeplacable = false;
                this.m_EstDestructible = true;
                break;
            case Sable : 
                this.m_EstDeplacable = false;
                this.m_EstDestructible = true;
                break;
            case Bombe : 
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
            case  BalleRouge : 
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
            case  BalleBleue : 
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
            case Porte : 
                this.m_EstDeplacable = false;
                this.m_EstDestructible = false;
                break;
            case Clef :
                this.m_EstDeplacable = false;
                this.m_EstDestructible = true;
                break;
            case Sortie : 
                this.m_EstDeplacable = false;
                this.m_EstDestructible = false;
                break;
            case Joueur :
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
            default : throw new CaseException("Type de la case inconnu");
        }
    }
    
    /**
     * Indique si la case est destructible par explosion
     */
    protected boolean m_EstDestructible;
    
    /**
     * Indique si la case est déplacable par le joueur
     */
    protected boolean m_EstDeplacable;
    
    /**
     * Donne la position de la case dans la map
     */
    protected Constantes.Position m_Position;
    
    /**
     * Indique le type de la case
     */
    protected Constantes.Case m_TypeCase;
}
