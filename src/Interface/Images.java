package Interface;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Ensemble des images pour le jeu
 * @author Vincent
 */
@SuppressWarnings("nls") 
public class Images
{
    
    /**
     * Init de toutes les images qui ne seront qu'une fois en mémoire
     * @throws IOException Signals that an I/O exception has occurred.
     */
    static public void Init() throws IOException
    { 
        Vide = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "")); 
        Indestructible =  ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + ""));
        Pierre = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "")); 
        Sable = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "")); 
        
        
        BalleBleue =  ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + ""));
        BalleRouge = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "")); 
        Clef = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "")); 
        
        Bombe = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "")); 
        Sortie = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "")); 
        Porte = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + ""));
        
        Joueur = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + ""));
        Crabe = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + ""));
        Poisson = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + ""));
        
    }

    /** L'image du Vide. */
    static public Image Joueur; 
    /** L'image du Vide. */
    static public Image Crabe; 
    /** L'image du Vide. */
    static public Image Poisson; 
    
    /** L'image du Vide. */
    static public Image Vide; 
    
    /** L'image de la Case Indestructible. */
    static public Image Indestructible;
    
    /** L'image de la Pierre. */
    static public Image Pierre; 
    
    /** L'image du Sable. */
    static public Image Sable;
    
    /** L'image de la Balle bleue. */
    static public Image BalleBleue;  
    
    /** L'image de la Balle rouge. */
    static public Image BalleRouge; 
    
    /** L'image de la Clef. */
    static public Image Clef; 

    /** L'image de la Bombe. */
    static public Image Bombe; 
    
    /** L'image de la Sortie. */
    static public Image Sortie; 
    
    /** L'image de la Porte. */
    static public Image Porte; 
}

