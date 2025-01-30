package info.reinput.reinputworkspaceservice.folder.infra.client;

import info.reinput.reinputworkspaceservice.folder.application.dto.InsightCountCollection;
import info.reinput.reinputworkspaceservice.folder.presentation.dto.res.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "content-service")
public interface ContentClient {
    @GetMapping("/insight/count/folder/{folderId}")
    ApiResponse<Long> countInsight(
            @PathVariable final Long folderId,
            @RequestHeader("X-User-Id") final Long memberId
    );

    @GetMapping("/insight/count/folders/{folderIds}")
    ApiResponse<InsightCountCollection> countInsight(
            @PathVariable final List<Long> folderIds,
            @RequestHeader("X-User-Id") final Long memberId
    );
}
