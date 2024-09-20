package galgo.com.backend.exception;


import galgo.com.backend.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static galgo.com.backend.utilities.Constant.DATABASE_QUERY_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(Exception exception, HttpServletRequest request){
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Error en el servidor " + exception);
        log.info("Error en el servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Error en la petición " + exception.getAllErrors().stream().toList());
        log.info("-----------------Error en el servidor");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    /**
     * Data Access exception
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handlerDataAccessException(DataAccessException exception, HttpServletRequest request){
        Map<String, Object> response = new HashMap<>();
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("error: " + exception.getMostSpecificCause().getMessage());
        log.info("-----------------DataAccessException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handlerSQLException(DataIntegrityViolationException exception, HttpServletRequest httpServletRequest){
        Map<String, Object> response = new HashMap<>();
        response.put("message", DATABASE_QUERY_ERROR);
        response.put("error", exception.getMostSpecificCause().getMessage());
        log.info("-----------------DataIntegrityViolationException");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleValidationError(ConstraintViolationException exception) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();

        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            Map<String, String> transformedError = new HashMap<>();

            String fieldName = violation.getPropertyPath().toString();
            transformedError.put("field", fieldName.substring(fieldName.lastIndexOf('.') + 1));
            transformedError.put("error", violation.getMessage());

            errors.add(transformedError);
        }
        response.put("errors", errors);
        log.info("------------------handleValidationError");
        return response;
    }
}
