package Moteur;

import Moteur.Constantes;
import Moteur.Constantes.Direction;
import Moteur.Constantes.Position;

/**
 * Classe qui va servir d'unit� pour la map
 * @author Vincent BELLEC
 *
 */
public class Case
{

    /**
     * Constructeur de la case
     * @param typeCase Type de la case, �num�ration de Moteur.Constantes
     * @param ligne Position de la case, ligne
     * @param colone Position de la case, colone
     */
    public Case(Constantes.Case typeCase, int ligne, int colone)
    {
        this.m_TypeCase = typeCase;
        this.m_Tombe = false;
        this.m_Position = new Position(ligne, colone);
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
                this.m_EstDeplacable = true;
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
            case Poisson :
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
            case Crabe : 
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
        }
        
    }

    /**
     * Constructeur de la case
     * @param typeCase Type de la case, �num�ration de Moteur.Constantes
     * @param p Position de la case, structure Constantes.Position
     */
    public Case(Constantes.Case typeCase, Constantes.Position p)
    {
        this.m_TypeCase = typeCase;
        this.m_Tombe = false;
        this.m_Position = new Position(p.Ligne, p.Colone);
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
            case Poisson :
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
            case Crabe : 
                this.m_EstDeplacable = true;
                this.m_EstDestructible = true;
                break;
        }
    }
    
    
    /**
     * Permet de transformer la case en vide, dans le cas d'une explosion par exemple
     */
    public void transformeEnVide()
    {
        this.m_TypeCase = Constantes.Case.Vide;
        this.m_EstDestructible = false;
        this.m_EstDeplacable = false;
    }
    
    
    /**
     * Indique si la case est destructible par explosion
     */
    protected boolean m_EstDestructible;
    
    /**
     * Indique si la case est d�placable par le joueur
     */
    protected boolean m_EstDeplacable;
    /**
     * Retourne si la case est d�placable
     * @return Retourne un bool�en
     */
    public boolean estDeplacable() { return this.m_EstDeplacable; }
    
    
    /** Propri�t�e pour savoir si la case est en chute */
    protected boolean m_Tombe;
    /** 
     * Permet de savoir si la case tombe
     * @return Bool�en
     */
    public boolean getTombe() { return this.m_Tombe; }
    /**
     * Permet de d�finir si la case tombe
     * @param value Bool�en � positionner
     */
    public void setTombe(boolean value) { this.m_Tombe = value; }
    
    
    /**
     * Donne la position de la case dans la map
     */
    protected Constantes.Position m_Position;
    /**
     * R�cup�re la position de la case
     * @return Retourne une structure position
     * @see Constantes.Position
     */
    public Constantes.Position getPosition() { return this.m_Position; }
    /**
     * D�place la case dans la position donn�e, si possible
     * @param direction Direction du d�placement
     */
    public void deplacement(Direction direction)
    {
        if(this.m_EstDeplacable)
            this.m_Position = this.m_Position.addPosition(direction); 
    }
    
    /**
     * Indique le type de la case
     */
    protected Constantes.Case m_TypeCase;
    /**
     * R�cup�re le type de la case
     * @return Retourne un �l�ment de l'�num�ration Constantes.Case
     */
    public Constantes.Case getType() { return this.m_TypeCase; }
}
