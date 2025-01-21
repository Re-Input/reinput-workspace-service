package info.reinput.reinputworkspaceservice.folder.presentation.dto.req;

import info.reinput.reinputworkspaceservice.folder.application.dto.ShareDto;
import info.reinput.reinputworkspaceservice.folder.domain.Color;
import info.reinput.reinputworkspaceservice.folder.domain.Share;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FolderPatchReq(
        @NotNull(message = "folderId is required")
        Long folderId,
        String folderName,
        Color folderColor,
        ShareReq share
) {
}
