package geiffel.da4.issuetracker.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // = DAO
public interface UserRepository extends JpaRepository<User, Long> {
}
