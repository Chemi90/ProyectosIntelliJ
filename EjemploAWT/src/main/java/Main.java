import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args){

        //crear una ventana
        Frame ventana = new Frame("Ventana de ejemplo");
        //ponerle tamaño a la ventana
        ventana.setSize(500,500);

        //tener los componentes de la ventana ordenados
        FlowLayout layout = new FlowLayout();
        ventana.setLayout(layout);

        //crear un boton
        Button boton = new Button("Mi primer boton");
        ventana.add(boton);

        //crear texto en la ventana
        Label label = new Label("Mi primer texto en una ventana");
        ventana.add(label);

        //opcion para poder cerrar la ventana
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        //accion del boton
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Este es mi primer evento de boton");
            }
        });

        //hacer visible la ventana
        ventana.setVisible(true);

    }
}
