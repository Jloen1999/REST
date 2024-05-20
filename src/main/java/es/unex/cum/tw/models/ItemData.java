package es.unex.cum.tw.models;

/*
 * Clase para la implementación en memoria
 */
public class ItemData {

	protected int idProducto;
	protected int idItemData;
	protected int idCartaData;
	protected int cantidad;


	public ItemData(int idItemData,int idProducto, int idCartaData, int cantidad) {
		this.idProducto = idProducto;
		this.idItemData = idItemData;
		this.idCartaData = idCartaData;
		this.cantidad = cantidad;
	}

	public ItemData() {
		idProducto = 0;
		idItemData = 0;
		idCartaData = 0;
		cantidad = 1;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdItemData() {
		return idItemData;
	}

	public void setIdItemData(int idItemData) {
		this.idItemData = idItemData;
	}

	public int getIdCartaData() {
		return idCartaData;
	}

	public void setIdCartaData(int idCartaData) {
		this.idCartaData = idCartaData;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
