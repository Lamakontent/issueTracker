package geiffel.da4.issuetracker.comentaire;

import java.util.List;
public interface CommentaireService {

    Commentaire getById(Long id);

    List<Commentaire> getAllByAuthorId(Long param);

    Commentaire create(Commentaire unCom);

    void delete(Long param);

    List<Commentaire> getAllByIssueCode(Long param);

    void update(Long id, Commentaire unCom);

    List<Commentaire> getAll();

}
