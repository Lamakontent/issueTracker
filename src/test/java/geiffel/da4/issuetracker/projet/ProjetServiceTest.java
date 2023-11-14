package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.issue.Issue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProjetServiceTest {

    private ProjetService projetService;

    private List<Projet> projet;

    @BeforeEach
    void setUp() {
        projet = new ArrayList<>(){{
            add(new Projet(1L, "projet 1"));
            add(new Projet(2L, "projet 2"));
            add(new Projet(3L, "projet 3"));
        }};
        projetService = new ProjetLocalService(projet);
    }

    @Test
    void whenGettingAll_shouldReturn3() {
        assertEquals(3, projetService.getAll().size());
    }

    @Test
    void whenGettingById_shouldReturnIfExists_andBeTheSame() {
        assertAll(
                () -> assertEquals(projet.get(0), projetService.getById(1L)),
                () -> assertThrows(ResourceNotFoundException.class, () -> projetService.getById(12L))
        );
    }

    @Test
    void whenCreating_shouldReturnTheSame(){
        Projet projet = new Projet(5L,"leNom");
        Projet projetRecu = projetService.create(projet);
        assertEquals(projet, projetRecu);

    }

    @Test
    void whenCreating_shouldBeInsideTheList(){
        Projet projet = new Projet(5L,"leNom");
        projetService.create(projet);
        assertEquals(projet, projetService.getById(projet.getId()));

    }

    @Test
    void whenCreatingWithExistingId_shouldThrowException(){
        Projet projet = new Projet(5L,"leNom");
        projetService.create(projet);
        assertThrows(ResourceAlreadyExistsException.class, () -> projetService.create(projet));

    }

    @Test
    void whenUpdating_isNotExit(){
        Projet projet = new Projet(5L,"leNom");
        assertThrows(ResourceNotFoundException.class, () -> projetService.update(projet));

    }

    @Test
    void whenUpdated_work(){

        Projet projet_init = projet.get(0);
        String nvnom = "oui";
        projet_init.setNom(nvnom);

        projetService.update(projet_init);
        Projet projetmodifier = projetService.getById(projet_init.getId());

        assertEquals(projet_init,projetmodifier);
    }

    @Test
    void whenDeleted_work(){



    }


    @Test
    void whenDeleting_isNotExit(){
        Projet projet = new Projet(4L,"leNom");
        assertThrows(ResourceNotFoundException.class, () -> projetService.delete(projet.getId()));

    }

}
