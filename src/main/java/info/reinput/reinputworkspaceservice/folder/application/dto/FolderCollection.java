package info.reinput.reinputworkspaceservice.folder.application.dto;

import info.reinput.reinputworkspaceservice.folder.domain.Folder;

import java.util.List;

public record FolderCollection (
        List<FolderDto> folders
){

    public static FolderCollection fromEntities(List<Folder> folders, List<Integer> insightCounts) {
        return new FolderCollection(
                folders.stream()
                        .map(folder -> FolderDto.fromEntity(folder, insightCounts.get(folders.indexOf(folder))))
                        .toList()
        );
    }
}
