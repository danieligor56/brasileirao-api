package exception;

import java.sql.Struct;

import org.springframework.cache.interceptor.CacheOperationInvoker.ThrowableWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException{

public NotFound () {
	super();
}

public NotFound(String message) {
	
	super(message);
}

public NotFound(Throwable cause) {

	super(cause);
}

public NotFound(String message,Throwable cause) {
	
	super(message,cause);
}

}
