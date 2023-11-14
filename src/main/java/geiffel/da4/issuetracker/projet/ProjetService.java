package geiffel.da4.issuetracker.projet;

import java.util.List;

public interface ProjetService {
    List<Projet> getAll();

    Projet getById(long id);

    Projet create(Projet projet);

    void update(Projet projet);

    void delete(Long id);
}
