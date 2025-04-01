package Clases;

import javax.swing.*;

public class MaquinaExpendedora implements OperacionesMaquina {
    private final Golosinas[][] productos=new Golosinas[4][4];
    private final int[][] stock=new int[4][4];
    private double totalVentas=0;

    //Método constructor = Construir la máquina expendedora....
    public MaquinaExpendedora() {
        inicializarProductos();
        rellenarMaquina(5);
    }


    //Métodos internos -> Solo serán accesibles cuando se cree la máquina (cuando se llama al método constructor)
    private void inicializarProductos() {
        //Como los productos van en la clase Golosinas, necesitaremos wl nombre y el precio
        String[][] nombres ={
                {"KitKat", "Chicles de fresa", "Lacasitos", "Palotes"},
                {"Kinder Bueno", "Haribo", "Chettos", "Twix"},
                {"Oreo", "M&M's", "Lays", "Chicles de menta"},
                {"CocaCola", "Batido Vainilla", "Patatas Fritas", "Agua 50ml"}
        };
        double[][] precios = {
                {1.4,2.0,2.5,1.5},
                {2.5,1.0,1.4,2.3},
                {1.5,2.0,1.0,1.5},
                {2.0,1.2,1.5,1.0}
        };

        //En este punto tenemos que rellenar el Array de Productos con nombre y precios
        for(int i=0;i<stock.length;i++){
            for(int j=0;j<stock[i].length;j++){
                productos[i][j]=new Golosinas(nombres[i][j],precios[i][j]);
            }
        }
    }

    private void rellenarMaquina(int cantidad) {
        for(int i=0;i<stock.length;i++){
            for(int j=0;j<stock[i].length;j++){
                stock[i][j]=cantidad;
            }
        }

    }


    //Implementar los métodos de la interfaz
    @Override
    public void pedirGolosinas() {
        String op =JOptionPane.showInputDialog(mostrarGolosinas().toString()+"\nIndica una opción → ");
        try{
            int opcion=Integer.parseInt(op);
            int fila = opcion/10;
            int columna = opcion%10;
            if(fila>=0 && fila<4 && columna>=0 && columna<4){
                if(stock[fila][columna]>0){
                    Golosinas g = productos[fila][columna]; //creo un objeto Golosina
                    double precio = g.getPrecio(); //leemos el precio
                    //Pedimos al usuario que nos indiqiue cuanto nos va a pagar...
                    String dineroStr = JOptionPane.showInputDialog("Precio del Producto: "+precio+"€\nIntroduzca el dinero → ");
                    double dinero = Double.parseDouble(dineroStr);
                    //Validamos el dinero ingresado por el usuario
                    if(dinero>=precio){
                        double cambio=dinero-precio; //Mostramos el cambio
                        stock[fila][columna]--; //Restamos al stock
                        totalVentas+=precio; //Acumulamos la venta
                        JOptionPane.showMessageDialog(null,"Disfrute de su " + g.getNombre() + "\nTu cambios es de → " + String.format("%.2f", cambio)+"€");
                    }else {
                        JOptionPane.showMessageDialog(null, "Dinero insuficiente para este prducto");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Lo lamentamos, no hay stock...");
                }
            }else {
                JOptionPane.showMessageDialog(null,"Codigo inválido");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Opción inválida");
        }
    }

    @Override
    public StringBuilder mostrarGolosinas() {
        StringBuilder sb=new StringBuilder("Golosinas disponibles:\n");
        for(int i=0;i<productos.length;i++){
            for(int j=0;j<productos[i].length;j++){
                sb.append(i).append(j).append(" - ").append(productos[i][j].mostrarProducto()).append("| Stock: ").append(stock[i][j]).append("\n").append(" ");
            }
        }
        return sb;
    }

    @Override
    public void rellenarStock() {
        //El tecnico que rellena el stock, tiene una clave de acceso
        String pass=JOptionPane.showInputDialog(null,"Clave de acceso (técnico): ");
        if (pass==null){
            return;
        } else if (pass.equals("chuch3s")) {
            rellenarMaquina(5);
            JOptionPane.showMessageDialog(null,"Stock rellenado correctamente");
        }else {
            JOptionPane.showMessageDialog(null,"Contraseña incorrecta. Acceso denegado");
        }
    }

    @Override
    public void apagarMaquina() {
        JOptionPane.showMessageDialog(null,"Esta maquina a reacudado → "+String.format("%.2f",totalVentas)+"€");
    }
}
