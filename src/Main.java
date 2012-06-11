
import java.io.IOException;

import javax.swing.JFrame;

import Interface.Images;
import Interface.MaFenetre;

/**
 * Classe principale d'entr�e du programme
 * @author Petrolevb
 *
 */
public class Main
{

    /**
     * Entr�e du programme
     * @param args Parram�tre pr�sent, mais a priori non utilis�
     */
    public static void main(String[] args)
    {
        try
        { Images.Init(); }
        catch(IOException e)
        { System.out.println(e.getMessage()); return; }
        
        // TODO Auto-generated method stub
        JFrame frame = new JFrame();
        frame.setSize(810, 610);
        frame.getContentPane().add(new MaFenetre());
        frame.setVisible(true);
    }

}

