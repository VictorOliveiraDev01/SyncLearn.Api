package br.com.api.synclearn.Foruns.MongoEntities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@MongoEntity(collection = "comentarios")
public class Comentario extends PanacheMongoEntity {
    public ObjectId id;
    public ObjectId autor;
    public String texto;
    public Date dataCriacao;
    public List<Resposta> respostas;
}
