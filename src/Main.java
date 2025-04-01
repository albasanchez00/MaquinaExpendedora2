import Clases.MaquinaExpendedora;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        menu();

    }

    public static void menu(){
        MaquinaExpendedora maquina = new MaquinaExpendedora();
        boolean salir=false;
        do {
            String opcionS=JOptionPane.showInputDialog(null,
                    """
                            *********Maquina Expendedora*********
                            ******** 1. Pedir Golosinas ********
                            ******* 2. Mostrar Golosinas *******
                            ******* 3. Reponer Golosinas *******
                            ******** 4. Apagar máquina *********
                            ******** Introduce tu opción →
                            """);
            if (opcionS==null){
                return;
            }
            try{
                switch (opcionS){
                    case "1"->maquina.pedirGolosinas();
                    case "2" -> JOptionPane.showMessageDialog(null,maquina.mostrarGolosinas().toString());
                    case "3" -> maquina.rellenarStock();
                    case "4" -> {
                        maquina.apagarMaquina();
                        salir=true;
                    }
                    default -> {
                        JOptionPane.showMessageDialog(null, "Opcion no válida");
                    }
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Por favor ");
            }
        }while(salir==false);
    }
}