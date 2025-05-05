package br.eti.ljr.sn.clientesrv.business;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class EmailBss implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/mail/sncontrole")
	private Session session;

	public void send(String to, String subject, String html) {
		try {
			MimeMessage mensagem = new MimeMessage(session);
			mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			mensagem.setSubject(subject, "UTF-8");
			mensagem.setContent(html, "text/html; charset=UTF-8");

			Transport.send(mensagem);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
