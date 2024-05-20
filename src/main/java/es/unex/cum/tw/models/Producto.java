package es.unex.cum.tw.models;

public class Producto {
	private int idProducto;
	private String nombreProd;
	private String pathImagen;
	private String comentarios;
	
	public Producto(){

	}
	
	public Producto(int id,String nombreProd, String pathImagen, String comentarios) {
		this.idProducto =id;
		this.nombreProd = nombreProd;
		this.pathImagen = pathImagen;
		this.comentarios = comentarios;
	}

	public Producto(String nombreProd, String pathImagen, String comentarios) {
		this.nombreProd = nombreProd;
		this.pathImagen = pathImagen;
		this.comentarios = comentarios;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombreProd() {
		return nombreProd;
	}
	public void setNombreProd(String nombreProd) {
		this.nombreProd = nombreProd;
	}
	public String getPathImagen() {
		return pathImagen;
	}
	public void setPathImagen(String pathImagen) {
		this.pathImagen = pathImagen;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	

}
