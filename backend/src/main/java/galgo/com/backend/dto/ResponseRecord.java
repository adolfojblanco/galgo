package galgo.com.backend.dto;

import lombok.Builder;

@Builder
public record ResponseRecord(String message, String status, Object data) {


}
