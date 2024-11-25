package TurneraMedicaTP;

import java.sql.SQLException;

public class ServiceException extends SQLException {
	public ServiceException() {
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}