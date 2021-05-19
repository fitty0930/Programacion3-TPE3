package ProgramacionIII.tp3.entregable;

public class Main {

	public static void main(String[] args) {
		
		// Ciudades creadas
		Ciudad ayacucho = new Ciudad(1,"Ayacucho");
		Ciudad tandil = new Ciudad(2,"Tandil");
		Ciudad olavarria= new Ciudad(3,"Olavarria");
		Ciudad bolivar = new Ciudad(4,"Bolivar");
		Ciudad azul= new Ciudad(5, "Azul");
		Ciudad mardelplata= new Ciudad(6, "Mar del Plata");
		Ciudad rauch= new Ciudad(7, "Rauch");
		Ciudad pehuajo= new Ciudad (8, "Pehuajo");
		// Agrego datos de las ciudades
		// Pehuajo 3 si si 5
		pehuajo.setEstacionesDeServicio(3);
		pehuajo.setTieneBalanza(true);
		pehuajo.setTieneRadar(true);
		pehuajo.setTallerMecanicos(5);
		// Ayacucho 1 no no 2
		ayacucho.setEstacionesDeServicio(3);
		ayacucho.setTieneBalanza(false);
		ayacucho.setTieneRadar(false);
		ayacucho.setTallerMecanicos(2);
		// Olavarria 9 si no 17
		olavarria.setEstacionesDeServicio(9);
		olavarria.setTieneBalanza(true);
		olavarria.setTieneRadar(false);
		olavarria.setTallerMecanicos(17);
		// Rauch 1 no si 0
		rauch.setEstacionesDeServicio(1);
		rauch.setTieneBalanza(false);
		rauch.setTieneRadar(true);
		rauch.setTallerMecanicos(0);
		// Bolivar 7 no no 4
		bolivar.setEstacionesDeServicio(7);
		bolivar.setTieneBalanza(false);
		bolivar.setTieneRadar(false);
		bolivar.setTallerMecanicos(4);
		// Tandil 6 si si 5
		tandil.setEstacionesDeServicio(6);
		tandil.setTieneBalanza(true);
		tandil.setTieneRadar(true);
		tandil.setTallerMecanicos(5);
		// Azul 4 no si 4
		azul.setEstacionesDeServicio(4);
		azul.setTieneBalanza(false);
		azul.setTieneRadar(true);
		azul.setTallerMecanicos(4);
		// Mar del plata 15 si no 12
		mardelplata.setEstacionesDeServicio(15);
		mardelplata.setTieneBalanza(true);
		mardelplata.setTieneRadar(false);
		mardelplata.setTallerMecanicos(12);



		Mapa mapa = new Mapa();
		
		mapa.addCiudad(ayacucho); // Agrego ayacucho
		mapa.addCiudad(tandil); // Agrego tandil
		mapa.addCiudad(olavarria); // Agrego olavarria
		mapa.addCiudad(bolivar); // Agrego bolivar
		mapa.addCiudad(azul);	// Agrego azul
		mapa.addCiudad(mardelplata); // Agrego mar del plata
		mapa.addCiudad(rauch); // Agrego rauch
		mapa.addCiudad(pehuajo); // Agrego pehuajo
		
		mapa.addRuta(ayacucho, tandil, 70); // Agrego la ruta entre ayacucho-tandil de 70km
		mapa.addRuta(tandil, mardelplata, 200);
		mapa.addRuta(tandil, olavarria, 130);
		mapa.addRuta(tandil, rauch, 60);
		mapa.addRuta(olavarria, rauch, 210);
		mapa.addRuta(olavarria, bolivar, 140);
		mapa.addRuta(bolivar, azul, 100);
		mapa.addRuta(bolivar, pehuajo, 70);
		mapa.addRuta(pehuajo, ayacucho, 540);
		mapa.addRuta(ayacucho, rauch, 50);

//		mapa.borrarRuta(ayacucho, rauch); // Borro la ruta entre ayacucho-tandil
//		System.out.println(mapa.encontrarCamino(ayacucho, bolivar)); // Busco el mejor camino entre ayacucho y bolivar
		
//		mapa.borrarCiudad(ayacucho); // Borro la ciudad ayacucho
//		mapa.borrarRuta(ayacucho, tandil); // Borro la ruta entre ayacucho-tandil
		// PRUEBAS DEL TPE
		System.out.println("pruebas tpe");
		System.out.println("orig");
		mapa.encontrarCamino(azul, ayacucho);
//		System.out.println("depre");
//		mapa.encontrarCaminoDeprecado(azul, ayacucho);
		System.out.println("orig");
		mapa.encontrarCamino(rauch, mardelplata);
//		System.out.println("depre");
//		mapa.encontrarCaminoDeprecado(rauch, mardelplata);
		System.out.println("orig");
		mapa.encontrarCamino(mardelplata, pehuajo);
//		System.out.println("depre");
//		mapa.encontrarCaminoDeprecado(mardelplata, pehuajo);
		mapa.borrarCiudad(ayacucho);
		mapa.borrarRuta(tandil, rauch);
		System.out.println("orig");
		mapa.encontrarCamino(mardelplata, pehuajo); // el problema esta aca
//		System.out.println("depre");
//		mapa.encontrarCaminoDeprecado(mardelplata, pehuajo);
//		System.out.println("romper");
//		mapa.encontrarCamino(null,null);
	}

}
