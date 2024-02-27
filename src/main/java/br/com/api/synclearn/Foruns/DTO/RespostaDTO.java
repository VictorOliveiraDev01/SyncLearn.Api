package br.com.api.synclearn.Foruns.DTO;

import br.com.api.synclearn.Foruns.MongoEntities.Resposta;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RespostaDTO {
    public ObjectId autor;
    public String texto;
    public LocalDateTime dataCriacao;
    public RecursoAnexadoDTO recursoAnexado;
    public List<RespostaDTO> respostas;
}
