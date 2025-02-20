package info.reinput.reinputworkspaceservice.folder.application;

import info.reinput.reinputworkspaceservice.folder.application.dto.FolderCollection;
import info.reinput.reinputworkspaceservice.folder.application.dto.FolderDto;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderCreateReq;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderPatchReq;

import java.util.List;

public interface FolderService {
    FolderDto createFolder(final FolderCreateReq folderCreateReq, final Long memberId);
    FolderDto getFolder(final Long folderId, final Long memberId);
    void deleteFolder(final Long folderId, final Long memberId);
    FolderDto updateFolder(final FolderPatchReq folderPatchReq, final Long memberId);
    FolderCollection getFolders(final Long memberId);
    FolderCollection createFolders(final List<FolderCreateReq> folderCreateReqs, final Long memberId);

}
