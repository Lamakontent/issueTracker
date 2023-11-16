package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.utils.LocalService;

import java.util.List;

public class ProjetLocalService extends LocalService<Projet, Long> implements ProjetService{

    public ProjetLocalService(List<Projet> projet) {
        super(projet);
    }

    @Override
    protected String getIdentifier() {
        return "id";
    }

    @Override
    public List<Projet> getAll() {
        return super.getAll();
    }

    @Override
    public Projet getById(long id) {
        return super.getByIdentifier(id);
    }

    @Override
    public Projet create(Projet projet) {
        try{
            this.getById(projet.getId());
            throw new ResourceAlreadyExistsException("projet", projet.getId());
        }catch (ResourceNotFoundException e) {
            allValues.add(projet);
            return projet;
        }
    }

    @Override
    public void update(Projet projet) throws ResourceNotFoundException {
        IndexAndValue<Projet> found = this.findById(projet.getId());
        this.allValues.remove(found.index());
        this.allValues.add(found.index(), projet);


        /*try{
            this.getById(projet.getId());
            throw new ResourceNotFoundException("projet", projet.getId());
        }catch (ResourceAlreadyExistsException e) {
            IndexAndValue<Projet> found = this.findById(projet.getId());
            this.allValues.remove(found.index());
            this.allValues.add(found.index(), projet);
        }*/
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException{
        IndexAndValue<Projet> found = this.findByProperty(id);
        this.allValues.remove(found.value());
    }

    public IndexAndValue<Projet> findById(Long id){

        return super.findByProperty(id);
    }

}
