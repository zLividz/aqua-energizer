package Moteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Timer;

import Exceptions.*;
import Moteur.Constantes.Direction;
import Moteur.Constantes.Position;
import Moteur.Vivant.*;

/**
 * Classe moteur qui va gérer les interractions avec les cases
 * @author Petrolevb
 */
public class Map
{
    /**
     * Constructeur de la classe
     * @param niveau Numéros du niveau
     * @param oxygene Oxygène disponnible pour le joueur
     * @throws LevelException Exception levée si le niveau n'est pas trouvé
     * @throws CaseException Exception levée s'il y a un problème de case
     */
    @SuppressWarnings("nls") 
    public Map(int niveau, int oxygene) throws LevelException, CaseException
    {
        String nomFichier = "src" + File.separator + 
                            "Ressources" + File.separator + 
                            "level" + File.separator + 
                            "niveau" + niveau;
        this.m_NombreBalleRouge = 0;
        this.m_Perdu = false;
        this.m_Map = new ArrayList<>();
        
        this.m_Personnage = null;
        this.m_Crabes = new ArrayList<>();
        this.m_Poissons = new ArrayList<>();
        this.m_PortesOuvertes = true;
        
        // Initialisation des threads
        this.m_VerificationGravite = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(true)
                {
                    for(ArrayList<Case> ac : Map.this.m_Map)
                        for(Case c : ac)
                            if(c.estDeplacable())
                                // On pousse la case vers le bas
                                if(Map.this.pousseCase(c.getPosition(), Constantes.Direction.Bas))
                                    // Si la map c'est bien actualisée, on actualise la case également
                                    c.deplacement(Constantes.Direction.Bas);
                }
            }
        });
        this.m_DeplacementMonstres = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (Poisson p : Map.this.m_Poissons)
                {
                    Direction depP = p.getDirectionPrecedente();
                    Position posP = p.getPosition(),
                             posSuiv = posP.addPosition(depP);
                    // Si on peut immédiatement déplacer le poisson
                    if(Map.this.m_Map.get(posSuiv.Ligne).get(posSuiv.Colone).getType() == Constantes.Case.Vide)
                        p.deplacement(depP);
                    // Sinon, on parcours toutes les directions envisageables
                    else
                        for(Constantes.Direction d : Constantes.Direction.values())
                        {
                            posSuiv = posP.addPosition(d);
                            if(Map.this.m_Map.get(posSuiv.Ligne).get(posSuiv.Colone).getType() == Constantes.Case.Vide)
                            { p.deplacement(d); break; }
                        }
                    // Sinon, on ne déplace pas le poisson, il reste sur place
                    
                    // Détection collision avec le joueur 
                    collisionAvecJoueur(p.getPosition());
                } // Fin foreach poisson
                for(Crabe c : Map.this.m_Crabes)
                {
                    // Le crabe veut le joueur, d'abords il se met sur la même ligne
                    Position posC = c.getPosition(),
                             posJ = Map.this.m_Personnage.getPosition(),
                             posSuiv;
                    if( (posC.Ligne-posJ.Ligne) != 0)
                    {
                        // On le déplace pour aller a l'alignement
                        // Si le crabe est plus haut que le joueur
                        if( (posC.Ligne-posJ.Ligne) > 0) 
                        {
                            posSuiv = posC.addPosition(Direction.Bas);
                            if(Map.this.m_Map.get(posSuiv.Ligne).get(posSuiv.Colone).getType() == Constantes.Case.Vide)
                                c.deplacement(Direction.Bas);
                        }
                        else 
                        {
                            posSuiv = posC.addPosition(Direction.Haut);
                            if(Map.this.m_Map.get(posSuiv.Ligne).get(posSuiv.Colone).getType() == Constantes.Case.Vide)
                            c.deplacement(Direction.Haut);
                        }
                    }
                    // Sinon, il est sur la même ligne
                    else
                    {
                        if( (posC.Colone-posJ.Colone) != 0)
                        {
                            // On le déplace pour aller a l'alignement
                            // Si le crabe est plus a droite que le joueur
                            if( (posC.Colone-posJ.Colone) > 0) 
                            {
                                posSuiv = posC.addPosition(Direction.Gauche);
                                if(Map.this.m_Map.get(posSuiv.Ligne).get(posSuiv.Colone).getType() == Constantes.Case.Vide)
                                    c.deplacement(Direction.Gauche);
                            }
                            else
                            {
                                posSuiv = posC.addPosition(Direction.Droite);
                                if(Map.this.m_Map.get(posSuiv.Ligne).get(posSuiv.Colone).getType() == Constantes.Case.Vide)
                                    c.deplacement(Direction.Droite);
                            }
                        }
                    } // Fin déplacement en colone
                    
                    // Detection des collisions avec le joueur
                    collisionAvecJoueur(c.getPosition());
                } // Fin foreach Crabes
                
            }
        });
        this.m_Respire = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Map.this.m_Personnage.decOxygene();
                if(Map.this.m_Personnage.getOxygene() <= 0)
                    Map.this.m_Perdu = true;
            }
        });
        
        Timer action = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                Map.this.m_Respire.start();
                Map.this.m_DeplacementMonstres.start();
            }
        });
        action.start();
        this.m_VerificationGravite.start();
        
        
        
        Reader r;
        try
        { r = new FileReader(nomFichier); }
        catch (IOException e)
        { System.err.println(e.getMessage()); throw new LevelException("Niveau non trouvé"); }
        
        Scanner sc = new Scanner(r);
        Position p = new Position(0, 0);
        
        // La ligne va contenir toutes les informations nécéssaire pour la map
            // Exemple : direction initiale des poisson
        String ligneInformation = sc.nextLine();
        Direction directionInitialePoisson = Direction.Haut;
        
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
                        this.m_Personnage = new Personnage(p, oxygene);
                        break;
                    case 'D' : caseLigne.add(new Case(Constantes.Case.Pierre, p));  break;
                    case 'p' : 
                        caseLigne.add(new Case(Constantes.Case.Vide, p));
                        switch(ligneInformation.charAt(0))
                        {
                            case 'h' : directionInitialePoisson = Direction.Haut; break;
                            case 'b' : directionInitialePoisson = Direction.Bas; break;
                            case 'g' : directionInitialePoisson = Direction.Gauche; break;
                            case 'd' : directionInitialePoisson = Direction.Droite; break;
                        }
                        // On retire le carractère utilisé de la ligne d'information
                        ligneInformation = ligneInformation.substring(1);
                        this.m_Poissons.add(new Poisson(p, directionInitialePoisson));
                        break;
                    case 'o' : caseLigne.add(new Case(Constantes.Case.Porte, p));  break;
                    case 'm' : caseLigne.add(new Case(Constantes.Case.Sable, p));  break;
                    case 's' : 
                        caseLigne.add(new Case(Constantes.Case.Sortie, p));
                        this.m_PositionSortie = p;
                        break;
                    default : p.Colone--; break; // Si l'on ajoute pas de case, on fait en sorte de ne pas avancer
                }
                p.Colone++;
            }
            
            this.m_Map.add(caseLigne);
            p.Ligne++;
            p.Colone = 0;
        }// Fin de lecture du fichier
        if(this.m_Personnage == null) 
            throw new LevelException("Position du joueur non trouvée dans la map");
        if(this.m_PositionSortie == null)
            throw new LevelException("Le niveau ne contient pas de sortie");
        
    }// Fin du constructeur
    
    /**
     * Deplace le personnage retourne si réussite ou collision
     * @param directionDeplacement Direction du délacement enregistrée
     * @return Booléen qui indique si le personnage a réussi à se déplacer
     */
    public boolean deplacementPersonnage(Constantes.Direction directionDeplacement)
    {
        Case caseDeplacement = null;
        switch(directionDeplacement)
        {
            case Haut : 
                caseDeplacement = this.m_Map.get(this.m_Personnage.getPosition().Ligne-1).get(this.m_Personnage.getPosition().Colone);
                break;
            case Bas : 
                caseDeplacement = this.m_Map.get(this.m_Personnage.getPosition().Ligne+1).get(this.m_Personnage.getPosition().Colone);
                break;
            case Gauche : 
                caseDeplacement = this.m_Map.get(this.m_Personnage.getPosition().Ligne).get(this.m_Personnage.getPosition().Colone-1);
                break;
            case Droite :
                caseDeplacement = this.m_Map.get(this.m_Personnage.getPosition().Ligne).get(this.m_Personnage.getPosition().Colone+1);
                break;
        }
        
        // Si le joueur va dans le sable, il le détruit
        if(caseDeplacement.getType() == Constantes.Case.Sable)
        {
            caseDeplacement.transformeEnVide();
            this.m_Personnage.deplacement(directionDeplacement);
            return true;
        }
        // Si le joueur va dans le vide, rien de spécial
        if(caseDeplacement.getType() == Constantes.Case.Vide)
        {
            this.m_Personnage.deplacement(directionDeplacement); 
            return true; 
        }
        
        // Si le joueur prend la clef
        if(caseDeplacement.getType() == Constantes.Case.Clef)
        { 
            caseDeplacement.transformeEnVide();
            this.m_PortesOuvertes = true;
            this.ouvrePortes();
            this.m_Personnage.deplacement(directionDeplacement);
            return true;
        }
        
        // On teste maintenant si la case de déplacement est déplacable : est ce que le joueur pousse la case
        if(caseDeplacement.estDeplacable())
            // Si on a pu pousser la case
            if(this.pousseCase(this.m_Personnage.getPosition().addPosition(directionDeplacement), directionDeplacement))
            { 
                this.m_Personnage.deplacement(directionDeplacement);
                return true; 
            }
        
        // C'est que on ne peut pas se déplacer
        return false;
        
    }
    
    /**
     * Teste si la position p est dans l'une des 8 cases autour du joueur
     * Met a jour si la map est perdue
     * @param p La Position à tester
     */
    public void collisionAvecJoueur(Position p)
    {
        if(this.getPositionPersonnage().contact(p))
        { explosion(p); explosion(this.getPositionPersonnage()); this.m_Perdu = true; }
    }
    
    /**
     * Fonction qui fait exploser les 8 cases autour de la position p
     * @param p La position qui explose
     */
    public void explosion(Position p)
    { 
        this.m_Map.get(p.Ligne).get(p.Colone).transformeEnVide();
        for(int i = p.Ligne - 1; i <= p.Ligne+1; i++)
        {
            // Tests si en dehors de la map
            if(i < 0) continue;
            if(i >= this.m_Map.size()) continue;
            
            for(int j = p.Colone - 1; j <= p.Colone+1; j++)
            {
                // Même tests que précédement
                if(j < 0) continue;
                if(j >= this.m_Map.get(i).size()) continue;
                // On test si le joueur est dans cette position
                if(this.getPositionPersonnage().contact(new Position(i, j))) this.m_Perdu = true;
                // S'il s'agit d'une bombe
                if(this.m_Map.get(i).get(j).getType() == Constantes.Case.Bombe)
                    explosion(new Position(i, j));
                if(this.m_Map.get(i).get(j).m_EstDestructible)
                    this.m_Map.get(i).get(j).transformeEnVide();
            }
        }
    }
    
    /**
    
    /**
     * Pousse la case indiquée en position dans la direction passée en parramètre
     * @param p La position de la case à déplacer
     * @param direction Direction du déplacement
     * @return Retourne s'il y a succès ou echec au déplacement
     * @see Constantes.Position
     * @see Constantes.Direction
     */
    public boolean pousseCase(Position p, Constantes.Direction direction)
    {
        // La seule condition est que la position soit libre
        Position nouvellePosition = p.addPosition(direction);
        Case caseDestination = this.m_Map.get(nouvellePosition.Ligne).get(nouvellePosition.Colone);
        
        if(caseDestination.getType() != Constantes.Case.Vide)
            return false;
        
        // Inversion des deux cases
        // La destination devient l'origine
        this.m_Map.get(nouvellePosition.Ligne).set(nouvellePosition.Colone, this.m_Map.get(p.Ligne).get(p.Colone));
        // On met a jour la position de la case
        this.m_Map.get(nouvellePosition.Ligne).get(nouvellePosition.Colone).deplacement(direction);
        // L'origine devient une case vide
        this.m_Map.get(p.Ligne).set(p.Colone, new Case(Constantes.Case.Vide, p));
        
        return true;
    }
    
    /**
     * Retourne la position du personnage sur la map
     * @return Retourne la position
     * @see Moteur.Vivant.Personnage
     * @see Moteur.Constantes.Position
     */
    public Position getPositionPersonnage() { return this.m_Personnage.getPosition(); }
    
    /**
     * Ouvre les portes si la clef a été récupérée
     */
    public void ouvrePortes()
    {
        if(this.m_PortesOuvertes)
            for(ArrayList<Case> ac : this.m_Map)
                for(Case c : ac)
                    if(c.getType() == Constantes.Case.Porte)
                        c.transformeEnVide();
    }
    
    
    private Thread m_VerificationGravite;
    private Thread m_DeplacementMonstres;
    private Thread m_Respire;
    
    // Liste des etres vivants
    private Personnage m_Personnage;
    private ArrayList<Crabe> m_Crabes;
        /**
         * Retourne l'ensemble des crabes de la map
         * @return Retourne un ArrayList
         * @see ArrayList
         * @see Crabe
         */
        public ArrayList<Crabe> getCrabes() { return this.m_Crabes; }
    private ArrayList<Poisson> m_Poissons;
        /**
         * Retourne l'ensemble des poissons de la map
         * @return Retourne un ArrayList
         * @see ArrayList
         * @see Poisson
         */
        public ArrayList<Poisson> getPoissons() { return this.m_Poissons; }
    
    // Equivalent de savoir si la clef à été prise ou pas encore
    private boolean m_PortesOuvertes;
    
    /** 
     * Récupération de la map en cours
     * @return Double Array List de Case
     * @see Moteur.Case
     */
    public ArrayList< ArrayList<Moteur.Case> > getMap() { return this.m_Map; }
    private ArrayList< ArrayList<Moteur.Case> > m_Map;
    private int m_NombreBalleRouge;
    
    /**
     * Retourne si le personnage peut sortir du niveau
     * @return Retourne un booléen
     */
    public boolean Sortie()
    {
        if(!this.Gagne()) return false;
        return (this.m_Personnage.getPosition().addPosition(Direction.Bas) == this.m_PositionSortie);
    }
    private Position m_PositionSortie;
    
    /**
     * Retourne si la sortie est ouverte
     * @return Retourne si toutes les balles rouges ont été rentrées
     */
    public boolean Gagne() { return this.m_NombreBalleRouge == 0; }
    /**
     * Retourne si la map est perdue : si le joueur est mort
     * @return Retourne un booléen
     */
    public boolean EstPerdue() { return this.m_Perdu; }
    private boolean m_Perdu;
    
}
    