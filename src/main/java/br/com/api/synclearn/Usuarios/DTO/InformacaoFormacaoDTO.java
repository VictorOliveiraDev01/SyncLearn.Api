package br.com.api.synclearn.Usuarios.DTO;

import lombok.Data;

@Data
public class InformacaoFormacaoDTO {
    private String universidade;

    private String curso;

    private Integer anoInicio;

    private Integer anoConclusao;
}
