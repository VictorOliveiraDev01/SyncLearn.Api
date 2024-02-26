package br.com.api.synclearn.Usuarios.DTO;

import lombok.Data;


@Data
public class ProfessorDTO {
    private String _id;
    private String nome;
    private String nomeUsuarioProfessor;
    private String email;
    private FormacaoDTO formacao;
    private String fotoPerfil;
}
