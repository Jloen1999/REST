package es.unex.cum.tw.models;

import java.util.ArrayList;
import java.util.List;
/*
 * Clase para la implementación en memoria
 */
public class CartaData {
	private int idCartaData;
	private int idUser;
	private List<ItemData> lProductos;


	public CartaData() {
		super();
		this.lProductos = new ArrayList<ItemData>();
	}

	public CartaData(int idUser) {
		super();
		this.idUser = idUser;
		this.lProductos = new ArrayList<ItemData>();
	}

	public CartaData(int idCartaData, int idUser) {
		this.idCartaData = idCartaData;
		this.idUser = idUser;
		this.lProductos = new ArrayList<ItemData>();
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public List<ItemData> getlProductos() {
		return lProductos;
	}

	public void setlProductos(List<ItemData> lProductos) {
		this.lProductos = lProductos;
	}
	public void addProducto(ItemData i){
		lProductos.add(i);
	}

	public int getIdCartaData() {
		return idCartaData;
	}

	public void setIdCartaData(int idCartaData) {
		this.idCartaData = idCartaData;
	}
}
