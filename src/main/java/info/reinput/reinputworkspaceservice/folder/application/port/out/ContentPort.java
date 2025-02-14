package info.reinput.reinputworkspaceservice.folder.application.port.out;

import info.reinput.reinputworkspaceservice.folder.application.dto.InsightCountCollection;

import java.util.List;

public interface ContentPort {
    Long countInsight(final Long folderId, final Long memberId);
    InsightCountCollection countInsight(final List<Long> folderIds, final Long memberId);
    Integer copyInsight(final Long folderId, final Long memberId);
}
