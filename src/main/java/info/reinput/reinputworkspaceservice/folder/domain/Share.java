package info.reinput.reinputworkspaceservice.folder.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Share {
    @Id
    private String id;
    private boolean isCopyable;

    public static Share createShare(boolean copyable) {
        return Share.builder()
                .id(UUID.randomUUID().toString())
                .isCopyable(copyable)
                .build();
    }

    public Share updateShare(boolean copyable) {
        this.isCopyable = copyable;
        return this;
    }
}
