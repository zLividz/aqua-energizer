package Moteur;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.CaseException;
import Exceptions.LevelException;
import Moteur.Constantes.Position;

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
        
        this.m_Map = new ArrayList<>();
        
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
                    default : p.Colone--; break; // Si l'on ajoute pas de case, on fait en sorte de ne pas avancer
                }
                p.Colone++;
            }
            
            this.m_Map.add(caseLigne);
            p.Ligne++;
            p.Colone = 0;
        }// Fin de lecture du fichier
        
    }// Fin du constructeur
        
    private ArrayList< ArrayList<Moteur.Case> > m_Map; 
}
    