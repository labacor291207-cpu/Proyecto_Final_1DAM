public class validaciones extends Exception {
	
		
		//validaciones de solicitud_compra
	
	    public static class FechaVaciaException extends Exception {
	        public FechaVaciaException() { super("Debe seleccionar una FECHA."); }
	    }

	    public static class ProductoSoloLetrasException extends Exception {
	        public ProductoSoloLetrasException() { super("PRODUCTO: solo se permiten letras."); }
	    }
	    public static class ProductoLongitudException extends Exception {
	        public ProductoLongitudException() { super("PRODUCTO: máximo 15 caracteres."); }
	    }

	    public static class CantidadSoloNumerosException extends Exception {
	        public CantidadSoloNumerosException() { super("CANTIDAD: solo se permiten números."); }
	    }
	    public static class CantidadLongitudException extends Exception {
	        public CantidadLongitudException() { super("CANTIDAD: máximo 6 dígitos."); }
	    }

	    public static class TipoObligatorioException extends Exception {
	        public TipoObligatorioException() { super("Debe seleccionar un TIPO."); }
	    }

	    public static class EstadoObligatorioException extends Exception {
	        public EstadoObligatorioException() { super("Debe seleccionar un ESTADO."); }
	    }
	    
	    public static class NumeroSolicitudObligatorioException extends Exception {
	        public NumeroSolicitudObligatorioException() { super("N° de Solicitud vacia."); }
	    }

	    
	    
	    //validaciones de registro_inventario
	    
	    
	    public static class CantidadSoloNumerosException2 extends Exception {
	        public CantidadSoloNumerosException2() { super("CANTIDAD: solo se permiten números."); }
	    }
	    public static class CantidadLongitudException2 extends Exception {
	        public CantidadLongitudException2() { super("CANTIDAD: máximo 6 dígitos."); }
	    }
	    
	    
	    public static class ProductoDuplicadoException extends Exception {
	        public ProductoDuplicadoException() { 
	            super("El nombre de este producto ya está registrado en el sistema."); 
	        }
	    }
	   	   
	}