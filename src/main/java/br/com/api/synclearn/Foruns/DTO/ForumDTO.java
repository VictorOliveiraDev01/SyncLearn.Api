package br.com.api.synclearn.Foruns.DTO;


import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;




@Data
public class ForumDTO {
    private ObjectId autor;
    private String tema;
    private String descricao;
    private LocalDateTime dataCriacao;
    private List<ComentarioDTO> comentarios;

}

