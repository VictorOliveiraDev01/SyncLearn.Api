package br.com.api.synclearn.Usuarios.MongoEntities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

@EqualsAndHashCode(callSuper = true)
@Data
@MongoEntity(collection = "alunos")
public class Aluno extends PanacheMongoEntity {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O nome de usuário é obrigatório")
    private String nomeUsuarioAluno;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @NotBlank(message = "O nome da instituição de ensino é obrigatório")
    private String universidade;

    @NotBlank(message = "O curso é obrigatório")
    private String curso;

    private String fotoPerfil;
}
