
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Interface.Images;
import Interface.MaFenetre;

/**
 * Classe principale d'entrée du programme
 * @author Petrolevb
 *
 */
public class Main
{

    /**
     * Entrée du programme
     * @param args Parramêtre présent, mais a priori non utilisé
     */
    public static void main(String[] args)
    {
        try
        { Images.Init(); }
        catch(IOException e)
        { System.err.println("Erreur d'initialisation"); System.err.println(e.getMessage()); return; }
        
        try
        {
            JFrame frame = new JFrame();
            frame.setSize(Interface.Constantes.LargeurFenetre, Interface.Constantes.HauteurFenetre);
            final MaFenetre fenetre = new MaFenetre();
            frame.getContentPane().add(fenetre);
            frame.getContentPane().addKeyListener(new KeyAdapter()
            {
                @Override public void keyTyped(KeyEvent e)
                { fenetre.keyPressed(e); }
            });
            frame.setVisible(true);
        }
        catch(Exception e)
        { 
            System.err.println(e.getMessage()); 
        } 
    }

}

