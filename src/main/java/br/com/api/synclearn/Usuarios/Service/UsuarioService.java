package br.com.api.synclearn.Usuarios.Service;

import br.com.api.synclearn.AWS.AmazonS3Service;
import br.com.api.synclearn.Usuarios.DTO.AlunoDTO;
import br.com.api.synclearn.Usuarios.DTO.ProfessorDTO;
import br.com.api.synclearn.Usuarios.Enums.TipoUsuario;
import br.com.api.synclearn.Usuarios.Mappers.AlunoMapper;
import br.com.api.synclearn.Usuarios.Mappers.ProfessorMapper;
import br.com.api.synclearn.Usuarios.MongoEntities.Aluno;
import br.com.api.synclearn.Usuarios.MongoEntities.Formacao.Formacao;
import br.com.api.synclearn.Usuarios.MongoEntities.Professor;
import br.com.api.synclearn.Usuarios.MongoRepositories.AlunoRepository;
import br.com.api.synclearn.Usuarios.MongoRepositories.ProfessorRepository;
import br.com.api.synclearn.Utils.MongoEntityUtils;
import br.com.api.synclearn.Utils.NullOrEmpty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mindrot.jbcrypt.BCrypt;


import java.io.InputStream;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoMapper alunoMapper;
    private final ProfessorMapper professorMapper;
    private final MongoEntityUtils mongoEntityUtils;

   // private final AmazonS3Service amazonS3Service;

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Inject
    public UsuarioService(AlunoRepository alunoRepository,
                          ProfessorRepository professorRepository,
                          AlunoMapper alunoMapper,
                          ProfessorMapper professorMapper,
                          MongoEntityUtils mongoEntityUtils,
                          AmazonS3Service amazonS3Service){
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.alunoMapper = alunoMapper;
        this.professorMapper = professorMapper;
        this.mongoEntityUtils = mongoEntityUtils;
        //this.amazonS3Service = amazonS3Service;
    }




    public List<AlunoDTO> findAllAlunos(){
        log.info("Recuperando todos os usuários alunos.");
        return alunoRepository.findAll()
                .stream()
                .map(AlunoMapper::toDTO)
                .toList();
    }

    public List<ProfessorDTO> findAllProfessores(){
        log.info("Recuperando todos os usuários professores.");
        return professorRepository.findAll()
                .stream()
                .map(ProfessorMapper::toDTO)
                .toList();
    }

    public AlunoDTO findAlunoById(String id){
        if(id.isBlank()){
            throw new IllegalArgumentException("Id do aluno não fornecido");
        }

        Aluno aluno = (Aluno) mongoEntityUtils.findUsuarioById(id, Aluno.class);

        log.info("Recuperando usuário aluno com ID: {}", id);

        return AlunoMapper.toDTO(aluno);

    }

    public ProfessorDTO findProfessorById(String id){
        if(id.isBlank()){
            throw new IllegalArgumentException("Id do professor não fornecido");
        }

        Professor professor = (Professor) mongoEntityUtils.findUsuarioById(id, Professor.class);

        log.info("Recuperando usuário professor com ID: {}", id);

        return ProfessorMapper.toDTO(professor);

    }


    public Professor addNewProfessor(@Valid Professor professor) throws Exception{
        log.info("Adicionando novo usuário professor: {}", professor);

        checkEmailUsuario(professor);

        String hashPass = BCrypt.hashpw(professor.getSenha(), BCrypt.gensalt());
        professor.setSenha(hashPass);

        professorRepository.persist(professor);

        return professor;

    }


    public Aluno addNewAluno(@Valid Aluno aluno) throws Exception{
        log.info("Adicionando novo usuário aluno: {}", aluno);

        checkEmailUsuario(aluno);

        String hashPass = BCrypt.hashpw(aluno.getSenha(), BCrypt.gensalt());
        aluno.setSenha(hashPass);

        alunoRepository.persist(aluno);

        return aluno;

    }

    public Professor updateProfessor(String id, ProfessorDTO professorDTO)throws Exception{
        Professor professorExistente = (Professor) mongoEntityUtils.findUsuarioById(id, Professor.class);

        checkEmailUsuario(professorDTO);

        professorExistente.setNome(professorDTO.getNome());
        professorExistente.setEmail(professorDTO.getEmail());
        professorExistente.setNomeUsuarioProfessor(professorDTO.getNomeUsuarioProfessor());

        professorRepository.update(professorExistente);

        return professorExistente;
    }



    public Professor updateFormacaoProfessor(String id, @Valid Formacao formacaoAtualizada) {
        if(id == null){
            throw new IllegalArgumentException("Id de usuário nçao fornecido");
        }
        Professor professorExistente = (Professor) mongoEntityUtils.findUsuarioById(id, Professor.class);

        professorExistente.setFormacao(formacaoAtualizada);
        professorRepository.update(professorExistente);

        return professorExistente;
    }



    public Aluno updateAluno(String id, AlunoDTO alunoDTO)throws Exception{
        Aluno alunoExistente = (Aluno) mongoEntityUtils.findUsuarioById(id, Aluno.class);

        checkEmailUsuario(alunoDTO);

        alunoExistente.setNome(alunoDTO.getNome());
        alunoExistente.setEmail(alunoDTO.getEmail());
        alunoExistente.setNomeUsuarioAluno(alunoDTO.getNomeUsuarioAluno());
        alunoExistente.setUniversidade(alunoDTO.getUniversidade());
        alunoExistente.setCurso(alunoDTO.getCurso());

        alunoRepository.update(alunoExistente);

        return alunoExistente;
    }

    public void updateSenhaUsuario(String id, TipoUsuario tipoUsuario, String newPass){
        if (id.isBlank() || newPass.isBlank()){
            throw new IllegalArgumentException("Dados não fornecidos. Dados solicitados: id do usuário, nova senha");
        }


        switch (tipoUsuario){
            case aluno:
                Aluno aluno = (Aluno) mongoEntityUtils.findUsuarioById(id, Aluno.class);

                String alunoHashPass = BCrypt.hashpw(newPass, BCrypt.gensalt());

                aluno.setSenha(alunoHashPass);

                alunoRepository.update(aluno);

                break;

            case professor:
                Professor professor = (Professor) mongoEntityUtils.findUsuarioById(id, Professor.class);

                String professorHashPass = BCrypt.hashpw(newPass, BCrypt.gensalt());

                professor.setSenha(professorHashPass);

                professorRepository.update(professor);

                break;

            default:
                throw new IllegalArgumentException("Tipo de usuário inválido");

        }

    }

    public void deleteUsuarioById(String id,TipoUsuario tipoUsuario){
        if(NullOrEmpty.isNullOrEmpty(id)
                || NullOrEmpty.isNullOrEmpty(tipoUsuario)){
            throw new IllegalArgumentException("Recursos não infoamdos. Recursos solicitados: Id do usuário, Tipo do usuário");
        }

        switch (tipoUsuario){
            case aluno:
                mongoEntityUtils.findUsuarioById(id, Aluno.class);

                alunoRepository.deleteById(new ObjectId(id));

                break;

            case professor:
                mongoEntityUtils.findUsuarioById(id, Professor.class);

                professorRepository.deleteById(new ObjectId(id));

                break;

            default:
                throw new IllegalArgumentException("Tipo de usuário inválido");
        }
    }


 /*   @Transactional
    public void uploadFotoPerfilUsuario(String nomeArquivoFoto, InputStream arquivo, TipoUsuario tipoUsuario, String idUsuario) {
        if (NullOrEmpty.isNullOrEmpty(nomeArquivoFoto)
                || NullOrEmpty.isNullOrEmpty(idUsuario)
                || NullOrEmpty.isNullOrEmpty(tipoUsuario)
                || NullOrEmpty.isNullOrEmpty(arquivo)) {
            throw new IllegalArgumentException("Recursos não informados.");
        }

        switch (tipoUsuario) {
            case aluno:
                Aluno aluno = (Aluno) mongoEntityUtils.findUsuarioById(idUsuario, Aluno.class);

                String caminhoFotoPerfilAluno = amazonS3Service.uploadArquivoS3(nomeArquivoFoto, arquivo);

                aluno.setFotoPerfil(caminhoFotoPerfilAluno);

                alunoRepository.persist(aluno);

                break;

            case professor:
                Professor professor = (Professor) mongoEntityUtils.findUsuarioById(idUsuario, Professor.class);

                String caminhoFotoPerfilProfessor = amazonS3Service.uploadArquivoS3(nomeArquivoFoto, arquivo);

                professor.setFotoPerfil(caminhoFotoPerfilProfessor);

                professorRepository.persist(professor);

                break;

            default:
                throw new IllegalArgumentException("Tipo de usuário inválido.");
        }
    }

  */



    public void checkEmailUsuario(Object user)throws Exception{
        if(user == null){
            throw new IllegalArgumentException("Usuário não informado");
        }
        if(user instanceof AlunoDTO){
            Aluno checkEmailAluno = alunoRepository.findByEmail(((AlunoDTO) user).getEmail());
            if(checkEmailAluno != null){
                throw new Exception("Já existe um usuário cadastrado com este e-mail");
            }
        }else if(user instanceof ProfessorDTO){
            Professor checkEmailProfessor = professorRepository.findByEmail(((ProfessorDTO) user).getEmail());
            if(checkEmailProfessor != null){
                throw new Exception("Já existe um usuário cadastrado com este e-mail");
            }
        }
    }








}
