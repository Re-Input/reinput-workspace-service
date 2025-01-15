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
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private boolean isCopyable;
    private String shareUrl;
}
