package info.reinput.reinputworkspaceservice.folder.application.impl;

import info.reinput.reinputworkspaceservice.folder.application.ShareService;
import info.reinput.reinputworkspaceservice.folder.application.dto.FolderDto;
import info.reinput.reinputworkspaceservice.folder.application.dto.ShareDto;
import info.reinput.reinputworkspaceservice.folder.domain.Folder;
import info.reinput.reinputworkspaceservice.folder.infra.FolderRepository;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.ShareCreateReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ShareServiceImpl implements ShareService {

    private final FolderRepository folderRepository;

    @Override
    @Transactional
    public ShareDto createShare(final ShareCreateReq shareCreateReq, final Long memberId) {
        log.info("createShare folderId : {}, memberId : {}", shareCreateReq.folderId(), memberId);

        Folder folder = folderRepository.findByIdAndMemberId(shareCreateReq.folderId(),memberId)
                .orElseThrow(() -> new IllegalArgumentException("Folder not found"));

        //todo check share duplication

        //todo create share propagation to insight service

        return ShareDto.fromEntity(folder.createShare(shareCreateReq.isCopyable()));

    }

    @Override
    @Transactional
    public FolderDto copySharedFolder(final String shareId, final Long memberId){
        log.info("copySharedFolder shareId : {}, memberId : {}", shareId, memberId);

        Folder folder = folderRepository.fetchFolderWithShare(shareId)
                .orElseThrow(() -> new IllegalArgumentException("Folder not found"));

        validateShareFolder(folder);

        Folder copiedFolder = Folder.copyFolder(folder, memberId);

        //todo copy propagation to insight service

        return FolderDto.fromEntity(folderRepository.save(copiedFolder));
    }

    private static void validateShareFolder(Folder folder) {
        if(folder.getShare() == null){
            throw new IllegalArgumentException("Folder is not shared");
        }else if (!folder.getShare().isCopyable()){
            throw new IllegalArgumentException("Folder is not copyable");
        }
    }

}
