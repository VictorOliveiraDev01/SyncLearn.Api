package br.com.api.synclearn.Utils;

import br.com.api.synclearn.Usuarios.MongoEntities.Aluno;
import br.com.api.synclearn.Usuarios.MongoEntities.Professor;
import br.com.api.synclearn.Usuarios.MongoRepositories.AlunoRepository;
import br.com.api.synclearn.Usuarios.MongoRepositories.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MongoEntityUtils {
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private static final Logger log = LoggerFactory.getLogger(MongoEntityUtils.class);
    @Inject
    public MongoEntityUtils(AlunoRepository alunoRepository, ProfessorRepository professorRepository){
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public Object findUsuarioById(String id, Class<?> userType) {
        if(id.isBlank()) {
            throw new IllegalArgumentException("ID do usuário não informado");
        }

        Object usuario = null;

        if (userType.equals(Aluno.class)) {
            usuario = alunoRepository.findById(new ObjectId(id));
        } else if (userType.equals(Professor.class)) {
            usuario = professorRepository.findById(new ObjectId(id));
        }

        if (usuario == null) {
            log.info("Usuário informado não encontrado. ID fornecido: {}", id);
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        return usuario;
    }
}
