package geiffel.da4.issuetracker.issue;

import geiffel.da4.issuetracker.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/issues")
@CrossOrigin(origins = "*")
public class IssueController {

    private IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("")
    public List<Issue> getAll(){
        return issueService.getAll();
    }

    @GetMapping("/{code}")
    public Issue getIssueByCode(@PathVariable Long code) {
        return issueService.getByCode(code);
    }

    @PostMapping("")
    public ResponseEntity createIssue(@RequestBody Issue issue) {
        Issue created_issue = issueService.create(issue);
        return ResponseEntity.created(URI.create("/issues/"+created_issue.getCode().toString())).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity updateUser(@PathVariable Long code, @RequestBody Issue issue) {
        issueService.update(code, issue);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{code}")
    public ResponseEntity deleteUser(@PathVariable Long code) {
        issueService.delete(code);
        return ResponseEntity.noContent().build();
    }

}
