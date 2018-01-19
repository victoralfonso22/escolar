package clases;

public class Huellas {
	
	private int id;
	private int idUsuario;	
	private byte[] izq_menique;
	private byte[] izq_anular;
	private byte[] izq_medio;
	private byte[] izq_indice;
	private byte[] izq_pulgar;
	private byte[] der_menique;
	private byte[] der_anular;
	private byte[] der_medio;
	private byte[] der_indice;
	private byte[] der_pulgar;
	private int tipo_usuario;	
	
	public Huellas(int id, int idUsuario, byte[] izq_menique, byte[] izq_anular, byte[] izq_medio,byte[] izq_indice, byte[] izq_pulgar, byte[] der_menique, byte[] der_anular,
			byte[] der_medio, byte[] der_indice, byte[] der_pulgar, int tipo_usuario){
		this.setId(id);
		this.setIdUsuario(idUsuario);
		this.setIzq_menique(izq_menique);
		this.setIzq_anular(izq_anular);
		this.setIzq_medio(izq_medio);
		this.setIzq_indice(izq_indice);
		this.setIzq_pulgar(izq_pulgar);
		this.setDer_menique(der_menique);
		this.setDer_anular(der_anular);
		this.setDer_medio(der_medio);
		this.setDer_indice(der_indice);
		this.setDer_pulgar(der_pulgar);
		this.setTipo_usuario(tipo_usuario);
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

	public byte[] getIzq_menique() {
		return izq_menique;
	}

	public void setIzq_menique(byte[] izq_menique) {
		this.izq_menique = izq_menique;
	}

	public byte[] getIzq_anular() {
		return izq_anular;
	}

	public void setIzq_anular(byte[] izq_anular) {
		this.izq_anular = izq_anular;
	}

	public byte[] getIzq_medio() {
		return izq_medio;
	}

	public void setIzq_medio(byte[] izq_medio) {
		this.izq_medio = izq_medio;
	}

	public byte[] getIzq_indice() {
		return izq_indice;
	}

	public void setIzq_indice(byte[] izq_indice) {
		this.izq_indice = izq_indice;
	}

	public byte[] getIzq_pulgar() {
		return izq_pulgar;
	}

	public void setIzq_pulgar(byte[] izq_pulgar) {
		this.izq_pulgar = izq_pulgar;
	}

	public byte[] getDer_menique() {
		return der_menique;
	}

	public void setDer_menique(byte[] der_menique) {
		this.der_menique = der_menique;
	}

	public byte[] getDer_anular() {
		return der_anular;
	}

	public void setDer_anular(byte[] der_anular) {
		this.der_anular = der_anular;
	}

	public byte[] getDer_medio() {
		return der_medio;
	}

	public void setDer_medio(byte[] der_medio) {
		this.der_medio = der_medio;
	}

	public byte[] getDer_indice() {
		return der_indice;
	}

	public void setDer_indice(byte[] der_indice) {
		this.der_indice = der_indice;
	}

	public byte[] getDer_pulgar() {
		return der_pulgar;
	}

	public void setDer_pulgar(byte[] der_pulgar) {
		this.der_pulgar = der_pulgar;
	}

	public int getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	
	}
