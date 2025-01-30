package info.reinput.reinputworkspaceservice.folder.application.dto;

import info.reinput.reinputworkspaceservice.folder.domain.Folder;

import java.util.List;
import java.util.Map;

public record FolderCollection (
        List<FolderDto> folders
){

    public static FolderCollection fromEntities(List<Folder> folders, Map<Long, Long> insightCounts) {
        return new FolderCollection(
                folders.stream()
                        .map(folder -> FolderDto.fromEntity(folder, insightCounts.get(folder.getId())))
                        .toList()
        );
    }
}
