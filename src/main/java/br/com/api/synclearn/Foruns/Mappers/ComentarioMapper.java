package br.com.api.synclearn.Foruns.Mappers;

import br.com.api.synclearn.Foruns.DTO.ComentarioDTO;
import br.com.api.synclearn.Foruns.DTO.RespostaDTO;
import br.com.api.synclearn.Foruns.MongoEntities.Comentario;
import br.com.api.synclearn.Foruns.MongoEntities.Resposta;

import java.util.List;
import java.util.stream.Collectors;

public class ComentarioMapper {

    public static ComentarioDTO toDTO(Comentario comentario){
        List<RespostaDTO> respostaDTOs = RespostaMapper.respostasToRespostaDTOs(comentario.getRespostas());
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setAutor(comentario.getAutor());
        comentarioDTO.setTexto(comentario.getTexto());
        comentarioDTO.setDataCriacao(comentario.getDataCriacao());
        comentarioDTO.setRespostas(respostaDTOs);
        return comentarioDTO;
    }

    public static List<ComentarioDTO> comentariosToComentarioDTOs(List<Comentario> comentarios) {
        return comentarios.stream()
                .map(ComentarioMapper::toDTO)
                .toList();
    }
}
