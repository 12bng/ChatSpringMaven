package hello;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

@Component
@SessionAttributes("user")
public class User {
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public User(String nombre) {
		this.nombre = nombre;
	}

	public User() {
	}

}
