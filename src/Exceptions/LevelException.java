package Exceptions;


/**
 * Classe fille d'Exception qui va g�rer les exceptions de niveau
 * @author Vincent BELLEC
 * @see Exception
 */
public class LevelException extends Exception
{
    /**
     * Constructeur de l'exception
     * @param message Le message de l'exception, r�cup�rable par getMessage
     */
    public LevelException(String message)
    { super(message); }
    
}
