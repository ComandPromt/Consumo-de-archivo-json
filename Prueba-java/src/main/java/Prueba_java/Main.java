package Prueba_java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

public class Main {

	static int indice = 0;

	static LinkedList<String> miembros = new <String>LinkedList();

	static LinkedList<String> subordinados = new <String>LinkedList();

	static LinkedList<String> jefes = new <String>LinkedList();

	static LinkedList<String> miembrosEnCarcel = new <String>LinkedList();

	static LinkedList<Integer> posicionMiembrosEnCarcel = new <Integer>LinkedList();

	static LinkedList<Integer> posicionSubordinadoMiembrosEnCarcel = new <Integer>LinkedList();

	public static LinkedList<Integer> getPosicionMiembrosEnCarcel() {
		return posicionMiembrosEnCarcel;
	}

	public static void setPosicionMiembrosEnCarcel(int indice) {
		Main.posicionMiembrosEnCarcel.add(indice);

	}

	public static LinkedList<Integer> getPosicionSubordinadoMiembrosEnCarcel() {
		return posicionSubordinadoMiembrosEnCarcel;
	}

	public static void setPosicionsSubordinadoMiembrosEnCarcel(int indice) {
		Main.posicionSubordinadoMiembrosEnCarcel.add(indice);
	}

	public static LinkedList<String> getMiembrosEnCarcel() {
		return miembrosEnCarcel;
	}

	public static void setMiembrosEnCarcel(String miembro) {
		Main.miembrosEnCarcel.add(miembro);
	}

	public static void main(String[] args) throws Exception {

		Carcel carcel = new Carcel();

		leerDatosJson();

		carcel.entrar("Jhon");

		// reorganizar subordinados

		carcel.salir("Jhon");

		for (int i = 0; i < miembros.size(); i++) {
			
			System.out.println(miembros.get(i) + "\n");

			System.out.println(jefes.get(i) + "\n");
			
		}

	}

	public static int getIndice() {
		return indice;
	}

	public static void setIndice(int indice) {
		Main.indice = indice;
	}

	public static LinkedList<String> getMiembros() {
		return miembros;
	}

	public static void setMiembros(LinkedList<String> miembros) {
		Main.miembros = miembros;
	}

	public static LinkedList<String> getJefes() {
		return jefes;
	}

	public static void setJefes(int indice, String dato) {
		Main.jefes.set(indice, dato);
	}

	private static void leerDatosJson() throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("datos.json"));
		String storeJson = br.lines().collect(Collectors.joining());
		br.close();

		ReadContext readContext = JsonPath.parse(storeJson);

		Map<String, String> expressions = new LinkedHashMap<>();
		expressions.put("nombre", "$.members[*].name");
		expressions.put("subordinados", "$.members[*].subordinates");
		expressions.put("jefes", "$.members[*].boss");

		expressions.forEach((key, expression) -> {

			Object value = readContext.read(expression);

			String[] parts = value.toString().split(",");

			for (int i = 0; i < parts.length; i++) {

				parts[i] = limpiarCadena(parts[i]);

				switch (indice) {

				case 0:

					parts[i] = parts[i].replace("[", "");

					parts[i] = parts[i].replace("]", "");

					miembros.add(parts[i]);

					break;

				case 1:

					subordinados.add(parts[i]);

					break;

				case 2:

					parts[i] = parts[i].replace("[", "");

					parts[i] = parts[i].replace("]", "");

					jefes.add(parts[i]);

					break;
				}

			}

			indice++;

		});
	}

	private static String limpiarCadena(String cadena) {

		cadena = cadena.replace("\"", "");

		cadena = cadena.replace("[[", "[");

		cadena = cadena.replace("]]", "]");

		cadena = cadena.replace("[]", "none");

		cadena = cadena.trim();

		return cadena;

	}

	public static LinkedList<String> getSubordinados() {
		return subordinados;
	}

	public static void setSubordinados(int indice, String dato) {
		Main.subordinados.set(indice, dato);
	}

}
