package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import javax.inject.Inject;

@Controller
public class GreetingController {

	@Inject
	private User user;

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {

		// Thread.sleep(1000); // simulated delay
		// String name = user.getNombre();
		String mensaje = HtmlUtils.htmlEscape(message.getMensaje());
		String remitente = HtmlUtils.htmlEscape(message.getRemitente());
		DBConector.getDBConector();
		DBConector.connect();
		DBConector.saveMensaje(mensaje, remitente);
		return new Greeting(remitente + ": " + mensaje);
	}

	@MessageMapping("/username")
	public void setUserName(User nombre) {
		user.setNombre(HtmlUtils.htmlEscape(nombre.getNombre()));
	}

	@SendTo("/topic/oldMessajes")
	@MessageMapping("/oldmessajes")
	public Iterable<HelloMessage> oldMessajes() {
		DBConector.getDBConector();
		DBConector.connect();
		Iterable<HelloMessage> messajes = DBConector.getAllMessages();
		return messajes;
	}

	@SendTo("/topic/greetings")
	public Greeting oldMessaje(String message, String name) throws Exception {
		return new Greeting(name + ": " + message);
	}

}