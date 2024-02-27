package br.com.api.synclearn.Foruns.MongoEntities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@MongoEntity(collection = "foruns")
public class Forum extends PanacheMongoEntity {
    public ObjectId autor;

    @NotBlank(message = "O tema do fórum é obrigatório")
    public String tema;

    @NotBlank(message = "A descrição do fórum é obrigatória")
    public String descricao;

    public LocalDateTime dataCriacao;

    public List<Comentario> comentarios;

}
