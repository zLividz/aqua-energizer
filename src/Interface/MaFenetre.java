package Interface;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Moteur.Map;

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
     * @param niveau Le niveau � charger
     * @param oxygene L'oxyg�ne disponnible au joueur pour le niveau
     */
    public void changementNiveau(int niveau, int oxygene)
    {
        this.m_NiveauCourrant = niveau;
        try
        { this.m_MapCourrante = new Map(this.m_NiveauCourrant, oxygene); }
        catch (Exception e)
        { System.err.println(e.getMessage()); }
        this.m_Timer = new Timer(1000, this.m_MapCourrante.listener());
        this.m_Timer.start();
    }
    
    
    // Champs
    private Timer m_Timer;
    private Map m_MapCourrante;
    private int m_NiveauCourrant;
    
    
    
    
    // M�thodes pour JPanel et KeyListener
    
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
            // D�placement vers le haut
            case KeyEvent.VK_Z : 
            case KeyEvent.VK_UP : 
                this.m_MapCourrante.deplacementPersonnage(Moteur.Constantes.Direction.Haut);
                break;
                
            // D�cplacement vers le bas
            case KeyEvent.VK_S : 
            case KeyEvent.VK_DOWN : 
                this.m_MapCourrante.deplacementPersonnage(Moteur.Constantes.Direction.Bas);
                break;
                
            // D�placement vers la droite
            case KeyEvent.VK_D : 
            case KeyEvent.VK_RIGHT : 
                this.m_MapCourrante.deplacementPersonnage(Moteur.Constantes.Direction.Droite);
                break;
                
            //D�placement vers la gauche
            case KeyEvent.VK_Q : 
            case KeyEvent.VK_LEFT : 
                this.m_MapCourrante.deplacementPersonnage(Moteur.Constantes.Direction.Gauche);
                break;
        }
        
    }

    @Override public void keyReleased(KeyEvent e) { /* TODO Auto-generated method stub */ }
    @Override public void keyTyped(KeyEvent e) { /* TODO Auto-generated method stub */ }
}
