package info.reinput.reinputworkspaceservice.folder.application.impl;

import info.reinput.reinputworkspaceservice.folder.application.FolderService;
import info.reinput.reinputworkspaceservice.folder.application.dto.FolderDto;
import info.reinput.reinputworkspaceservice.folder.domain.Folder;
import info.reinput.reinputworkspaceservice.folder.infra.FolderRepository;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderCreateReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FolderServiceImpl implements FolderService {
    private final FolderRepository folderRepository;

    @Transactional
    public FolderDto createFolder(final FolderCreateReq folderCreateReq, final Long memberId){
        log.info("createFolder start");

        Folder folder = Folder.createFolder(folderCreateReq.folderName(),folderCreateReq.folderColor(), memberId);
        return FolderDto.fromEntity(folderRepository.save(folder));
    }
}
