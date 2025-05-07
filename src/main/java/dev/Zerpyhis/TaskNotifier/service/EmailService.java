package dev.Zerpyhis.TaskNotifier.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender sender;

    public void senderEmail(String para,String assunto,String title, String dataTarefa , String horaTarefa) throws MessagingException{
        MimeMessage mensagem=sender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(mensagem,true,"UTGF-8");

        helper.setTo(para);
        helper.setSubject(assunto);
        helper.setText(genereteHtml(title,dataTarefa,horaTarefa),true);

        sender.send(mensagem);
    }

    private String genereteHtml(String titulo,String data,String hora){
        return """
                    <html>
                                <body style="font-family: Arial, sans-serif; color: #333;">
                                    <div style="max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px;">
                                        <h2 style="color: #2E86C1;">🔔 Notificação de Tarefa</h2>
                                        <p>Olá,</p>
                                        <p>Você tem uma nova tarefa agendada:</p>
                                        <table style="width: 100%; border-collapse: collapse;">
                                            <tr>
                                                <td style="padding: 8px;"><strong>Título:</strong></td>
                                                <td style="padding: 8px;">%s</td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;"><strong>Data:</strong></td>
                                                <td style="padding: 8px;">%s</td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;"><strong>Horário:</strong></td>
                                                <td style="padding: 8px;">%s</td>
                                            </tr>
                                        </table>
                                        <p>Não se esqueça de comparecer! ✅</p>
                                        <br>
                                        <p style="font-size: 12px; color: #888;">Este é um e-mail automático. Não responda.</p>
                                    </div>
                                </body>
                            </html>
                        """.formatted(titulo, data, hora);
    }
}
