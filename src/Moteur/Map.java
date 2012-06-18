package Moteur;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.*;
import Moteur.Constantes.Position;
import Moteur.Vivant.*;

/**
 * Classe moteur qui va gérer les interractions avec les cases
 * @author Petrolevb
 *
 */
public class Map
{
    /**
     * Constructeur de la classe
     * @param niveau Numéros du niveau
     * @throws LevelException Exception levée si le niveau n'est pas trouvé
     * @throws CaseException Exception levée s'il y a un problème de case
     */
    @SuppressWarnings("nls") 
    public Map(int niveau) throws LevelException, CaseException
    {
        String nomFichier = "src" + File.separator + 
                            "Ressources" + File.separator + 
                            "level" + File.separator + 
                            "niveau" + niveau;
        this.m_NombreBalleRouge = 0;
        this.m_Map = new ArrayList<>();
        
        this.m_Personnage = null;
        this.m_Crabes = new ArrayList<>();
        this.m_Poissons = new ArrayList<>();
        this.m_PortesOuvertes = true;
        
        Reader r;
        try
        { r = new FileReader(nomFichier); }
        catch (IOException e)
        { System.err.println(e.getMessage()); throw new LevelException("Niveau non trouvé"); }
        
        Scanner sc = new Scanner(r);
        Position p = new Position(0, 0);
        
        
        while(sc.hasNext())
        {
            String ligne = sc.nextLine();
            ArrayList<Moteur.Case> caseLigne = new ArrayList<>();
            for(char c : ligne.toCharArray())
            {
                switch(c)
                {
                    case ' ' : caseLigne.add(new Case(Constantes.Case.Vide, p));  break;
                    case 'b' : caseLigne.add(new Case(Constantes.Case.BalleBleue, p));  break;
                    case 'r' : 
                        caseLigne.add(new Case(Constantes.Case.BalleRouge, p)); 
                        this.m_NombreBalleRouge++; 
                        break;
                    case 'u' : caseLigne.add(new Case(Constantes.Case.Bombe, p));  break;
                    case 'k' : 
                        caseLigne.add(new Case(Constantes.Case.Clef, p));
                        this.m_PortesOuvertes = false;
                        break;
                    case 'c' :
                        caseLigne.add(new Case(Constantes.Case.Vide, p));
                        this.m_Crabes.add(new Crabe(p));
                        break;
                    case 'I' : caseLigne.add(new Case(Constantes.Case.Indestructible, p));  break;
                    case 'J' :
                        caseLigne.add(new Case(Constantes.Case.Vide, p));
                        this.m_Personnage = new Personnage(p);
                        break;
                    case 'D' : caseLigne.add(new Case(Constantes.Case.Pierre, p));  break;
                    case 'p' : 
                        caseLigne.add(new Case(Constantes.Case.Vide, p));
                        this.m_Poissons.add(new Poisson(p));
                        break;
                    case 'o' : caseLigne.add(new Case(Constantes.Case.Porte, p));  break;
                    case 'm' : caseLigne.add(new Case(Constantes.Case.Sable, p));  break;
                    case 's' : caseLigne.add(new Case(Constantes.Case.Sortie, p));  break;
                    default : p.Colone--; break; // Si l'on ajoute pas de case, on fait en sorte de ne pas avancer
                }
                p.Colone++;
            }
            if(this.m_Personnage == null) 
                throw new LevelException("Position du joueur non trouvée dans la map");
            
            this.m_Map.add(caseLigne);
            p.Ligne++;
            p.Colone = 0;
        }// Fin de lecture du fichier
        
    }// Fin du constructeur
    
    /**
     * Deplace le personnage retourne si réussite ou collision
     * @param directionDeplacement Direction du délacement enregistrée
     * @return Booléen qui indique si le personnage a réussi à se déplacer
     */
    public boolean deplacementPersonnage(Constantes.Direction directionDeplacement)
    {
        // gestion de la collision
        //if(collision) return false
        
        this.m_Personnage.deplacement(directionDeplacement); 
        return true;
    }
    
    /**
     * Retourne la position du personnage sur la map
     * @return Retourne la position
     * @see Moteur.Vivant.Personnage
     * @see Moteur.Constantes.Position
     */
    public Position getPositionPersonnage() { return this.m_Personnage.getPosition(); }
    
    //Liste des etres vivants
    private Personnage m_Personnage;
    private ArrayList<Crabe> m_Crabes;
    private ArrayList<Poisson> m_Poissons;
    
    // Equivalent de savoir si la clef à été prise ou pas encore
    private boolean m_PortesOuvertes;
    
    private ArrayList< ArrayList<Moteur.Case> > m_Map;
    private int m_NombreBalleRouge;
}
    