package Interface;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Moteur.Map;
import Moteur.Vivant.Crabe;
import Moteur.Vivant.Poisson;
import Interface.Constantes;

/**
 * @author Vincent
 *
 */
public class MaFenetre extends JPanel implements KeyListener
{
    /**
     * Constructeur de la classe
     */
    public MaFenetre()
    {
        // Initialisation des champs
        this.m_NiveauCourrant = 1;
        
        // Initialisation de la fenetre
        setSize(Constantes.LargeurFenetre, Constantes.HauteurFenetre);
        setOpaque(true);
        addKeyListener(this);
        changementNiveau(1, 30);
        
    }
    
    /**
     * Recharge un nouveau Niveau
     * @param niveau Le niveau à charger
     * @param oxygene L'oxygène disponnible au joueur pour le niveau
     */
    public void changementNiveau(int niveau, int oxygene)
    {
        this.m_NiveauCourrant = niveau;
        try
        { this.m_MapCourrante = new Map(this.m_NiveauCourrant, oxygene); }
        catch (Exception e)
        { System.err.println(e.getMessage()); }
    }
    
    // Champs
    private Map m_MapCourrante;
    private int m_NiveauCourrant;
    
    
    // Méthodes pour JPanel et KeyListener
    
    @Override
    public void paintComponent(Graphics g)
    {
        // Affichage de toutes les cases de la map
        for(int i = 0; i < this.m_MapCourrante.getMap().size(); i++)
            for(int j =  0; j < this.m_MapCourrante.getMap().get(i).size(); j++)
            {
                switch(this.m_MapCourrante.getMap().get(i).get(j).getType())
                {
                    case BalleBleue : 
                        g.drawImage(Interface.Images.BalleBleue, j*Constantes.LargeurCase, i*Constantes.HauteurCase, null);
                        break;
                    case BalleRouge :
                        g.drawImage(Interface.Images.BalleRouge, j*Constantes.LargeurCase, i*Constantes.HauteurCase, null);
                        break;
                    case Bombe :
                        g.drawImage(Interface.Images.Bombe, j*Constantes.LargeurCase, i*Constantes.HauteurCase, null);
                        break;
                    case Clef:
                        g.drawImage(Interface.Images.Clef, j*Constantes.LargeurCase, i*Constantes.HauteurCase, null);
                        break;
                    case Indestructible : 
                        g.drawImage(Interface.Images.Indestructible, j*Constantes.LargeurCase, i*Constantes.HauteurCase, null);
                        break;
                    case Pierre : 
                        g.drawImage(Interface.Images.Pierre, j*Constantes.LargeurCase, i*Constantes.HauteurCase, null);
                        break;
                    case Porte : 
                        g.drawImage(Interface.Images.Porte, j*Constantes.LargeurCase, i*Constantes.HauteurCase, null);
                        break;
                    case Sable : 
                        g.drawImage(Interface.Images.Sable, j*Constantes.LargeurCase, i*Constantes.HauteurCase, null);
                        break;
                    case Sortie :
                        g.drawImage(Interface.Images.Sortie, j*Constantes.LargeurCase, i*Constantes.HauteurCase, null);
                        break; 
                    case Vide : 
                        g.drawImage(Interface.Images.Vide, j*Constantes.LargeurCase, i*Constantes.HauteurCase, null);
                        break;
                    default :
                        g.drawImage(Interface.Images.Vide, j*Constantes.LargeurCase, i*Constantes.HauteurCase, null);
                        break;
                }
            }
        
        // Affichage des monstres
        
        for(Crabe c : this.m_MapCourrante.getCrabes())
            g.drawImage(Interface.Images.Crabe,
                        c.getPosition().Colone*Constantes.LargeurCase,
                        c.getPosition().Ligne * Constantes.HauteurCase,
                        null);
        
        for(Poisson p : this.m_MapCourrante.getPoissons())
            g.drawImage(Interface.Images.Poisson.get(p.getDirectionPrecedente()),
                    p.getPosition().Colone*Constantes.LargeurCase,
                    p.getPosition().Ligne * Constantes.HauteurCase,
                    null);
        
        
        
        // Affichage du joueur
        g.drawImage(Interface.Images.Joueur, 
                    this.m_MapCourrante.getPositionPersonnage().Colone*Constantes.LargeurCase,
                    this.m_MapCourrante.getPositionPersonnage().Ligne*Constantes.HauteurCase,
                    null);
        
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            // Déplacement vers le haut
            case KeyEvent.VK_Z : 
            case KeyEvent.VK_UP : 
                this.m_MapCourrante.deplacementPersonnage(Moteur.Constantes.Direction.Haut);
                break;
                
            // Décplacement vers le bas
            case KeyEvent.VK_S : 
            case KeyEvent.VK_DOWN : 
                this.m_MapCourrante.deplacementPersonnage(Moteur.Constantes.Direction.Bas);
                break;
                
            // Déplacement vers la droite
            case KeyEvent.VK_D : 
            case KeyEvent.VK_RIGHT : 
                this.m_MapCourrante.deplacementPersonnage(Moteur.Constantes.Direction.Droite);
                break;
                
            //Déplacement vers la gauche
            case KeyEvent.VK_Q : 
            case KeyEvent.VK_LEFT : 
                this.m_MapCourrante.deplacementPersonnage(Moteur.Constantes.Direction.Gauche);
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

}
