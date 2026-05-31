
public class categoria_producto {

	
	private int id;
    private String nombre;

    public categoria_producto(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nombre; 
    }
}
