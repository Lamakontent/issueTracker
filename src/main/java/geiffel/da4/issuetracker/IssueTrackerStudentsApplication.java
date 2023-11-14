package geiffel.da4.issuetracker;

import geiffel.da4.issuetracker.comentaire.Commentaire;
import geiffel.da4.issuetracker.comentaire.CommentaireRepository;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.issue.IssueRepository;
import geiffel.da4.issuetracker.user.Fonction;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class IssueTrackerStudentsApplication {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private CommentaireRepository commentaireRepository;

    public static void main(String[] args) {
        SpringApplication.run(IssueTrackerStudentsApplication.class, args);
    }
        @Bean
        public CommandLineRunner setupDB(){

            return (args) -> {
                List<User> users = new ArrayList<>(){{
                    add(new User(1L, "Machin", Fonction.USER));
                    add(new User(2L, "Chose", Fonction.USER));
                    add(new User(3L, "Truc", Fonction.DEVELOPPER));
                }};
                userRepository.saveAll(users);

                List<Issue> issues = new ArrayList<>(){{
                    add(new Issue(1L, "Issue 1", "prblm avec mon pc", users.get(0)));
                    add(new Issue(2L, "Issue 2", "prblm tout cour", users.get(1)));
                    add(new Issue(3L, "Issue 3", "jai pas de probleme lol", users.get(2)));

                }};
                issueRepository.saveAll(issues);

               List<Commentaire> commentaires = new ArrayList<>(){{
                    add(new Commentaire(1L, users.get(0), issues.get(0),"JE SAIOS PAS"));
                    add(new Commentaire(2L, users.get(1), issues.get(1),"Contenu 2"));
                    add(new Commentaire(3L, users.get(0), issues.get(2),"Contenu 3"));
                }};
               commentaireRepository.saveAll(commentaires);
            };


        }
}
