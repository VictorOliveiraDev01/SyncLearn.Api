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
    public String tipo;
    public String nome;
    public String url;
}
