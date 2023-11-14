package geiffel.da4.issuetracker.issue;

import java.util.List;

public interface IssueService {

    List<Issue> getAll();

    void update(Long param1, Issue param2);

    Issue getByCode(Long code);

    Issue create(Issue param1);

    void delete(Long code);
}
