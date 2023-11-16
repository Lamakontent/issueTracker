package geiffel.da4.issuetracker.comentaire;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.utils.LocalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CommentaireLocalService extends LocalService<Commentaire, Long> implements CommentaireService {

    public CommentaireLocalService(){
        super();
    }
    public CommentaireLocalService(List<Commentaire> commentaires){
        super(commentaires);
    }

    @Override
    protected String getIdentifier() {
        return "id";
    }

    public Commentaire getById(Long id){
        return super.getByIdentifier(id);
    }


    @Override
    public List<Commentaire> getAllByAuthorId(Long param) {
        return super.getAll().stream().filter(user -> Objects.equals(user.getAuthorId(), param)).toList();
    }

    @Override
    public Commentaire create(Commentaire unCom) {
        try {
            this.findByProperty(unCom.getId());
            throw new ResourceAlreadyExistsException("Commentaire", unCom.getId());
        } catch (ResourceNotFoundException e) {
            this.allValues.add(unCom);
            return unCom;
        }
    }

    @Override
    public void delete(Long param) {
        IndexAndValue<Commentaire> found = this.findByProperty(param);
        this.allValues.remove(found.value());
    }

    @Override
    public List<Commentaire> getAllByIssueCode(Long param) {
        return super.getAll().stream().filter(commentaire -> Objects.equals(commentaire.getIssueCode(), param)).toList();
    }

    @Override
    public void update(Long id, Commentaire unCom) throws ResourceNotFoundException {
        IndexAndValue<Commentaire> found = this.findByProperty(id);
        this.allValues.remove(found.index());
        this.allValues.add(found.index(), unCom);
    }

    @Override
    public List<Commentaire> getAll() {
        return super.getAll();
    }
}
