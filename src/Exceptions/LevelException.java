package Exceptions;


/**
 * Classe fille d'Exception qui va gérer les exceptions de niveau
 * @author Vincent BELLEC
 * @see Exception
 */
public class LevelException extends Exception
{
    /**
     * Constructeur de l'exception
     * @param message Le message de l'exception, récupérable par getMessage
     */
    public LevelException(String message)
    { super(message); }
    
}
