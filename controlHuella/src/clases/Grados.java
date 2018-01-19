package clases;

public class Grados {
	
	private int id;
	private String grado;
	
	public Grados (int id, String grado){
		this.id = id;
		this.grado= grado;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	
	public String toString(){
		return grado;
	}
}
