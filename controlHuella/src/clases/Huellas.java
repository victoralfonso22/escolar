package clases;

public class Huellas {
	
	private int UsersInFmd;
	private int SucursalId;
	private int UserId;
	private byte[] FMD;
	private int FingerIndex;
	private String nombre;
	
	public Huellas(int id, int SucursalId, int UserId, byte[] FMD, int FingerIndex, String nombre){
		this.UsersInFmd = id;
		this.SucursalId = SucursalId;
		this.UserId = UserId;
		this.FMD =  FMD;
		this.FingerIndex = FingerIndex;
		this.setNombre(nombre);
	}
	
	
	public void setId(int id) {
		this.UsersInFmd = id;
	}
	
	public int getUsersInFmd() {
		return UsersInFmd;
	}
	
	public int getSucursalId() {
		return SucursalId;
	}
	public void setSucursalId(int sucursalId) {
		SucursalId = sucursalId;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getFingerIndex() {
		return FingerIndex;
	}
	public void setFingerIndex(int fingerIndex) {
		FingerIndex = fingerIndex;
	}
	public byte[] getFMD() {
		return FMD;
	}
	public void setFMD(byte[] fMD) {
		FMD = fMD;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
