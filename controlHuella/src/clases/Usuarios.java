package clases;



public class Usuarios {
	private int id;
	private String usuario;
	private String pass;
	private int estatus;
	
	public Usuarios(int id, String usuario, String pass, int estatus){
		this.setId(id);
		this.setUsuario(usuario);
		this.setPass(pass);
		this.setEstatus(estatus);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
}
