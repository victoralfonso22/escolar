package clases;

public class Personal {
	
	private int id;
	private String nombre_completo;
	private String curp;
	private String fecha_nacimiento;
	private String edad;
	
	public Personal(int id, String nombre_completo,String curp,String fecha_nacimiento,String edad){
		this.id = id;
		this.nombre_completo = nombre_completo;
		this.curp = curp;
		this.fecha_nacimiento = fecha_nacimiento;
		this.edad = edad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}
	
	public String toString(){
		return nombre_completo;
	}

}
