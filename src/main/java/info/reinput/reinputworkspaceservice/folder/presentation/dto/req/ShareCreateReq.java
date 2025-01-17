package info.reinput.reinputworkspaceservice.folder.presentation.dto.req;

public record ShareCreateReq(
        Long folderId,
        boolean isCopyable
) {
}
