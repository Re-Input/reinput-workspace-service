package info.reinput.reinputworkspaceservice.folder.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "folder")
@SecondaryTable(name = "folder_share", pkJoinColumns = @PrimaryKeyJoinColumn(name = "folder_id"))
@AllArgsConstructor
@Builder
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

    public LocalDateTime getCreatedAt(){
        return timeAudit.getCreatedAt();
    }

    public LocalDateTime getUpdatedAt(){
        return timeAudit.getUpdatedAt();
    }

    public static Folder createFolder(String name, Color color, Long memberId){
        return Folder.builder()
                .name(name)
                .color(color)
                .memberId(memberId)
                .build();
    }
}
