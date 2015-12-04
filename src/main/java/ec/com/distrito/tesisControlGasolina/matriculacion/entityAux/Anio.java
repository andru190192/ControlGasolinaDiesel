package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entityAux;

public enum Anio {

	a2014("2014"), a2015("2015"), a2016("2016"), a2017("2017"), a2018("2018"), a2019("2019"), a2020("2020");

	private final String id;

	private Anio(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public static Anio obtenerPorId(String id) {
		for (Anio a : Anio.values())
			if (a.getId().compareTo(id) == 0)
				return a;
		return null;
	}
}