package com.sk.ecommerce.email;


import com.sk.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sk.ecommerce.email.EmailTempletes.ORDER_CONFIRMATION;
import static com.sk.ecommerce.email.EmailTempletes.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_RELATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;


    @Async
    public void sentPaymentSucessEmail( String destinationEmail,
    String customerName,
    BigDecimal amount,
    String orderReference)throws MessagingException {


    MimeMessage mimeMessage=mailSender.createMimeMessage();
    MimeMessageHelper messageHelper=
            new MimeMessageHelper(mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name());

     messageHelper.setFrom("shashikumar8804068526@gmail.com");
     final String templateName= PAYMENT_CONFIRMATION.getTemplate();

        Map<String,Object> variables=new HashMap<>();
        variables.put("customerName",customerName);
        variables.put("amount",amount);
        variables.put("orderReference", orderReference);

        Context context=new Context();
        context.setVariables(variables);
        messageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());
        try {
            String htmlTemplet=templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplet,true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO -Email Sucessfully Send to %s with templet %s",destinationEmail,templateName));



        }catch (MessagingException e){
            log.warn("WARNING - Can't send email to {} ",destinationEmail);
        }

}



    @Async
    public void sentOrderConfirmationEmail( String destinationEmail,
                                        String customerName,
                                        BigDecimal amount,
                                        String orderReference,
                                        List<Product>  products
    )throws MessagingException {


        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=
                new MimeMessageHelper(mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name());

        messageHelper.setFrom("shashikumar8804068526@gmail.com");
        final String templateName= ORDER_CONFIRMATION.getTemplate();

        Map<String,Object> variables=new HashMap<>();
        variables.put("customerName",customerName);
        variables.put("Totalamount",amount);
        variables.put("orderReference", orderReference);
         variables.put("products",products);

        Context context=new Context();
        context.setVariables(variables);
        messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());
        try {
            String htmlTemplet=templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplet,true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO -Email Sucessfully Send to %s with templet %s",destinationEmail,templateName));



        }catch (MessagingException e){
            log.warn("WARNING - Can't send email to {} ",destinationEmail);
        }

    }

}
