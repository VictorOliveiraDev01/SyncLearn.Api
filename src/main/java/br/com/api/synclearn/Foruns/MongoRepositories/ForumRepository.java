package br.com.api.synclearn.Foruns.MongoRepositories;

import br.com.api.synclearn.Foruns.MongoEntities.Forum;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class ForumRepository implements PanacheMongoRepository<Forum> {
    public List<Forum> findByAutor(ObjectId autor) {
        return find("autor", autor).list();
    }
}
