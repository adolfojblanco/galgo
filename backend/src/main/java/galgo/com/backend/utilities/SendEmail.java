package galgo.com.backend.utilities;

import galgo.com.backend.models.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class SendEmail {

    @Value("${spring.mail.username}")
    private String fromMail;

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Async
    public void sendMail(User user) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setFrom(fromMail);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setSubject("Email de bienvenida " + user.getFirstName());
        Context context = new Context();
        context.setVariable("content", user);
        String processedString = templateEngine.process("welcome", context);

        mimeMessageHelper.setText(processedString, true);

        mailSender.send(mimeMessage);
    }
}



