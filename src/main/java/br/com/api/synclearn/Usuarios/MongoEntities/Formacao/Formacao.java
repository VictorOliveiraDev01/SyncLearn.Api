package br.com.api.synclearn.Usuarios.MongoEntities.Formacao;


import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@MongoEntity(collection = "formacao_professores")
public class Formacao extends PanacheMongoEntity {
    @Valid
    private List<InformacaoFormacao> informacoesFormacao = new ArrayList<>();
}
