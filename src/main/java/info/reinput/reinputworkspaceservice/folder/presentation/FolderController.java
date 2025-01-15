package info.reinput.reinputworkspaceservice.folder.presentation;

import info.reinput.reinputworkspaceservice.folder.application.FolderService;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderCreateReq;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderPatchReq;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.res.FolderCreateRes;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.res.FolderRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/folder")
@Slf4j
public class FolderController {

    private final FolderService folderService;

    @PostMapping("create/v1")
    public ResponseEntity<FolderCreateRes> createFolder(
            final FolderCreateReq folderCreateReq,
            final @RequestHeader("X-User-Id") Long memberId){
        log.info("createFolder request : {}", folderCreateReq);
        FolderCreateRes res = FolderCreateRes.fromDto(folderService.createFolder(folderCreateReq, memberId));

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @DeleteMapping("/{folderId}/v1")
    public ResponseEntity<Void> deleteFolder(
            @PathVariable final Long folderId,
            @RequestHeader("X-User-Id") final Long memberId){
        log.info("deleteFolder request : {}", folderId);
        folderService.deleteFolder(folderId, memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/update/v1")
    public ResponseEntity<FolderRes> updateFolder(
            final FolderPatchReq folderPatchReq,
            final @RequestHeader("X-User-Id") Long memberId){
        log.info("updateFolder request : {}", folderPatchReq.folderId());
        FolderRes res = FolderRes.fromDto(folderService.updateFolder(folderPatchReq, memberId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
