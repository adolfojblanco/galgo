package galgo.com.backend.exception;


import galgo.com.backend.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(HttpServletRequest httpServletRequest, Exception exception){
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(httpServletRequest.getRequestURL().toString());
        apiError.setMethod(httpServletRequest.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Error en el servidor");

        return ResponseEntity.internalServerError().body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(HttpServletRequest httpServletRequest, MethodArgumentNotValidException exception){
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(httpServletRequest.getRequestURL().toString());
        apiError.setMethod(httpServletRequest.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Error en la petición " + exception.getAllErrors().stream().toList());

        return ResponseEntity.badRequest().body(apiError);
    }
}
