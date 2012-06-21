package Interface;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Ensemble des images pour le jeu
 * @author Vincent BELLEC
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
        Vide = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "vide.png")); 
        Indestructible =  ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "indestructible.png"));
        Pierre = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "pierre.png")); 
        Sable = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "sable.png")); 
        
        
        BalleBleue =  ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "boulle bleu.png"));
        BalleRouge = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "boulle rouge.png")); 
        Clef = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "clef.png")); 
        
        Bombe = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "bombe.png")); 
        Sortie = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "sortie.png")); 
        Porte = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "porte.png"));
        
        Joueur = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "personnage.png"));
        Crabe = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "crabe.png"));
        Poisson = new HashMap<>();
        Poisson.put(Moteur.Constantes.Direction.Haut,   ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "poisson_haut.png")));
        Poisson.put(Moteur.Constantes.Direction.Bas,    ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "poisson_bas.png")));
        Poisson.put(Moteur.Constantes.Direction.Gauche, ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "poisson_gauche.png")));
        Poisson.put(Moteur.Constantes.Direction.Droite, ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "poisson_droite.png")));
        
        Explosion = ImageIO.read(new File("src" + File.separator + "Ressources" + File.separator + "img" + File.separator + "explosion.png"));
    }
    /** L'image de l'explosion */
    static public Image Explosion;
    /** L'image du Vide. */
    static public Image Joueur; 
    /** L'image du Vide. */
    static public Image Crabe; 
    /** L'image du Vide. */
    static public HashMap<Moteur.Constantes.Direction, Image> Poisson; 
    
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

