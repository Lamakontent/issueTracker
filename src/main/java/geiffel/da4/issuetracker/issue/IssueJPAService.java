package geiffel.da4.issuetracker.issue;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("jpa")
@Primary
public class IssueJPAService implements IssueService {

    @Autowired
    private IssueRepository issueRepository;
    @Override
    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    @Override
    public Issue getByCode(Long code) {
        Optional<Issue> issue = issueRepository.findById(code);
        if(issue.isPresent()){
            return issue.get();
        }
        else{
            throw new ResourceNotFoundException("issue", code);
        }
    }

    @Override
    public Issue create(Issue newIssue) {
        Long code = newIssue.getCode();
        if(issueRepository.existsById(newIssue.getCode())){
            throw new ResourceAlreadyExistsException("issue", code);
        }
        return issueRepository.save(newIssue);
    }

    @Override
    public void update(Long code, Issue updateIssue) {
        Optional<Issue> issue = issueRepository.findById(code);
        if(issue.isPresent()){
            issueRepository.save(updateIssue);
        }
        else{
            throw new ResourceNotFoundException("issue", code);
        }

    }

    @Override
    public void delete(Long code) {
        Optional<Issue> issue = issueRepository.findById(code);
        if(issue.isPresent()){
            issueRepository.delete(getByCode(code));
        }
        else {
            throw new ResourceNotFoundException("issue", code);
        }
    }
}
