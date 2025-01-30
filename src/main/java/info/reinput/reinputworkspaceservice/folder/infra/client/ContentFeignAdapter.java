package info.reinput.reinputworkspaceservice.folder.infra.client;

import info.reinput.reinputworkspaceservice.folder.application.dto.InsightCountCollection;
import info.reinput.reinputworkspaceservice.folder.application.port.out.ContentPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ContentFeignAdapter implements ContentPort {

    private final ContentClient contentClient;

    @Override
    public Long countInsight(final Long folderId, final Long memberId) {
        log.info("countInsight folderId : {}, memberId : {}", folderId, memberId);

        return contentClient.countInsight(folderId, memberId).data();
    }

    @Override
    public InsightCountCollection countInsight(final List<Long> folderIds, final Long memberId) {
        log.info("countInsight folderIds : {}, memberId : {}", folderIds, memberId);

        return contentClient.countInsight(folderIds, memberId).data();
    }


}
