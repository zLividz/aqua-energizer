package Exceptions;


/**
 * Classe fille d'Exception qui va g�rer les exceptions des cases
 * @author Vincent BELLEC
 * @see Exception
 */
public class CaseException extends Exception
{
    /**
     * Constructeur de l'exception
     * @param message Le message de l'exception, r�cup�rable par getMessage
     */
    public CaseException(String message)
    { super(message); }
    
}
