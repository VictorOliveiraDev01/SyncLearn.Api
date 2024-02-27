package br.com.api.synclearn.Foruns.Mappers;

import br.com.api.synclearn.Foruns.DTO.RecursoAnexadoDTO;
import br.com.api.synclearn.Foruns.MongoEntities.RecursoAnexado;

public class RecursoAnexadoMapper {
    public static RecursoAnexadoDTO toDTO(RecursoAnexado recursoAnexado) {
        RecursoAnexadoDTO recursoAnexadoDTO = new RecursoAnexadoDTO();
        recursoAnexadoDTO.setTipo(recursoAnexado.getTipo());
        recursoAnexadoDTO.setNome(recursoAnexado.getNome());
        recursoAnexadoDTO.setUrl(recursoAnexado.getUrl());
        return recursoAnexadoDTO;
    }
}
