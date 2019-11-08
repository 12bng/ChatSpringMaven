package hello;

public class Messages {
	Iterable<HelloMessage> mensajes;
	String dueño;

	public Iterable<HelloMessage> getMensajes() {
		return mensajes;
	}

	public void setMensajes(Iterable<HelloMessage> mensajes) {
		this.mensajes = mensajes;
	}

	public String getDueño() {
		return dueño;
	}

	public void setDueño(String dueño) {
		this.dueño = dueño;
	}
}
