package info.reinput.reinputworkspaceservice.folder.presentation.dto.res;

public record ApiResponse<T>(
        Integer status,
        String message,
        T content
) {
}
