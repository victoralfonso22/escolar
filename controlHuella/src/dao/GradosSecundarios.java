package dao;

public class GradosSecundarios {
		
		private int id;
		private String secundario;
		private int idGrado;
		
		public GradosSecundarios (int id, String secundario, int idGrado){
			this.id = id;
			this.secundario= secundario;
			this.idGrado = idGrado;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

		public String getSecundario() {
			return secundario;
		}

		public void setSecundario(String secundario) {
			this.secundario = secundario;
		}

		public int getIdGrado() {
			return idGrado;
		}

		public void setIdGrado(int idGrado) {
			this.idGrado = idGrado;
		}
		
	}
