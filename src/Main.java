
import java.io.IOException;

import javax.swing.JFrame;

import Interface.Images;
import Interface.MyCanvas;

/**
 * 
 * @author Petrolevb
 *
 */
public class Main
{

    /**
     * @param args
     * @throws IOException 
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
        frame.getContentPane().add(new MyCanvas());
        frame.setVisible(true);
    }

}

