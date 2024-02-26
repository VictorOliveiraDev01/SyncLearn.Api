package br.com.api.synclearn.Foruns.MongoEntities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
@EqualsAndHashCode(callSuper = true)
@Data
@MongoEntity(collection = "recursos_anexados")
public class RecursoAnexado extends PanacheMongoEntity {
    public ObjectId id;
    public ObjectId comentarioId; // Referência ao identificador do comentário
    public ObjectId respostaId; // Referência ao identificador da resposta (caso esteja anexado a uma resposta)
    public String tipo; // Tipo do recurso (por exemplo: PDF, imagem, etc.)
    public String nome; // Nome do recurso
    public String url;
}
