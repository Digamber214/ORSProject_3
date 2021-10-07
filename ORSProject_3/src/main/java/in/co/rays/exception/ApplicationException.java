package in.co.rays.exception;


/**
 * ApplicationException is propagated from Service classes when an business
 * logic exception occurred.
 * @author Digamber
 *
 */
public class ApplicationException extends Exception{

	
	private static final long serialVersionUID = 1L;

	
	/**
	 * @param msg
	 *      : Error message
	 */
	public ApplicationException(String msg) {
		super(msg);
	}
}
