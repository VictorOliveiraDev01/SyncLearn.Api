package br.com.api.synclearn.Usuarios.Mappers;

import br.com.api.synclearn.Usuarios.DTO.AlunoDTO;
import br.com.api.synclearn.Usuarios.MongoEntities.Aluno;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlunoMapper {

    public static AlunoDTO toDTO(Aluno aluno) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.set_id(aluno.id != null ? aluno.id.toString() : null);
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setNomeUsuarioAluno(aluno.getNomeUsuarioAluno());
        alunoDTO.setEmail(aluno.getEmail());
        alunoDTO.setUniversidade(aluno.getUniversidade());
        alunoDTO.setCurso(aluno.getCurso());
        alunoDTO.setFotoPerfil(aluno.getFotoPerfil());
        return alunoDTO;
    }
}

