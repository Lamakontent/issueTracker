package geiffel.da4.issuetracker.issue;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.utils.LocalService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IssueLocalService extends LocalService<Issue, Long> implements IssueService {

    public IssueLocalService(){

    }
    public IssueLocalService(List<Issue> issues) {
        super(issues);
    }

    @Override
    protected String getIdentifier() {
        return "code";
    }


    @Override
    public List<Issue> getAll() {
        return super.getAll();
    }

    @Override
    public void update(Long code, Issue issue) {
        IndexAndValue<Issue> found = this.findByCode(code);
        this.allValues.remove(found.index());
        this.allValues.add(found.index(), issue);
    }

    @Override
    public Issue getByCode(Long param1) {
        return super.getByIdentifier(param1);
    }

    @Override
    public Issue create(Issue param1) {
        try {
            this.findByCode(param1.getCode());
            throw new ResourceAlreadyExistsException("Issue", param1.getCode());
        } catch (ResourceNotFoundException e) {
            this.allValues.add(param1);
            return param1;
        }
    }

    public IndexAndValue<Issue> findByCode(Long param1){

        return super.findByProperty(param1);
    }

    @Override
    public void delete(Long param1) {
        IndexAndValue<Issue> found = this.findByCode(param1);
        this.allValues.remove(found.value());
    }
}
