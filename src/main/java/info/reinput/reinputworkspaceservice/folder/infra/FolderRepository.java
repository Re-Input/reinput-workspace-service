package info.reinput.reinputworkspaceservice.folder.infra;

import info.reinput.reinputworkspaceservice.folder.domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long>, FolderRepositoryCustom {

    @Query("select f from Folder f where f.memberId = :memberId")
    Optional<List<Folder>> findByMemberId(Long memberId);

    @Query("select f from Folder f where f.id = :id and f.memberId = :memberId")
    Optional<Folder> findByIdAndMemberId(Long id, Long memberId);

    @Query("select f from Folder f join fetch f.share s where s.id = :shareId")
    Optional<Folder> fetchFolderWithShare(Long shareId);
}
