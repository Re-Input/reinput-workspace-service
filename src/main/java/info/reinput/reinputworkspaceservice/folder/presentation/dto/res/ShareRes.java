package info.reinput.reinputworkspaceservice.folder.presentation.dto.res;

import lombok.Builder;

@Builder
public record ShareRes (
        String shareId,
        boolean isCopyable
){
}
