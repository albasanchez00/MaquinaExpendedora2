package Clases;

public class Golosinas extends Producto {

    public Golosinas(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String mostrarProducto() {
        return nombre+" - "+precio+"€";
    }
}
