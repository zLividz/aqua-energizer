
import java.io.IOException;

import javax.swing.JFrame;

import Interface.Images;
import Interface.MaFenetre;

/**
 * Classe principale d'entrée du programme
 * @author Vincent BELLEC
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
            final MaFenetre fenetre = new MaFenetre("Niveau3");
            frame.getContentPane().add(fenetre);
            frame.addKeyListener(fenetre);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
        catch(Exception e)
        { System.err.println(e.getMessage()); e.printStackTrace(); } 
    }

}

