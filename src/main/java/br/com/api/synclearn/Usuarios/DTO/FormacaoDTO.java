package br.com.api.synclearn.Usuarios.DTO;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
public class FormacaoDTO {

    private List<InformacaoFormacaoDTO> informacoesFormacao = new ArrayList<>();

}
