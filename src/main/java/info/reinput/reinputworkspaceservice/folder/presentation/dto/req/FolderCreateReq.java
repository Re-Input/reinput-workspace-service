package info.reinput.reinputworkspaceservice.folder.presentation.dto.req;

import info.reinput.reinputworkspaceservice.folder.domain.Color;
import lombok.Builder;

@Builder
public record FolderCreateReq (
        String folderName,
        Color folderColor
){
}
