package info.reinput.reinputworkspaceservice.folder.presentation;

import info.reinput.reinputworkspaceservice.folder.application.ShareService;
import info.reinput.reinputworkspaceservice.folder.application.dto.FolderDto;
import info.reinput.reinputworkspaceservice.folder.application.dto.ShareDto;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.ShareCreateReq;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.res.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/folder/share")
public class ShareApiController {
    private final ShareService shareService;

    @Operation(
            summary = "[106] Copy Shared Folder",
            description = "공유된 폴더를 복사합니다. 폴더 내 인사이트들의 복사 설정 구현이 완료되지 않았습니다."
    )
    @GetMapping("/copy/{shareId}/v1")
    public ResponseEntity<ApiResponse<FolderDto>> copySharedFolder(
            @PathVariable final String shareId,
            @RequestHeader("X-User-Id") final Long memberId) {
        log.info("copySharedFolder request : {}", shareId);
        ApiResponse<FolderDto> response = ApiResponse.<FolderDto>builder()
                .status(HttpStatus.CREATED.value())
                .message("Folder copied")
                .data(shareService.copySharedFolder(shareId, memberId))
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "[107] Create Share",
            description = "폴더를 공유합니다."
    )
    @PostMapping("create/v1")
    public ResponseEntity<ApiResponse<ShareDto>> createShare(
            @RequestBody final ShareCreateReq shareCreateReq,
            @RequestHeader("X-User-Id") final Long memberId) {
        log.info("createShare request : {}", shareCreateReq.folderId());
        ApiResponse<ShareDto> response = ApiResponse.<ShareDto>builder()
                .status(HttpStatus.CREATED.value())
                .message("Share created")
                .data(shareService.createShare(shareCreateReq, memberId))
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



}
