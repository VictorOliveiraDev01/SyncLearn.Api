package br.com.api.synclearn.Usuarios.Mappers;

import br.com.api.synclearn.Usuarios.DTO.InformacaoFormacaoDTO;
import br.com.api.synclearn.Usuarios.MongoEntities.Formacao.InformacaoFormacao;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InformacaoFormacaoMapper {
    public static InformacaoFormacaoDTO toDTO(InformacaoFormacao informacaoFormacao) {
        InformacaoFormacaoDTO informacaoFormacaoDTO = new InformacaoFormacaoDTO();
        informacaoFormacaoDTO.setUniversidade(informacaoFormacao.getUniversidade());
        informacaoFormacaoDTO.setCurso(informacaoFormacao.getCurso());
        informacaoFormacaoDTO.setAnoInicio(informacaoFormacao.getAnoInicio());
        informacaoFormacaoDTO.setAnoConclusao(informacaoFormacao.getAnoConclusao());
        return informacaoFormacaoDTO;
    }
}
