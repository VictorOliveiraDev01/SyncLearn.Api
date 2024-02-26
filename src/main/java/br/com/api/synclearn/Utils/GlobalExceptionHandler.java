package br.com.api.synclearn.Utils;

import io.quarkus.security.UnauthorizedException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        if (e instanceof WebApplicationException webAppException) {
            return buildResponse(webAppException.getResponse().getStatus(), webAppException.getMessage());
        } else if (e instanceof IllegalArgumentException) {
            return buildResponse(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage());
        } else if (e instanceof ConstraintViolationException) {
            Map<String, String> errors = new HashMap<>();
            ((ConstraintViolationException) e).getConstraintViolations().forEach(violation -> {
                String fieldName = violation.getPropertyPath().toString();
                String errorMessage = violation.getMessage();
                errors.put(fieldName, errorMessage);
            });
            return buildResponse(Response.Status.fromStatusCode(Response.Status.BAD_REQUEST.getStatusCode()), "Erro de validação", errors);
        } else if (e instanceof EntityNotFoundException) {
            return buildResponse(Response.Status.NOT_FOUND.getStatusCode(), "Recurso não encontrado. Recurso: " + e.getMessage());
        } else if (e instanceof UnauthorizedException) {
            return buildResponse(Response.Status.UNAUTHORIZED.getStatusCode(), "Usuário sem permissão. Causa: " + e.getMessage());
        } else {
            return buildResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Ocorreu um erro em nossos serviços. Causa: " + e.getMessage());
        }
    }

    private Response buildResponse(int status, String message) {
        return Response.status(status).entity(new ResponseApi<>(message)).build();
    }

    private Response buildResponse(Response.Status status, String message, Map<String, String> errors) {
        return Response.status(status).entity(new ResponseApi<>(status, message, errors)).build();
    }
}
