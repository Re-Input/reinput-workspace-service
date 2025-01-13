package info.reinput.reinputworkspaceservice.folder.presentation.dto.res;

import info.reinput.reinputworkspaceservice.folder.application.dto.FolderDto;
import lombok.Builder;

@Builder
public record FolderCreateRes(
        FolderDto createdFolder
) {
    public static FolderCreateRes fromDto(FolderDto folderDto) {
        return FolderCreateRes.builder()
                .createdFolder(folderDto)
                .build();
    }
}
