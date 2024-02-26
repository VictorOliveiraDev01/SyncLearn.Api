package br.com.api.synclearn.Usuarios.Mappers;

import br.com.api.synclearn.Usuarios.DTO.ProfessorDTO;
import br.com.api.synclearn.Usuarios.MongoEntities.Professor;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfessorMapper {

    public static ProfessorDTO toDTO(Professor professor) {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.set_id(professor.id != null ? professor.id.toString() : null);
        professorDTO.setNome(professor.getNome());
        professorDTO.setNomeUsuarioProfessor(professor.getNomeUsuarioProfessor());
        professorDTO.setEmail(professor.getEmail());
        professorDTO.setFormacao(FormacaoMapper.toDTO(professor.getFormacao()));
        professorDTO.setFotoPerfil(professor.getFotoPerfil());
        return professorDTO;
    }
}

