package clases;

public class Alumno {
	
	private int id;
	private String nombre_completo;
	private String apellido_p;
	private String apellido_m;
	private String curp;
	private String fecha_nacimiento;
	private String edad;
	private int id_grado;
	private String nombre_tutor;
	private String ocupacion_tutor;
	private String calle_tutor;
	private int numero_calle_tutor;
	private String colonia_tutor;
	private String cp_tutor;
	private String tel_tutor;
	private int estatus;
	
	public Alumno(int id,String nombre_completo, String apellido_p, String apellido_m, String curp, String fecha_nacimiento, String edad, int id_grado, String nombre_tutor, String ocupacion_tutor, String calle_tutor,
				int numero_calle_tutor, String colonia_tutor, String cp_tutor, String tel_tutor, int estatus){
		
		this.setId(id);
		this.setNombre_completo(nombre_completo);
		this.apellido_p = apellido_p;
		this.apellido_m = apellido_m;
		this.setCurp(curp);
		this.setFecha_nacimiento(fecha_nacimiento);
		this.setEdad(edad);
		this.setId_grado(id_grado);
		this.setNombre_tutor(nombre_tutor);
		this.setOcupacion_tutor(ocupacion_tutor);
		this.setCalle_tutor(calle_tutor);
		this.setNumero_calle_tutor(numero_calle_tutor);
		this.setColonia_tutor(colonia_tutor);
		this.setCp_tutor(cp_tutor);
		this.setTel_tutor(tel_tutor);
		this.setEstatus(estatus);
		
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


	public int getId_grado() {
		return id_grado;
	}


	public void setId_grado(int id_grado) {
		this.id_grado = id_grado;
	}


	public String getNombre_tutor() {
		return nombre_tutor;
	}


	public void setNombre_tutor(String nombre_tutor) {
		this.nombre_tutor = nombre_tutor;
	}


	public String getOcupacion_tutor() {
		return ocupacion_tutor;
	}


	public void setOcupacion_tutor(String ocupacion_tutor) {
		this.ocupacion_tutor = ocupacion_tutor;
	}


	public String getCalle_tutor() {
		return calle_tutor;
	}


	public void setCalle_tutor(String calle_tutor) {
		this.calle_tutor = calle_tutor;
	}


	public int getEstatus() {
		return estatus;
	}


	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}


	public int getNumero_calle_tutor() {
		return numero_calle_tutor;
	}


	public void setNumero_calle_tutor(int numero_calle_tutor) {
		this.numero_calle_tutor = numero_calle_tutor;
	}


	public String getColonia_tutor() {
		return colonia_tutor;
	}


	public void setColonia_tutor(String colonia_tutor) {
		this.colonia_tutor = colonia_tutor;
	}


	public String getCp_tutor() {
		return cp_tutor;
	}


	public void setCp_tutor(String cp_tutor) {
		this.cp_tutor = cp_tutor;
	}


	public String getTel_tutor() {
		return tel_tutor;
	}


	public void setTel_tutor(String tel_tutor) {
		this.tel_tutor = tel_tutor;
	}  
	
	public String toString(){
		return nombre_completo+" "+apellido_p+" "+apellido_m;
	}


	public String getApellido_p() {
		return apellido_p;
	}


	public void setApellido_p(String apellido_p) {
		this.apellido_p = apellido_p;
	}


	public String getApellido_m() {
		return apellido_m;
	}


	public void setApellido_m(String apellido_m) {
		this.apellido_m = apellido_m;
	}
	
}
