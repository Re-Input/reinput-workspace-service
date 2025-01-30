package info.reinput.reinputworkspaceservice.folder.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import info.reinput.reinputworkspaceservice.folder.domain.Color;
import info.reinput.reinputworkspaceservice.folder.domain.Folder;
import info.reinput.reinputworkspaceservice.folder.domain.Share;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FolderDto (
        Long id,
        String name,
        Color color,
        Long memberId,
        LocalDateTime createdAt,
        Long insightCount,
        Share share
){
    public Folder toEntity(){
        return Folder.builder()
                .name(name)
                .color(color)
                .memberId(memberId)
                .build();
    }

    public static FolderDto fromEntity(Folder folder){
        return FolderDto.builder()
                .id(folder.getId())
                .name(folder.getName())
                .color(folder.getColor())
                .memberId(folder.getMemberId())
                .createdAt(folder.getCreatedAt())
                .build();
    }

    public static FolderDto fromEntity(Folder folder, Long insightCount){
        return FolderDto.builder()
                .id(folder.getId())
                .name(folder.getName())
                .color(folder.getColor())
                .memberId(folder.getMemberId())
                .createdAt(folder.getCreatedAt())
                .insightCount(insightCount)
                .build();
    }
}
