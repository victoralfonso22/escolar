package clases;

public class HuellasComparar {
	
	private int id;
	private int idUsuario;	
	private byte[] huella;
	private int tipo_usuario;
	private String nombre_completo;
	
	public HuellasComparar(int id, int idUsuario, byte[] huella,int tipo_usuario, String nombre_completo){
		this.setId(id);
		this.setIdUsuario(idUsuario);
		this.setHuella(huella);
		this.tipo_usuario=tipo_usuario;
		this.nombre_completo = nombre_completo;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public byte[] getHuella() {
		return huella;
	}

	public void setHuella(byte[] huella) {
		this.huella = huella;
	}

	public int getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	
	}
