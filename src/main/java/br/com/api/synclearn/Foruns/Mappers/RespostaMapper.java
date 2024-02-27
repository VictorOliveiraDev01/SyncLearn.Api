package br.com.api.synclearn.Foruns.Mappers;

import br.com.api.synclearn.Foruns.DTO.RespostaDTO;
import br.com.api.synclearn.Foruns.MongoEntities.Resposta;

import java.util.List;
import java.util.stream.Collectors;

public class RespostaMapper {

    public static RespostaDTO toDTO(Resposta resposta) {
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setAutor(resposta.getAutor());
        respostaDTO.setTexto(resposta.getTexto());
        respostaDTO.setDataCriacao(resposta.getDataCriacao());
        respostaDTO.setRecursoAnexado(RecursoAnexadoMapper.toDTO(resposta.getRecursoAnexado()));
        respostaDTO.setRespostas(RespostaMapper.respostasToRespostaDTOs(resposta.getRespostas()));

        return respostaDTO;
    }

    public static List<RespostaDTO> respostasToRespostaDTOs(List<Resposta> respostas) {
        return respostas.stream()
                .map(resposta -> toDTO(resposta))
                .collect(Collectors.toList());
    }
}
