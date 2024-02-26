package br.com.api.synclearn.Usuarios.MongoEntities.Formacao;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@MongoEntity(collection = "informacoes_formacao")
public class InformacaoFormacao extends PanacheMongoEntity {

    @NotBlank(message = "O nome da instituição é obrigatório")
    private String universidade;

    @NotBlank(message = "O nome do curso é obrigatório")
    private String curso;

    @NotNull(message = "O ano de início é obrigatório")
    private Integer anoInicio;

    @NotNull(message = "O ano de conclusão é obrigatório")
    private Integer anoConclusao;
}
