package br.com.api.synclearn.Cursos.MongoEntities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
@EqualsAndHashCode(callSuper = true)
@Data
@MongoEntity(collection = "aulas")
public class Aula extends PanacheMongoEntity {
    public ObjectId _id;

    @NotBlank(message = "O título da aula é obrigatório")
    public String titulo;

    public String tipo;

    public String conteudo;

}
