package geiffel.da4.issuetracker.comentaire;

import geiffel.da4.issuetracker.issue.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // = DAO
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
}
