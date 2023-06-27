package exception;

import java.sql.Struct;

import org.springframework.cache.interceptor.CacheOperationInvoker.ThrowableWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException{

public BadRequest () {
	super();
}

public BadRequest(String message) {
	
	super(message);
}

public BadRequest(Throwable cause) {

	super(cause);
}

public BadRequest(String message,Throwable cause) {
	
	super(message,cause);
}

}
