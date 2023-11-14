package geiffel.da4.issuetracker.comentaire;

import geiffel.da4.issuetracker.comentaire.Commentaire;

import geiffel.da4.issuetracker.comentaire.CommentaireService;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/commentaires")
@CrossOrigin(origins = "*")
public class CommentaireController {


    private CommentaireService commentaireService;

    @Autowired
    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService=commentaireService;
    }

    @GetMapping("")
    public List<Commentaire> getAllCommentaires(){
        return commentaireService.getAll();
    }

    @GetMapping("/{id}")
    public Commentaire getCommentaire(@PathVariable Long id){
        return commentaireService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity createCommentaire(@RequestBody Commentaire com){
        Commentaire created_commentaire = commentaireService.create(com);
        return ResponseEntity.created(URI.create("/commentaires/"+created_commentaire.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Commentaire com){
        commentaireService.update(id, com);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        commentaireService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
