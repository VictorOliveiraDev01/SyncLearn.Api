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
@MongoEntity(collection = "capitulos")
public class Capitulo extends PanacheMongoEntity {
    public ObjectId _id;

    @NotBlank(message = "O nome do capítulo é obrigatório")
    public String nome;

    public List<Aula> aulas;

}