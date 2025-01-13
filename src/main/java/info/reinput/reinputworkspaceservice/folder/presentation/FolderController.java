package info.reinput.reinputworkspaceservice.folder.presentation;

import info.reinput.reinputworkspaceservice.folder.application.FolderService;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderCreateReq;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.res.FolderCreateRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
