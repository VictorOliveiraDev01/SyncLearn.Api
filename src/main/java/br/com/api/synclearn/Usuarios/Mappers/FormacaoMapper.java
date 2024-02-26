package br.com.api.synclearn.Usuarios.Mappers;

import br.com.api.synclearn.Usuarios.DTO.FormacaoDTO;
import br.com.api.synclearn.Usuarios.MongoEntities.Formacao.Formacao;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.stream.Collectors;

@ApplicationScoped
public class FormacaoMapper {

    public static FormacaoDTO toDTO(Formacao formacao) {
        FormacaoDTO formacaoDTO = new FormacaoDTO();
        formacaoDTO.setInformacoesFormacao(
                formacao.getInformacoesFormacao().stream()
                        .map(InformacaoFormacaoMapper::toDTO)
                        .toList()
        );
        return formacaoDTO;
    }
}

