package info.reinput.reinputworkspaceservice.folder.infra.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import info.reinput.reinputworkspaceservice.folder.domain.Folder;
import info.reinput.reinputworkspaceservice.folder.domain.QFolder;
import info.reinput.reinputworkspaceservice.folder.infra.FolderRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static info.reinput.reinputworkspaceservice.folder.domain.QFolder.folder;

@Repository
@RequiredArgsConstructor
public class FolderRepositoryCustomImpl implements FolderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Folder fetchFolderWithOptionalShare(Long folderId, boolean includeShare) {
        var query = queryFactory.selectFrom(folder)
                .where(folder.id.eq(folderId));
        if(includeShare){
            query.fetchJoin();
        }

        return query.fetchOne();
    }

}
