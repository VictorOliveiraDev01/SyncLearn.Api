package br.com.api.synclearn.Foruns.MongoRepositories;

import br.com.api.synclearn.Foruns.MongoEntities.Comentario;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class ComentarioRepository implements PanacheMongoRepository<Comentario> {
}
