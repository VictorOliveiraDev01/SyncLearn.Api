package br.com.api.synclearn.Usuarios.MongoRepositories;

import br.com.api.synclearn.Usuarios.MongoEntities.Aluno;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlunoRepository implements PanacheMongoRepository<Aluno> {
    public Aluno findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
