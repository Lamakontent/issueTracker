package geiffel.da4.issuetracker.commentaire;


import geiffel.da4.issuetracker.user.Fonction;
import geiffel.da4.issuetracker.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommentaireTest {

    @Test
    void whenCreating_shouldBeAddedToIssueAndAuthorCommentairesList() {
        User user = new User(12L, "Machin", Fonction.USER);
        Issue issue = new Issue(1L, "The Issue", "The Content", user);
        Commentaire commentaire = new Commentaire(24L, user, issue, "Du contenu");

        assertAll(
                ()->assertTrue(issue.getCommentaires().contains(commentaire)),
                ()->assertTrue(user.getCommentairesEcrits().contains(commentaire))
        );
    }
}
