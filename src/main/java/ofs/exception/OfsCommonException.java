package ofs.exception;

public class OfsCommonException  extends Exception{
	


	private static final long serialVersionUID = 6323819350253711395L;
	
    public OfsCommonException(String message) {
        super(message);
    }
    
    

    public OfsCommonException(String message, Throwable cause) {
        super(message, cause);
    }
    

    public OfsCommonException(Throwable cause) {
        super(cause);
    }
	


}
