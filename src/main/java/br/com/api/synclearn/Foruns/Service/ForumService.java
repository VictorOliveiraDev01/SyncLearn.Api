package br.com.api.synclearn.Foruns.Service;

import br.com.api.synclearn.Foruns.DTO.ForumDTO;
import br.com.api.synclearn.Foruns.Mappers.ForumMapper;
import br.com.api.synclearn.Foruns.MongoEntities.Comentario;
import br.com.api.synclearn.Foruns.MongoEntities.Forum;
import br.com.api.synclearn.Foruns.MongoEntities.Resposta;
import br.com.api.synclearn.Foruns.MongoRepositories.ComentarioRepository;
import br.com.api.synclearn.Foruns.MongoRepositories.ForumRepository;
import br.com.api.synclearn.Foruns.MongoRepositories.RespostaRepository;
import br.com.api.synclearn.Utils.MongoEntityUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class ForumService {

    private final ForumRepository forumRepository;
    private final ComentarioRepository comentarioRepository;
    private final RespostaRepository respostaRepository;
    private final MongoEntityUtils mongoEntityUtils;

    private static final Logger log = LoggerFactory.getLogger(ForumService.class);
    @Inject
    public ForumService(ForumRepository forumRepository,
                        MongoEntityUtils mongoEntityUtils,
                        ComentarioRepository comentarioRepository,
                        RespostaRepository respostaRepository){
        this.forumRepository = forumRepository;
        this.mongoEntityUtils = mongoEntityUtils;
        this.comentarioRepository = comentarioRepository;
        this.respostaRepository = respostaRepository;
    }

    public List<ForumDTO> findAllForuns(){
        log.info("Recuperando todos os fóruns...");
        return forumRepository.findAll()
                .stream()
                .map(ForumMapper::toDTO)
                .toList();
    }

    public ForumDTO findForumById(String id){
        Forum forum = mongoEntityUtils.findForumById(id);
        return ForumMapper.toDTO(forum);
    }

    public List<ForumDTO> findForunsByUser(String userId){
        if(userId == null){
            throw new IllegalArgumentException("Id de usuário não informado");
        }
        return forumRepository.findByAutor(new ObjectId(userId))
                .stream()
                .map(ForumMapper::toDTO)
                .toList();
    }

    public Forum addNewForum(@Valid Forum forum){
        log.info("Adicionando novo fórum: {}", forum);

        forumRepository.persist(forum);

        return forum;
    }

    public Forum updateForum(String id, @Valid Forum forum){
        Forum forumExistente = mongoEntityUtils.findForumById(id);

        forumExistente.setTema(forum.getTema());
        forumExistente.setDescricao(forum.getDescricao());

        forumRepository.update(forumExistente);

        return forumExistente;
    }

    public void addComentarioForum(String forumId, Comentario comentario) {
        log.info("Adicionando novo comentário ao fórum: {}", comentario);

        Forum forum = mongoEntityUtils.findForumById(forumId);

        comentarioRepository.persist(comentario);

        forum.getComentarios().add(comentario);

        forumRepository.update(forum);
    }

    public void addRespostaComentario(String forumId, String comentarioId, Resposta resposta) {
        log.info("Adicionando nova resposta ao comentário de id {} do fórum de id: {}. \nResposta: {}", comentarioId, forumId, resposta);


        Forum forum = mongoEntityUtils.findForumById(forumId);

        Comentario comentario = mongoEntityUtils.findComentarioForumById(comentarioId);

        comentario.getRespostas().add(resposta);

        forumRepository.update(forum);
    }



}
