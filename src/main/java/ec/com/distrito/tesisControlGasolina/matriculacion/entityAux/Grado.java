package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entityAux;

public enum Grado {

	INI(0, "INICIAL"), PRI(1, "PRIMERO"), SEG(2, "SEGUNDO"), TER(3, "TERCERO"), CUA(4, "CUARTO"), QUI(5,
			"QUINTO"), SEX(6, "SEXTO"), SEP(7, "SEPTIMO"), OCT(8, "OCTAVO"), NOV(9, "NOVENO"), DEC(10, "DECIMO");

	private final Integer id;
	private final String nombre;

	private Grado(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public static Grado obtenerPorId(Integer id) {
		for (Grado a : Grado.values())
			if (a.getId().compareTo(id) == 0)
				return a;
		return null;
	}

	public static Grado obtenerPorNombre(String nombre) {
		for (Grado a : Grado.values())
			if (a.getId().toString().compareTo(nombre) == 0)
				return a;
		return null;
	}
}