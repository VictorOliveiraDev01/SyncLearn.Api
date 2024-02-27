package br.com.api.synclearn.Foruns.DTO;


import lombok.Data;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ComentarioDTO {
    public ObjectId autor;
    public String texto;
    public LocalDateTime dataCriacao;
    public List<RespostaDTO> respostas;
}
