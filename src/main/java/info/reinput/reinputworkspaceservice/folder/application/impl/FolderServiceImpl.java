package info.reinput.reinputworkspaceservice.folder.application.impl;

import info.reinput.reinputworkspaceservice.folder.application.FolderService;
import info.reinput.reinputworkspaceservice.folder.application.dto.FolderDto;
import info.reinput.reinputworkspaceservice.folder.domain.Folder;
import info.reinput.reinputworkspaceservice.folder.infra.FolderRepository;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderCreateReq;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderPatchReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

    @Transactional
    public void deleteFolder(final Long folderId, final Long memberId){
        log.info("deleteFolder start");
        Folder folder = folderRepository.findById(folderId).orElseThrow(() -> new IllegalArgumentException("Folder not found"));
        if(!Objects.equals(folder.getMemberId(), memberId)){
            throw new IllegalArgumentException("Member not matched");
        }

        //todo delete propagation to insight service
        folderRepository.delete(folder);
    }

    @Transactional
    public FolderDto updateFolder(final FolderPatchReq folderPatchReq, final Long memberId){
        log.info("updateFolder start");

        Folder folder = folderRepository.findById(folderPatchReq.folderId()).orElseThrow(
                () -> new IllegalArgumentException("Folder not found"));
        if(!Objects.equals(folder.getMemberId(), memberId)){
            throw new IllegalArgumentException("Member not matched");
        }

        folder.updateFolder(folderPatchReq.folderName(), folderPatchReq.folderColor());


        //todo insight count request to insight service
        return FolderDto.fromEntity(folder).setInsightCount(10);
    }




}
