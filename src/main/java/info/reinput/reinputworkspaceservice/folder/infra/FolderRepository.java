package info.reinput.reinputworkspaceservice.folder.infra;

import info.reinput.reinputworkspaceservice.folder.domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long>, FolderRepositoryCustom {

}
