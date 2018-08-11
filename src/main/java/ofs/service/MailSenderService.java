package ofs.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class MailSenderService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	public void processMailNotification() {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			Context context = new Context();

			Map<String, Object> map = new HashMap<>();
			context.setVariables(map);
			String html = templateEngine.process("welcom-vivek.html", context);
			helper.addTo("vivekkumar7442.k@gmail.com");

			helper.setText(html, true);
			helper.setSubject("Hi vivek");
			helper.setFrom("varshinipriya73@gmail.com");
			File[] files = new File("C:\\Optimus-Docs").listFiles();
			for (File file : files) {
				helper.addAttachment(file.getName(), file);
			}
			emailSender.send(message);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
