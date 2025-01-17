package info.reinput.reinputworkspaceservice.folder.presentation;

import info.reinput.reinputworkspaceservice.folder.application.ShareService;
import info.reinput.reinputworkspaceservice.folder.application.dto.ShareDto;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.ShareCreateReq;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.res.ApiResponse;
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
