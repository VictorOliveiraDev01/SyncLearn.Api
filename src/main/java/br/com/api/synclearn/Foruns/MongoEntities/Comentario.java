package br.com.api.synclearn.Foruns.MongoEntities;

import br.com.api.synclearn.Foruns.DTO.RespostaDTO;
import br.com.api.synclearn.Usuarios.Enums.TipoUsuario;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@MongoEntity(collection = "comentarios")
public class Comentario extends PanacheMongoEntity {
    public ObjectId autor;
    public String texto;
    public LocalDateTime dataCriacao;
    public List<Resposta> respostas;
    public RecursoAnexado recursoAnexado;
}
