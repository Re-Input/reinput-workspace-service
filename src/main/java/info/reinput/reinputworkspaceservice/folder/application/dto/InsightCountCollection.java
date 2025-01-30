package info.reinput.reinputworkspaceservice.folder.application.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record InsightCountCollection (
        Map<Long, Long> insightCountMap
){
}
