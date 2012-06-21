
import java.io.IOException;

import javax.swing.JFrame;

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
        { System.out.println(e.getMessage()); return; }
        
        try
        {
            JFrame frame = new JFrame();
            frame.setSize(Interface.Constantes.LargeurFenetre, Interface.Constantes.HauteurFenetre);
            frame.getContentPane().add(new MaFenetre());
            frame.setVisible(true);
        }
        catch(Exception e)
        { System.err.println(e.getMessage()); } 
    }

}

