package info.reinput.reinputworkspaceservice.folder.infra;

import info.reinput.reinputworkspaceservice.folder.domain.Folder;

public interface FolderRepositoryCustom {
    Folder fetchFolderWithOptionalShare(Long folderId, boolean includeShare);
}
