package exception;

import java.sql.Struct;

import org.springframework.cache.interceptor.CacheOperationInvoker.ThrowableWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends RuntimeException{

public InternalServerError () {
	super();
}

public InternalServerError(String message) {
	
	super(message);
}

public InternalServerError(Throwable cause) {

	super(cause);
}

public InternalServerError(String message,Throwable cause) {
	
	super(message,cause);
}

}
