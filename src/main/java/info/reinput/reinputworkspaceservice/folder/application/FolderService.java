package info.reinput.reinputworkspaceservice.folder.application;

import info.reinput.reinputworkspaceservice.folder.application.dto.FolderDto;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderCreateReq;

public interface FolderService {
    FolderDto createFolder(final FolderCreateReq folderCreateReq, final Long memberId);
}
