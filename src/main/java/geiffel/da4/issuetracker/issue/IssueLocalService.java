package geiffel.da4.issuetracker.issue;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.utils.LocalService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class IssueLocalService extends LocalService<Issue, Long> implements IssueService {



    private IssueService issueService;
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
    public User getByCode(Long l) {
        return getByIdentifier(l);
    }

    public IndexAndValue<Issue> findByCode(long l){
        return super.findByProperty(l);
    }

    @Override
    public ResponseEntity<Object> update(Long l, Issue capture) throws ResourceNotFoundException {

        IndexAndValue<Issue> found = this.findByCode(l);
        this.allValues.remove(found.index());
        this.allValues.add(found.index(), capture);
        return null;
    }

    @Override
    public Issue create(Issue toCreate) {
        try {
            this.findByCode(toCreate.getCode());
            throw new ResourceAlreadyExistsException("Issue", toCreate.getId());
        } catch (ResourceNotFoundException e) {
            this.allValues.add(toCreate);
            return toCreate;
        }
    }

    @Override
    public ResponseEntity<Object> delete(Long capture) {
        IndexAndValue<Issue> found = this.findByCode(capture);
        this.allValues.remove(found.value());
        return null;
    }

}