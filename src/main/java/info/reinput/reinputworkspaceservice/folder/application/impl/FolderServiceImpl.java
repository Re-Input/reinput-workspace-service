package info.reinput.reinputworkspaceservice.folder.application.impl;

import info.reinput.reinputworkspaceservice.folder.application.FolderService;
import info.reinput.reinputworkspaceservice.folder.application.dto.FolderCollection;
import info.reinput.reinputworkspaceservice.folder.application.dto.FolderDto;
import info.reinput.reinputworkspaceservice.folder.domain.Folder;
import info.reinput.reinputworkspaceservice.folder.infra.FolderRepository;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderCreateReq;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderPatchReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public FolderDto updateFolder(final FolderPatchReq folderPatchReq, final Long memberId) {
        log.info("updateFolder start");
        boolean includeShare = folderPatchReq.share() != null;

        Folder folder = folderRepository.fetchFolderWithOptionalShare(folderPatchReq.folderId(), includeShare);

        if (folder == null) {
            throw new IllegalArgumentException("Folder not found");
        }
        if (!Objects.equals(folder.getMemberId(), memberId)) {
            throw new IllegalArgumentException("Member not matched");
        }

        folder.updateFolder(folderPatchReq.folderName(), folderPatchReq.folderColor());

        if (includeShare) {
            //TODO: propagation to insight service if share is changed
            folder.updateShare(folderPatchReq.share().isCopyable());
        }


        // int insightCount = fetchInsightCount(folder.getId());
        int insightCount = 10; // 예시 값. 실제 구현에서는 외부 서비스 호출 필요.

        return FolderDto.fromEntity(folder, insightCount);
    }

    public FolderCollection getFolders(final Long memberId){
        log.info("getFolders start");
        List<Folder> folders = folderRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Folder not found"));
        List<Integer> insightCounts = fetchInsightCounts(folders.stream().map(Folder::getId).toList());

        return FolderCollection.fromEntities(folders, insightCounts);
    }

    @Transactional
    public FolderCollection createFolders(final List<FolderCreateReq> folderCreateReqs, final Long memberId){
        log.info("saveFolders start");
        List<Folder> folders = folderCreateReqs.stream()
                .map(folderCreateReq -> Folder.createFolder(folderCreateReq.folderName(), folderCreateReq.folderColor(), memberId))
                .toList();
        List<Folder> savedFolders = folderRepository.saveAll(folders);

        return FolderCollection.fromEntities(savedFolders, fetchInsightCounts(savedFolders.stream().map(Folder::getId).toList()));
    }


    private int fetchInsightCount(Long folderId){
        //todo insight count request to insight service
        return 1;
    }

    private List<Integer> fetchInsightCounts(List<Long> folderIds){
        //todo insight count request to insight service
        return null;
    }




}
