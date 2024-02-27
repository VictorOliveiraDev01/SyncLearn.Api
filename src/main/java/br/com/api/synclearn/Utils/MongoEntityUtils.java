package br.com.api.synclearn.Utils;

import br.com.api.synclearn.Foruns.MongoEntities.Comentario;
import br.com.api.synclearn.Foruns.MongoEntities.Forum;
import br.com.api.synclearn.Foruns.MongoRepositories.ComentarioRepository;
import br.com.api.synclearn.Foruns.MongoRepositories.ForumRepository;
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
    private final ForumRepository forumRepository;

    private final ComentarioRepository comentarioRepository;
    private static final Logger log = LoggerFactory.getLogger(MongoEntityUtils.class);
    @Inject
    public MongoEntityUtils(AlunoRepository alunoRepository,
                            ProfessorRepository professorRepository,
                            ForumRepository forumRepository,
                            ComentarioRepository comentarioRepository){
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.forumRepository = forumRepository;
        this.comentarioRepository = comentarioRepository;
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

    public Forum findForumById(String id){
        if(id.isBlank()){
            throw new IllegalArgumentException("ID do fórum não informado");
        }
        Forum forum = forumRepository.findById(new ObjectId(id));
        if(forum == null){
            log.info("Fórum informado não encontrado. ID fornecido: {}", id);
            throw new EntityNotFoundException("Fórum não encontrado");
        }
        return forum;
    }

    public Comentario findComentarioForumById(String id){
        if(id.isBlank()){
            throw new IllegalArgumentException("ID do comentário não informado");
        }
        Comentario comentario = comentarioRepository.findById(new ObjectId(id));
        if(comentario == null){
            log.info("Comentario informado não encontrado. ID fornecido: {}", id);
            throw new EntityNotFoundException("Comentario não encontrado");
        }
        return comentario;
    }
}
