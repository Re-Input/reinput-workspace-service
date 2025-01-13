package info.reinput.reinputworkspaceservice.folder.application.dto;

import info.reinput.reinputworkspaceservice.folder.domain.Color;
import info.reinput.reinputworkspaceservice.folder.domain.Folder;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FolderDto (
        Long id,
        String name,
        Color color,
        Long memberId,
        LocalDateTime createdAt
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
}
