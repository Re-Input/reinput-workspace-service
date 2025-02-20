package info.reinput.reinputworkspaceservice.folder.presentation.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record FolderRes (
        Long folderId,
        String folderName,
        String folderColor,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long insightCount,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        ShareRes share
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
