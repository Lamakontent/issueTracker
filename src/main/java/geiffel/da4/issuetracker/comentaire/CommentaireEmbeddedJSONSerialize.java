package geiffel.da4.issuetracker.comentaire;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CommentaireEmbeddedJSONSerialize extends JsonSerializer<Commentaire> {
    @Override
    public void serialize(Commentaire commentaire, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("url", "/commentaire/"+ commentaire.getId());
        gen.writeEndObject();

    }
}
