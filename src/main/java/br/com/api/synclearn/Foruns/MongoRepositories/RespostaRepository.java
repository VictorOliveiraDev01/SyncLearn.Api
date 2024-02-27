package br.com.api.synclearn.Foruns.MongoRepositories;

import br.com.api.synclearn.Foruns.MongoEntities.Resposta;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class RespostaRepository implements PanacheMongoRepository<Resposta> {
}
