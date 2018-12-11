package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTO;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTOResponse;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTOSenha;
import br.com.academiadev.bumblebee.exception.ConflictException;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.mapper.UsuarioMapper;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.service.EmailService;
import br.com.academiadev.bumblebee.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
@Api(description = "Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Value("${email.enviar}")
    private String enviaEmail;

    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "Retorna uma usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public UsuarioDTOResponse buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário com id " + id + " não encontrado"));
        return usuarioMapper.toDTOResponse(usuario);
    }

    @ApiOperation(value = "Cria um Usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário criado com sucesso")
    })
    @PostMapping
    public UsuarioDTOResponse criar(@RequestBody @Valid UsuarioDTO usuarioDTO, HttpServletRequest request) {
        if (usuarioService.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new ConflictException("E-mail já cadastrado");
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        usuario.setConfirmToken(UUID.randomUUID().toString());

        if (this.enviaEmail.equals("true")) {
            SimpleMailMessage confirmEmail = new SimpleMailMessage();
            confirmEmail.setFrom("bumblebeepets@gmail.com");
            confirmEmail.setTo(usuario.getEmail());
            confirmEmail.setSubject("Confirmação de Cadastro");
            confirmEmail.setText("Seja bem-vindo ao Bumblebee Pets! Para confirmar seu cadastro, acesse o link a seguir:\n"
                    + request.getRequestURL() + "/confirmar?token=" + usuario.getConfirmToken());
            emailService.sendEmail(confirmEmail);
        }

        usuarioService.save(usuario);
        return usuarioMapper.toDTOResponse(usuario);
    }

    @ApiOperation(value = "Buscar todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuários encontrados com sucesso")
    })
    @GetMapping("/usuarios")
    public List<UsuarioDTOResponse> buscarTodos() {
        List<Usuario> listaUsuario = usuarioService.findAll();
        return usuarioMapper.toDTOResponse(listaUsuario);
    }

    @ApiOperation(value = "Deleta um Usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public UsuarioDTOResponse deletar(@PathVariable Long id) throws ObjectNotFoundException {
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário com id " + id + " não encontrado"));
        usuario.setExcluido(Boolean.TRUE);
        usuarioService.save(usuario);
        return usuarioMapper.toDTOResponse(usuario);
    }

    @ApiOperation(value = "Atualiza um Usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário atualizado com sucesso")
    })
    @PostMapping("/update")
    public UsuarioDTOResponse updateUsuario(@RequestBody @Valid UsuarioDTOResponse usuarioDTORequest) {
        Usuario usuarioSenha = usuarioService.findById(usuarioDTORequest.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Usuário com id " + usuarioDTORequest.getId() + " não encontrado"));

        Usuario usuario = usuarioMapper.toEntityUpdate(usuarioDTORequest, usuarioSenha.getSenha());
        usuarioService.saveAndFlush(usuario);
        return usuarioMapper.toDTOResponse(usuario);
    }

    @ApiOperation(value = "Envio de Email de Recuperação de Senha")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Email de recuperação de senha enviado com sucesso!")
    })
    @PostMapping("/senha/recuperar/{email}")
    public void enviarEmailRecuperarSenha(@PathVariable String email, HttpServletRequest request) throws UnknownHostException {
        Usuario user = usuarioService.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Usuário com id " + email + " não encontrado"));

        user.setResetToken(UUID.randomUUID().toString());

        user = usuarioService.save(user);


        if (enviaEmail.equals("true")) {
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("suporte@bumblebeepets.com");
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Recuperação de senha");
            passwordResetEmail.setText("Para recuperar sua senha, entre no link a seguir:\n" + request.getRequestURL()
                    + "/senha/recuperar?token=" + user.getResetToken());
            emailService.sendEmail(passwordResetEmail);
        }

    }

    @ApiOperation(value = "Recuperação de Senha")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Email de recuperação de senha enviado com sucesso!")
    })
    @GetMapping("/senha/recuperar")
    public UsuarioDTOSenha recuperarSenha(@RequestParam("token") String token) {
        Usuario usuario = usuarioService.findUserByResetToken(token).orElseThrow(() -> new ObjectNotFoundException("Token inválido"));
        return new UsuarioDTOSenha();
    }

    @ApiOperation(value = "Confirmação de cadastro")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cadastro confirmado com sucesso!")
    })
    @GetMapping("/confirmar")
    public void confirmarCadastro(@RequestParam("token") String token) {
        Usuario usuario = usuarioService.findUserByConfirmToken(token).orElseThrow(() -> new ObjectNotFoundException("Token inválido"));
        usuario.setEnable(true);
        usuario.setConfirmToken(null);
        usuarioService.save(usuario);
    }


    @ApiOperation(value = "Recuperação de Senha")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Email de recuperação de senha enviado com sucesso!")
    })
    @PostMapping("/senha/recuperar")
    public void recuperarSenha(@RequestParam("token") String token,
                               @RequestBody @Valid UsuarioDTOSenha usuarioDTOSenha) {
        Usuario usuario = usuarioService.findUserByResetToken(token).orElseThrow(() -> new ObjectNotFoundException("Token inválido"));
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioDTOSenha.getSenha()));
        usuario.setResetToken(null);
        usuarioService.save(usuario);
    }

}
