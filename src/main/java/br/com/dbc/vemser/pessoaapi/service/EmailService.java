package br.com.dbc.vemser.pessoaapi.service;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final freemarker.template.Configuration fmConfiguration;
    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;
    public void sendEmailPessoaCadastrada(PessoaDTO pessoa){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(pessoa.getEmail());
        message.setSubject("Cadastro");
        message.setText("Olá, "+pessoa.getNome()+
                "\nEstamos felizes em ter você em nosso sistema!\n"+
                "Seus cadastro foi realizado com sucesso, sei id é: "+pessoa.getIdPessoa()+
                "\nQualquer dúvida nos contate pelo email "+from);

        emailSender.send(message);
    }

    public void emailCadastroEndereco(EnderecoDTO enderecoDTO, PessoaDTO pessoaDTO) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(pessoaDTO.getEmail());
            mimeMessageHelper.setSubject("Cadastro de endereço");
            mimeMessageHelper.setText(geContentFromTemplateCadastro(enderecoDTO,pessoaDTO), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | RegraDeNegocioException | TemplateException e ) {
            e.printStackTrace();
        }

    }
    public void emailAtualizacaoEndereco(EnderecoDTO enderecoDTO, PessoaDTO pessoaDTO) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(pessoaDTO.getEmail());
            mimeMessageHelper.setSubject("Atualização de endereço");
            mimeMessageHelper.setText(geContentFromTemplateAtualizar(enderecoDTO,pessoaDTO), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | RegraDeNegocioException | TemplateException e ) {
            e.printStackTrace();
        }

    }
    public void emailDeleteEndereco(EnderecoDTO enderecoDTO, PessoaDTO pessoaDTO) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(pessoaDTO.getEmail());
            mimeMessageHelper.setSubject("Endereço deletado!");
            mimeMessageHelper.setText(geContentFromTemplateDeletar(enderecoDTO), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | RegraDeNegocioException | TemplateException e ) {
            e.printStackTrace();
        }

    }

    public String geContentFromTemplateCadastro(EnderecoDTO enderecoDTO, PessoaDTO pessoaDTO) throws IOException, TemplateException, RegraDeNegocioException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome",pessoaDTO.getNome());
        dados.put("email", pessoaDTO.getEmail());
        dados.put("tipo", enderecoDTO.getTipo());
        dados.put("logradouro", enderecoDTO.getLogradouro());
        dados.put("numero", enderecoDTO.getNumero());
        dados.put("complemento", enderecoDTO.getComplemento());
        dados.put("cep", enderecoDTO.getCep());
        dados.put("cidade", enderecoDTO.getCidade());
        dados.put("estado", enderecoDTO.getEstado());
        dados.put("pais", enderecoDTO.getPais());

        Template template = fmConfiguration.getTemplate("email-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
    public String geContentFromTemplateAtualizar(EnderecoDTO enderecoDTO, PessoaDTO pessoaDTO) throws IOException, TemplateException, RegraDeNegocioException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome",pessoaDTO.getNome());
        dados.put("email", pessoaDTO.getEmail());
        dados.put("tipo", enderecoDTO.getTipo());
        dados.put("logradouro", enderecoDTO.getLogradouro());
        dados.put("numero", enderecoDTO.getNumero());
        dados.put("complemento", enderecoDTO.getComplemento());
        dados.put("cep", enderecoDTO.getCep());
        dados.put("cidade", enderecoDTO.getCidade());
        dados.put("estado", enderecoDTO.getEstado());
        dados.put("pais", enderecoDTO.getPais());

        Template template = fmConfiguration.getTemplate("email-template-update.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
    public String geContentFromTemplateDeletar(EnderecoDTO enderecoDTO) throws IOException, TemplateException, RegraDeNegocioException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("tipo", enderecoDTO.getTipo());
        dados.put("logradouro", enderecoDTO.getLogradouro());
        dados.put("numero", enderecoDTO.getNumero());
        dados.put("complemento", enderecoDTO.getComplemento());
        dados.put("cep", enderecoDTO.getCep());
        dados.put("cidade", enderecoDTO.getCidade());
        dados.put("estado", enderecoDTO.getEstado());
        dados.put("pais", enderecoDTO.getPais());

        Template template = fmConfiguration.getTemplate("email-template-delete.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
}
