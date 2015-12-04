package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entityAux;

public enum Mes {

	EN("01", "ENERO"), FE("02", "FEBRERO"), MA("03", "MARZO"), AB("04", "ABRIL"), MY(
			"05", "MAYO"), JU("06", "JUNIO"), JL("07", "JULIO"), AG("08",
			"AGOSTO"), SE("09", "SEPTIEMBRE"), OC("10", "OCTUBRE"), NO("11",
			"NOVIEMBRE"), DI("12", "DICIEMBRE");

	private final String id;
	private final String nombre;

	private Mes(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public static Mes obtenerPorId(String id) {
		for (Mes a : Mes.values())
			if (a.getId().compareTo(id) == 0)
				return a;
		return null;
	}

	public static Mes obtenerPorNombre(String nombre) {
		for (Mes a : Mes.values())
			if (a.getId().compareTo(nombre) == 0)
				return a;
		return null;
	}
}