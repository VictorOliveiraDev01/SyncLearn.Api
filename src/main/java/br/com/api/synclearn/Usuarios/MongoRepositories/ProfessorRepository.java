package br.com.api.synclearn.Usuarios.MongoRepositories;

import br.com.api.synclearn.Usuarios.MongoEntities.Professor;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfessorRepository implements PanacheMongoRepository<Professor> {
    public Professor findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
