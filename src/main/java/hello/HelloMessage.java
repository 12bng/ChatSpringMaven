package hello;

public class HelloMessage {

	private String remitente;
	private String mensaje;

	public HelloMessage() {
	}

	public HelloMessage(String nombre, String mensaje) {
		this.mensaje = mensaje;
		this.remitente = nombre;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return remitente + ": " + mensaje;
	}
}