package cc_test.cc_bo;

/**
 * BusinessException
 * @author Fernando
 */
public class BusinessException extends Exception {
    
    public BusinessException(String message, Exception e) {
        super(message, e);
    }
    
    public BusinessException(String message) {
        super(message);
    }
    
}
