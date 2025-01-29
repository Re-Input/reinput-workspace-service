package info.reinput.reinputworkspaceservice.folder.application.port.out;

import java.util.List;

public interface ContentPort {
    Integer countInsight(final Long folderId, final Long memberId);
    List<Integer> countInsight(final List<Long> folderIds, final Long memberId);
}
