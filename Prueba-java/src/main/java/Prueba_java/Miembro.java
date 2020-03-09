package Prueba_java;
import java.util.LinkedList;

public class Miembro {

	private LinkedList<String> subordinado = new <String>LinkedList();
	private String superior;
	private String nombre;
	private String antiguedad;

	public LinkedList<String> getSubordinado() {
		return subordinado;
	}

	public void setSubordinado(LinkedList<String> subordinado) {
		this.subordinado = subordinado;
	}

	public String getSuperior() {
		return superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}

}
