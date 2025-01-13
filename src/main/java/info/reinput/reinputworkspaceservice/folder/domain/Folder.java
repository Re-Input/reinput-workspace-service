package info.reinput.reinputworkspaceservice.folder.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "folder")
public class Folder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Long id;

    @Column(name = "folder_name")
    private String name;

    @Column(name = "folder_color")
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "member_id")
    private Long memberId;

    @Embedded
    private Share share;

    @Embedded
    private TimeAudit timeAudit;
}
