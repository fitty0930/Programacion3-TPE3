package ProgramacionIII.tp3.entregable;

public class Ciudad {

	private int id;
	private String nombre;
	private int estacionesDeServicio;
	private boolean tieneBalanza;
	private boolean tieneRadar;
	private int tallerMecanicos;

	// O(1)
	public Ciudad(int id, String nombre) {
		this.nombre = nombre;
		this.id = id;
	}

	// O(1)
	public String getNombre() {
		return nombre;
	}

	// O(1)
	public boolean isTieneBalanza() {
		return tieneBalanza;
	}

	// O(1)
	public void setTieneBalanza(boolean tieneBalanza) {
		this.tieneBalanza = tieneBalanza;
	}

	// O(1)
	public int getEstacionesDeServicio() {
		return estacionesDeServicio;
	}

	// O(1)
	public void setEstacionesDeServicio(int estacionesDeServicio) {
		this.estacionesDeServicio = estacionesDeServicio;
	}

	// O(1)
	public boolean isTieneRadar() {
		return tieneRadar;
	}

	// O(1)
	public void setTieneRadar(boolean tieneRadar) {
		this.tieneRadar = tieneRadar;
	}

	// O(1)
	public int getTallerMecanicos() {
		return tallerMecanicos;
	}

	// O(1)
	public void setTallerMecanicos(int tallerMecanicos) {
		this.tallerMecanicos = tallerMecanicos;
	}

	// O(1)
	public int getId() {
		return id;
	}

}
