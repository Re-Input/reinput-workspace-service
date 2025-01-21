package info.reinput.reinputworkspaceservice.folder.presentation.dto.res;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
        Integer status,
        String message,
        T data
) {
}
