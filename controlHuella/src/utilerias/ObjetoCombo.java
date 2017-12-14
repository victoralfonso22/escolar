package utilerias;

public class ObjetoCombo 
{
  private String nombreDeEjemplo;
  private int codigo;

    public ObjetoCombo(int codigo, String nombreDeEjemplo)
      {
        this.codigo=codigo;
        this.nombreDeEjemplo=nombreDeEjemplo;
      }
    public String getNombreDeEjemplo() {
        return nombreDeEjemplo;
    }

    public void setNombreDeEjemplo(String nombreDeEjemplo) {
        this.nombreDeEjemplo = nombreDeEjemplo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String toString()
    {
     return nombreDeEjemplo;
    }
  }