package in.co.rays.exception;

/**
 * DatabaseException is prpogated by DAO classes when an unhandled Database
 * exception occurred
 * @author Digamber
 *
 */
public class DatabaseException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 * error message
	 */
	public DatabaseException(String msg){
		super(msg);
	}

}
