package info.reinput.reinputworkspaceservice.folder.infra;

import info.reinput.reinputworkspaceservice.folder.domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long>, FolderRepositoryCustom {

    @Query("select f from Folder f where f.memberId = :memberId")
    Optional<List<Folder>> findByMemberId(Long memberId);
}
