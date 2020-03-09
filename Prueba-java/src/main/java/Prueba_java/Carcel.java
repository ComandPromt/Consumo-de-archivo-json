package Prueba_java;

public class Carcel {

	public void entrar(String nombre) {

		int busqueda = Main.getJefes().indexOf(nombre);

		Main.setMiembrosEnCarcel(nombre);

		String jefe = "";

		int posiconSubordinado;

		String subordinado;

		if (Main.getJefes().indexOf(nombre) != -1) {

			jefe = Main.getJefes().get((Main.getMiembros().indexOf(nombre)));
		}

		posiconSubordinado = Main.getSubordinados().indexOf("[" + nombre);

		if (posiconSubordinado == -1) {

			posiconSubordinado = Main.getSubordinados().indexOf(nombre);

			if (posiconSubordinado == -1) {

				posiconSubordinado = Main.getSubordinados().indexOf(nombre + "]");

			}

		}

		subordinado = Main.getSubordinados().get(posiconSubordinado);

		char ch1 = subordinado.charAt(0);

		if ((int) ch1 >= 91) {
			Main.setSubordinados(posiconSubordinado, ch1 + jefe);
		}

		else {
			Main.setSubordinados(posiconSubordinado, jefe);
		}

		Main.setPosicionsSubordinadoMiembrosEnCarcel(posiconSubordinado);

		while (busqueda != -1) {

			Main.setPosicionMiembrosEnCarcel(busqueda);

			Main.setJefes(busqueda, jefe);

			busqueda = Main.getJefes().indexOf(nombre);
		}

		mostrarMiembros();

	}

	public void mostrarMiembros() {

		System.out.println("\n------ Miembros ------\n");

		for (int i = 0; i < Main.getMiembros().size(); i++) {
			System.out.println(Main.getMiembros().get(i));
		}

		System.out.println("------\n");

		System.out.println("\n------ Jefes ------\n");

		for (int i = 0; i < Main.getJefes().size(); i++) {
			System.out.println(Main.getJefes().get(i));
		}

		System.out.println("------\n");

		System.out.println("\n------ Subordinados ------\n");

		for (int i = 0; i < Main.getSubordinados().size(); i++) {
			System.out.println(Main.getSubordinados().get(i));
		}

		System.out.println("------\n");

		System.out.println("\n------ Cabeza de banda (pez gordo) ------\n");

		System.out.println(Main.getMiembros().get(Main.getJefes().indexOf("")));

		System.out.println("\n------\n");

	}

	public void salir(String nombre) {

		for (int i = 0; i < Main.getPosicionMiembrosEnCarcel().size(); i++) {
			Main.setJefes(Main.getPosicionMiembrosEnCarcel().get(i), nombre);
		}

		for (int i = 0; i < Main.getPosicionSubordinadoMiembrosEnCarcel().size(); i++) {
			Main.setSubordinados(Main.getPosicionSubordinadoMiembrosEnCarcel().get(i), nombre);
		}

		Main.getPosicionMiembrosEnCarcel().remove(nombre);

		Main.getPosicionSubordinadoMiembrosEnCarcel().remove(nombre);

		mostrarMiembros();

	}

}
