package info.reinput.reinputworkspaceservice.folder.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.UUID;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ShareDto(
        String shareId,
        @NotEmpty(message = "shareName is required")
        Boolean isCopyable,
        String shareUrl
) {
}
