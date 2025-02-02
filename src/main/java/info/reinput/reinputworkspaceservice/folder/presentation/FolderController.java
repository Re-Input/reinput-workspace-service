package info.reinput.reinputworkspaceservice.folder.presentation;

import info.reinput.reinputworkspaceservice.folder.application.FolderService;
import info.reinput.reinputworkspaceservice.folder.application.dto.FolderCollection;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderCreateReq;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.req.FolderPatchReq;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.res.ApiResponse;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.res.FolderCreateRes;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.res.FolderRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/folder")
@Slf4j
public class FolderController {

    private final FolderService folderService;

    @Operation(
            summary = "[102] Create Folder",
            description = "폴더를 생성합니다."
    )
    @PostMapping("create/v1")
    public ResponseEntity<ApiResponse<FolderCreateRes>> createFolder(
            final FolderCreateReq folderCreateReq,
            final @Parameter(hidden = true) @RequestHeader("X-User-Id") Long memberId){
        log.info("createFolder request : {}", folderCreateReq);
        FolderCreateRes res = FolderCreateRes.fromDto(folderService.createFolder(folderCreateReq, memberId));
        ApiResponse<FolderCreateRes> response = ApiResponse.<FolderCreateRes>builder()
                .status(HttpStatus.CREATED.value())
                .message("Folder created")
                .data(res)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "[101] [미완성] Delete Folder",
            description = "폴더를 삭제합니다. 인사이트 서비스와 미연동으로 인한 폴더 삭제후에도 인사이트가 남아있을 수 있습니다.")
    @DeleteMapping("/{folderId}/v1")
    public ResponseEntity<Void> deleteFolder(
            @PathVariable final Long folderId,
            @Parameter(hidden = true) @RequestHeader("X-User-Id") final Long memberId){
        log.info("deleteFolder request : {}", folderId);
        folderService.deleteFolder(folderId, memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "[108] Update Folder",
            description = "폴더 정보를 수정합니다. ")
    @PatchMapping("/update/v1")
    public ResponseEntity<ApiResponse<FolderRes>> updateFolder(
            final FolderPatchReq folderPatchReq,
            final @Parameter(hidden = true) @RequestHeader("X-User-Id") Long memberId){
        log.info("updateFolder request : {}", folderPatchReq.folderId());
        FolderRes res = FolderRes.fromDto(folderService.updateFolder(folderPatchReq, memberId));
        ApiResponse<FolderRes> response = ApiResponse.<FolderRes>builder()
                .status(HttpStatus.OK.value())
                .message("Folder updated")
                .data(res)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "[109] Get Folders",
            description = "폴더 목록을 조회합니다.")
    @GetMapping("/v1")
    public ResponseEntity<ApiResponse<FolderCollection>> getFolders(
            final @Parameter(hidden = true) @RequestHeader("X-User-Id") Long memberId){
        log.info("getFolders request : {}", memberId);
        FolderCollection folderCollection = folderService.getFolders(memberId);
        ApiResponse<FolderCollection> response = ApiResponse.<FolderCollection>builder()
                .status(HttpStatus.OK.value())
                .message("Folders retrieved")
                .data(folderCollection)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "[x] Batch Create Folders",
            description = "폴더를 여러 개 생성합니다")
    @PostMapping("/batch-create/v1")
    public ResponseEntity<ApiResponse<FolderCollection>> createFolders(
            final @Parameter(hidden = true) @RequestHeader("X-User-Id") Long memberId,
            final @RequestBody List<FolderCreateReq> folderCreateReqs){
        log.info("createFolders request : {}", folderCreateReqs);
        FolderCollection folderCollection = folderService.createFolders(folderCreateReqs, memberId);
        ApiResponse<FolderCollection> response = ApiResponse.<FolderCollection>builder()
                .status(HttpStatus.CREATED.value())
                .message("Folders created")
                .data(folderCollection)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
