package Interface;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import Exceptions.CaseException;
import Moteur.Vivant.Personnage;

/**
 * @author Vincent
 *
 */
public class MaFenetre extends JPanel implements KeyListener
{
    /**
     * Constructeur de la classe
     * @throws Exception Exception de conception
     */
    public MaFenetre() throws Exception
    {
        // Initialisation des champs
        try
        { this.m_Personnage = new Personnage(new Moteur.Constantes.Position(0, 0)); }
        catch(CaseException e)
        { 
            System.err.println(e.getMessage()); 
            throw new Exception("Erreur d'implementation sur la position initiale du joueur");
        }
        
        // Initialisation de la fenetre
        setSize(Constantes.LargeurFenetre, Constantes.HauteurFenetre);
        setOpaque(true);
        addKeyListener(this);
        
    }
    
    
    // Champs
    
    private Moteur.Vivant.Personnage m_Personnage;
    
    
    
    
    // Méthodes pour JPanel et KeyListener
    
    @Override
    public void paintComponent(Graphics g)
    {
        //g.drawImage(this.m_Img, 0, 0, null); 
        
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            // Déplacement vers le haut
            case KeyEvent.VK_Z : 
            case KeyEvent.VK_UP : 
                this.m_Personnage.deplacement(Moteur.Constantes.Direction.Haut);
                break;
                
            // Décplacement vers le bas
            case KeyEvent.VK_S : 
            case KeyEvent.VK_DOWN : 
                this.m_Personnage.deplacement(Moteur.Constantes.Direction.Bas);
                break;
                
            // Déplacement vers la droite
            case KeyEvent.VK_D : 
            case KeyEvent.VK_RIGHT : 
                this.m_Personnage.deplacement(Moteur.Constantes.Direction.Droite);
                break;
                
            //Déplacement vers la gauche
            case KeyEvent.VK_Q : 
            case KeyEvent.VK_LEFT : 
                this.m_Personnage.deplacement(Moteur.Constantes.Direction.Gauche);
                break;
        }
        
    }

    @Override public void keyReleased(KeyEvent e) { /* TODO Auto-generated method stub */ }
    @Override public void keyTyped(KeyEvent e) { /* TODO Auto-generated method stub */ }
}
