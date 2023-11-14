package geiffel.da4.issuetracker.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // = DAO
public interface IssueRepository extends JpaRepository<Issue, Long>{
}
