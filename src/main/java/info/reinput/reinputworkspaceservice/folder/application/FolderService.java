package info.reinput.reinputworkspaceservice.folder.application;

import info.reinput.reinputworkspaceservice.folder.application.dto.FolderDto;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderCreateReq;

import java.util.List;

public interface FolderService {
    FolderDto createFolder(final FolderCreateReq folderCreateReq, final Long memberId);
    void deleteFolder(final Long folderId, final Long memberId);
    //List<FolderDto> getFolders(final Long memberId);


}
