package br.com.api.synclearn.Usuarios.Controllers;

import br.com.api.synclearn.Usuarios.DTO.AlunoDTO;
import br.com.api.synclearn.Usuarios.DTO.ProfessorDTO;
import br.com.api.synclearn.Usuarios.Enums.TipoUsuario;
import br.com.api.synclearn.Usuarios.MongoEntities.Aluno;
import br.com.api.synclearn.Usuarios.MongoEntities.Formacao.Formacao;
import br.com.api.synclearn.Usuarios.MongoEntities.Professor;
import br.com.api.synclearn.Usuarios.Service.UsuarioService;
import br.com.api.synclearn.Utils.ResponseApi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path(value = "/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    private final UsuarioService usuarioService;
    @Inject
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GET
    @Path("/alunos")
    public Response findAllAlunos(){
        return Response.ok(new ResponseApi<>(usuarioService.findAllAlunos()))
                .build();
    }

    @GET
    @Path("/professores")
    public Response findAllProfessores(){
        return Response.ok(new ResponseApi<>(usuarioService.findAllProfessores()))
                .build();
    }

    @GET
    @Path("/alunos/{id}")
    public Response findAlunoById(@PathParam("id") String id){
        return Response.ok(new ResponseApi<>(usuarioService.findAlunoById(id)))
                .build();
    }

    @GET
    @Path("/professores/{id}")
    public Response findProfessorById(@PathParam("id") String id){
        return Response.ok(new ResponseApi<>(usuarioService.findProfessorById(id)))
                .build();
    }

    @POST
    @Path("/alunos")
    public Response addNewAluno(@Valid Aluno aluno) throws Exception {
        return Response.ok(new ResponseApi<>(usuarioService.addNewAluno(aluno)))
                .build();
    }

    @POST
    @Path("/professores")
    public Response addNewProfessor(@Valid Professor professor) throws Exception {
        return Response.ok(new ResponseApi<>(usuarioService.addNewProfessor(professor)))
                .build();
    }

    @PUT
    @Path("/alunos/{id}")
    public Response updateAluno(@PathParam("id") String id, AlunoDTO alunoDTO) throws Exception{
        return Response.ok(new ResponseApi<>(usuarioService.updateAluno(id, alunoDTO)))
                .build();
    }

    @PUT
    @Path("/professores/{id}")
    public Response updateProfessor(@PathParam("id") String id, ProfessorDTO professorDTO) throws Exception{
        return Response.ok(new ResponseApi<>(usuarioService.updateProfessor(id, professorDTO)))
                .build();
    }

    @PUT
    @Path("/professores/atualizar-formacao/{id}")
    public Response updateFormacaoProfessor(@PathParam("id") String id, @Valid Formacao formacaoAtualizada){
        return Response.ok(new ResponseApi<>(usuarioService.updateFormacaoProfessor(id, formacaoAtualizada)))
                .build();
    }


    @PUT
    @Path("/alterar-senha/{tipoUsuario}/{id}/{novaSenha}")
    public Response updateSenhaUsuario(@PathParam("tipoUsuario") TipoUsuario tipoUsuario,
                                       @PathParam("id") String id,
                                       @PathParam("novaSenha") String novaSenha){
        try {
            usuarioService.updateSenhaUsuario(id, tipoUsuario, novaSenha);
            return Response.ok()
                    .entity("Senha alterada com sucesso")
                    .build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }

    }


    @DELETE
    @Path("/deletar-usuario/{tipoUsuario}/{id}")
    public Response deleteUsuarioById(@PathParam("tipoUsuario") TipoUsuario tipoUsuario, @PathParam("id") String id){
        try {
            usuarioService.deleteUsuarioById(id, tipoUsuario);
            return Response.ok()
                    .entity("Usuário excluído com sucesso")
                    .build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
