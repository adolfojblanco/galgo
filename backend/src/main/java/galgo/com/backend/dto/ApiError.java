package galgo.com.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError implements Serializable {

    private String backendMessage;
    private String message;
    private LocalDateTime timestamp;
    private String url;
    private String method;

}
