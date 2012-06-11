package Moteur;

/**
 * Classe qui va définir le personnage
 * @author Vincent
 * @see Moteur.EtreVivant
 */
public class Personnage implements Moteur.EtreVivant
{
    /**
     * Constructeur de la classe
     */
    public Personnage()
    {
        this.m_Position.Ligne = 0;
        this.m_Position.Colone = 0;
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
    
    private Constantes.Position m_Position;

}
