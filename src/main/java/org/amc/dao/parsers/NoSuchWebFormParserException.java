package org.amc.dao.parsers;

/**
 * No WebFormSearchToJPQL Parser defined
 * 
 * @author Adrian McLaughlin
 *
 */
public class NoSuchWebFormParserException extends Exception {
 
    private static final long serialVersionUID = -4345071277175885239L;

    public NoSuchWebFormParserException() {
        super();
    }

    public NoSuchWebFormParserException(Throwable throwable) {
        super(throwable);
    }

    public NoSuchWebFormParserException(String message) {
        super(message);
    }
}
