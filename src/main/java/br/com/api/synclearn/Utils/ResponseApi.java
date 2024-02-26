package br.com.api.synclearn.Utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.core.Response.Status;
import lombok.Data;

/**
 * Classe que representa a resposta padrão para as operações da API.
 *
 * @author victor.marcelo
 *
 * @param <T> Tipo de dado associado à resposta.
 */
@Data
public class ResponseApi<T> {

    private T data;
    private int status;
    private String message;
    private final List<String> errors = new ArrayList<>();
    private final Map<String, String> validationErrors = new HashMap<>();

    public ResponseApi(T data) {
        this.data = data;
        this.status = Status.OK.getStatusCode();
        this.message = Status.OK.getReasonPhrase();
    }

    public ResponseApi(Status status, String message) {
        this.status = status.getStatusCode();
        this.message = message;
    }

    public ResponseApi(Status status, String message, String error) {
        this.status = status.getStatusCode();
        this.message = message;
        this.errors.add(error);
    }

    public ResponseApi(Status status, String message, List<String> errors) {
        this.status = status.getStatusCode();
        this.message = message;
        this.errors.addAll(errors);
    }

    public ResponseApi(Status status, String message, Map<String, String> validationErrors) {
        this.status = status.getStatusCode();
        this.message = message;
        this.validationErrors.putAll(validationErrors);
    }

}


