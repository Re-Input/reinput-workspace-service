package info.reinput.reinputworkspaceservice.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Share {
    private Long id;
    private boolean isCopyable;
    private String shareUrl;
}
