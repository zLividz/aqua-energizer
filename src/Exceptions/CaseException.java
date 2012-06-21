package Exceptions;


/**
 * Classe fille d'Exception qui va gérer les exceptions des cases
 * @author Vincent BELLEC
 * @see Exception
 */
public class CaseException extends Exception
{
    /**
     * Constructeur de l'exception
     * @param message Le message de l'exception, récupérable par getMessage
     */
    public CaseException(String message)
    { super(message); }
    
}
