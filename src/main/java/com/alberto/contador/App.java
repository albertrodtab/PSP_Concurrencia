package com.alberto.contador;

import com.alberto.contador.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/*
Esta clase debe heredar de la clase Application y debo crear metodos que me obliga la propia clase como el de Start
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppController controller = new AppController();

        /*
        le tenemos que decir en donde está el fichero FXML, para que sepa el controller donde está
        Lo sacamos a una Clase R como en android y creamos un método al que le pasamos el nombre de fichero
        para que los vincule
         */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUi("contador.fxml"));

        //le digo la clase donde estan los métodos que va a querer usar
        loader.setController(controller);
        //como dentro del diseño para cargar la ventana llamamos al método VBox y con el metodo load la cargamos
        VBox vBox = loader.load();

        //inicializamos la escena que es como inicializar la ventana, le pasamos el vBox donde está el diseño
        Scene scene = new Scene(vBox);
        //Esto es lo que tienes que inicializar y por ultimo lo pintamos.
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        //este método lanza el método start y lanza la app
        launch();

    }
}