package br.com.api.synclearn.Foruns.MongoEntities;

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
@MongoEntity(collection = "respostas")
public class Resposta extends PanacheMongoEntity {

    public ObjectId autor;
    public TipoUsuario tipoUsuarioAutor;
    public String texto;
    public LocalDateTime dataCriacao;
    public RecursoAnexado recursoAnexado;
    public List<Resposta> respostas;

}
