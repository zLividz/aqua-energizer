package Interface;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import Moteur.Map;
import Moteur.Vivant.Crabe;
import Moteur.Vivant.Poisson;
import Exceptions.CaseException;
import Exceptions.LevelException;
import Interface.Constantes;

/**
 * @author Vincent BELLEC
 */
@SuppressWarnings("nls")
public class MaFenetre extends JPanel implements KeyListener
{
    /**
     * Constructeur de la classe
     * @param code Code pour pouvoir directement accéder à un autre niveau
     * @throws LevelException Stop le processus si le chargement du niveau échoue
     * @throws CaseException Stop le processus en cas d'erreur
     */
     public MaFenetre(String code) throws CaseException, LevelException
    {
        // Initialisation des champs
        this.m_NiveauCourrant = 1;
        
        // Initialisation de la fenetre
        setSize(Constantes.LargeurFenetre, Constantes.HauteurFenetre);
        setOpaque(true);
        addKeyListener(this);
        
        switch(code)
        {
            case "Niveau2" : changementNiveau(2, 30); break;
            case "Niveau3" : changementNiveau(3, 30); break;
            case "Niveau4" : changementNiveau(4, 30); break;
            default : changementNiveau(1, 30); break;
        }

        this.m_BarreEnergie = new JProgressBar();
        this.m_BarreEnergie.setMaximum(100);
        this.m_BarreEnergie.setValue(0);
        this.m_BarreEnergie.setLocation(0, Constantes.HauteurFenetre - Constantes.HauteurCase);

        this.m_Oxygene = new JLabel(((Integer)this.m_MapCourrante.getOxygene()).toString()); 
        
        this.m_Suicide = new JButton("Mort");
        this.m_Suicide.addActionListener(new ActionListener()
        { 
            @Override public void actionPerformed(ActionEvent e) 
            { 
                MaFenetre.this.requestFocus();
                try{ MaFenetre.this.changementNiveau(MaFenetre.this.m_NiveauCourrant, 30); }
                catch(Exception err) { System.err.println(err.getMessage()); err.printStackTrace(); System.exit(1); }
            }
        });
        this.add(this.m_Oxygene);
        this.add(this.m_BarreEnergie);
        this.add(this.m_Suicide);
    }
    
    /**
     * Fonction en charge de faire recommencer le jeu, ou de faire continuer la partie en fonction de l'action
     * @throws LevelException stop le programme en cas d'erreur lors du changement de niveau
     * @throws CaseException Stop le processus en cas d'erreur
     */
    public void verrificationFinNiveau() throws CaseException, LevelException
    {
        if(this.m_MapCourrante.Sortie())
        {
            this.m_NiveauCourrant++;
            this.changementNiveau(this.m_NiveauCourrant, this.m_MapCourrante.getOxygene() + 30);
        }
        if(this.m_MapCourrante.EstPerdue())
        {
            System.err.println("Fin de partie");
            this.m_NiveauCourrant = 1;
            this.changementNiveau(this.m_NiveauCourrant,  30);
        }
    }
    
    /**
     * Recharge un nouveau Niveau
     * @param niveau Le niveau à charger
     * @param oxygene L'oxygène disponnible au joueur pour le niveau
     * @throws LevelException Stop le processus en cas d'erreur
     * @throws CaseException Stop le processus en cas d'erreur
     */
    public void changementNiveau(int niveau, int oxygene) throws LevelException, CaseException
    {
        this.m_NiveauCourrant = niveau;
        try { this.m_MapCourrante = new Map(this.m_NiveauCourrant, oxygene, this); }
        catch (LevelException e) 
        { 
            System.err.println(e.getMessage()); 
            e.printStackTrace();
            throw new LevelException("End of Program");
        }
    }

    // Champs
    private Map m_MapCourrante;
    private int m_NiveauCourrant;
    
    private JProgressBar m_BarreEnergie;
    private JLabel m_Oxygene;
    private JButton m_Suicide;

    // Méthodes pour JPanel et KeyListener
    /**
     * Mise à jour des diverses informations
     */
    public void affichageInformations()
    {
        this.m_Oxygene.setText(((Integer)this.m_MapCourrante.getOxygene()).toString());
        this.m_BarreEnergie.setValue((int)(100*this.m_MapCourrante.getEnergie()));
    }
    
    
    @Override
    public void paintComponent(Graphics g)
    {
        // On met a jour les informations (au cas où)
        this.affichageInformations();
        
        // Affichage de toutes les cases de la map
        for(ArrayList<Moteur.Case> ac : this.m_MapCourrante.getMap())
            for(Moteur.Case c : ac)
            {
                int i = c.getPosition().Ligne,
                    j = c.getPosition().Colone;
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
        
        this.affichageInformations();
        try { this.verrificationFinNiveau(); }
        catch(LevelException le)
        {
            System.err.println(le.getMessage());
            le.printStackTrace();
            System.exit(1); 
        }
        catch(CaseException ce)
        {
            System.err.println(ce.getMessage());
            ce.printStackTrace();
            System.exit(1); 
        }
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
