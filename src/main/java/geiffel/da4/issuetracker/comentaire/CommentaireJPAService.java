package geiffel.da4.issuetracker.comentaire;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("jpa")
@Primary

public class CommentaireJPAService implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;
    @Override
    public Commentaire getById(Long id) {
        Optional<Commentaire> commentaire = commentaireRepository.findById(id);
        if(commentaire.isPresent()){
            return commentaire.get();
        }
        else{
            throw new ResourceNotFoundException("commentaire", id);
        }
    }

    @Override
    public List<Commentaire> getAllByAuthorId(Long id) {
        return null;
    }

    @Override
    public List<Commentaire> getAllByIssueCode(Long code) {
        return null;
    }

    @Override
    public Commentaire create(Commentaire unCom) {
        Long id = unCom.getId();
        if(commentaireRepository.existsById(unCom.getId())){
            throw new ResourceAlreadyExistsException("commentaire", id);
        }
        return commentaireRepository.save(unCom);
    }

    @Override
    public void delete(Long id) {
        Optional <Commentaire> commentaire = commentaireRepository.findById(id);
        if(commentaire.isPresent()){
            commentaireRepository.delete(getById(id));
        }
        else{
            throw new ResourceNotFoundException("commentaire", id);
        }
    }

    @Override
    public void update(Long id, Commentaire unCom) {
        Optional<Commentaire> commentaire = commentaireRepository.findById(id);
        if(commentaire.isPresent()){
            commentaireRepository.save(unCom);
        }
        else{
            throw new ResourceNotFoundException("commentaire", id);
        }

    }

    @Override
    public List<Commentaire> getAll() {
        return commentaireRepository.findAll();
    }
}
