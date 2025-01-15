package info.reinput.reinputworkspaceservice.folder.presentation.dto.res;

import lombok.Builder;

@Builder
public record FolderRes (
        Long folderId,
        String folderName,
        String folderColor,
        Integer insightCount
){
    public static FolderRes fromDto(info.reinput.reinputworkspaceservice.folder.application.dto.FolderDto folderDto){
        return FolderRes.builder()
                .folderId(folderDto.id())
                .folderName(folderDto.name())
                .folderColor(folderDto.color().name())
                .insightCount(folderDto.insightCount())
                .build();
    }
}
