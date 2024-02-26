package br.com.api.synclearn.Cursos.MongoEntities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@MongoEntity(collection = "cursos")
public class Curso extends PanacheMongoEntity {
    private ObjectId _id;

    private ObjectId id_professor;

    @NotBlank(message = "O nome do curso é obrigatório")
    private String nome;

    @NotBlank(message = "A área do curso é obrigatória")
    private String area;

    private String descricao;

    private List<Capitulo> capitulos;


}
