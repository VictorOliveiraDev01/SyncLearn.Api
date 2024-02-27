package br.com.api.synclearn.Foruns.Mappers;

import br.com.api.synclearn.Foruns.DTO.ForumDTO;
import br.com.api.synclearn.Foruns.MongoEntities.Forum;
import br.com.api.synclearn.Foruns.DTO.ComentarioDTO;
import br.com.api.synclearn.Foruns.MongoEntities.Comentario;
import br.com.api.synclearn.Usuarios.DTO.AlunoDTO;
import br.com.api.synclearn.Usuarios.DTO.ProfessorDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForumMapper {
    public static ForumDTO toDTO(Forum forum) {
        List<ComentarioDTO> comentarioDTOs = new ArrayList<>();
        if (forum.getComentarios() != null) {
            comentarioDTOs = ComentarioMapper.comentariosToComentarioDTOs(forum.getComentarios());
        }

        ForumDTO forumDTO = new ForumDTO();
        forumDTO.setAutor(forum.getAutor());
        forumDTO.setTema(forum.getTema());
        forumDTO.setDescricao(forum.getDescricao());
        forumDTO.setDataCriacao(forum.getDataCriacao());
        forumDTO.setComentarios(comentarioDTOs);

        return forumDTO;
    }


    public static List<ForumDTO> forumsToForumDTOs(List<Forum> forums) {
        return forums.stream()
                .map(ForumMapper::toDTO)
                .collect(Collectors.toList());
    }
}
