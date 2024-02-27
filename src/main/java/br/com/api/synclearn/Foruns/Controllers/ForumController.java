package br.com.api.synclearn.Foruns.Controllers;

import br.com.api.synclearn.Foruns.MongoEntities.Comentario;
import br.com.api.synclearn.Foruns.MongoEntities.Forum;
import br.com.api.synclearn.Foruns.MongoEntities.Resposta;
import br.com.api.synclearn.Foruns.Service.ForumService;
import br.com.api.synclearn.Utils.ResponseApi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/foruns")
public class ForumController {

    private final ForumService forumService;
    @Inject
    public ForumController(ForumService forumService){
        this.forumService = forumService;
    }
    @GET
    public Response findAllForuns(){
        return Response.ok(new ResponseApi<>(forumService.findAllForuns()))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findForumById(@PathParam("id") String id){
        return Response.ok(new ResponseApi<>(forumService.findForumById(id)))
                .build();
    }

    @GET
    @Path("/usuarios/{id}")
    public Response findForunsByUser(@PathParam("id") String id){
        return Response.ok(new ResponseApi<>(forumService.findForunsByUser(id)))
                .build();
    }

    @POST
    public Response addNewForum(@Valid Forum forum){
        return Response.ok(new ResponseApi<>(forumService.addNewForum(forum)))
                .build();
    }

    @PUT
    @Path("/atualizar-forum/{id}")
    public Response updateForum(@PathParam("id") String forumId, @Valid Forum forum){
        return Response.ok(new ResponseApi<>(forumService.updateForum(forumId, forum)))
                .build();
    }

    @POST
    @Path("/adiciona-comentario/{forumId}")
    public Response addComentarioForum(@PathParam("id") String forumId, Comentario comentario){
        try {
            forumService.addComentarioForum(forumId, comentario);
            return Response.ok()
                    .entity("Comentário adicionado com sucesso")
                    .build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/adiciona-resposta-comentario/{forumId}/{comentarioId}")
    public Response addRespostaComentario(@PathParam("id") String forumId, @PathParam("comentarioId") String comentarioId, Resposta resposta){
        try {
            forumService.addRespostaComentario(forumId, comentarioId, resposta);
            return Response.ok()
                    .entity("Resposta de comentario adicionada com sucesso")
                    .build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }


}
