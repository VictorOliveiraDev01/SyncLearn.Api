package br.com.api.synclearn.Usuarios.MongoEntities;

import br.com.api.synclearn.Usuarios.MongoEntities.Formacao.Formacao;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.bson.types.ObjectId;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@Data
@MongoEntity(collection = "professores")
public class Professor extends PanacheMongoEntity {

    @NotBlank(message = "O nome é obrigatório")
    public String nome;

    @NotBlank(message = "O nome de usuário é obrigatório")
    private String nomeUsuarioProfessor;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "A senha  é obrigatória")
    private String senha;

    public Formacao formacao;

    public String fotoPerfil;

}

