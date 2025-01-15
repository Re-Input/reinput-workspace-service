package info.reinput.reinputworkspaceservice.folder.presentation.dto.req;

import info.reinput.reinputworkspaceservice.folder.domain.Color;
import jakarta.validation.constraints.NotEmpty;

public record FolderPatchReq(
        @NotEmpty(message = "folderId is required")
        Long folderId,
        String folderName,
        Color folderColor
) {
}
