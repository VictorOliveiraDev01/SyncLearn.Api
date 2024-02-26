package br.com.api.synclearn.Usuarios.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.bson.types.ObjectId;


@Data
public class AlunoDTO {
    private String _id;
    private String nome;
    private String nomeUsuarioAluno;
    private String email;
    private String universidade;
    private String curso;
    private String fotoPerfil;
}
