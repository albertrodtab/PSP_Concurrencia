package com.alberto.contador;

import com.alberto.contador.task.TimerTask;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class AppController {

    /*
    Esta clase me sirve para que mi aplicación y el interfaz puedan comunicarse.
     */

    /*
    como el contoller ya tiene cargada la interfaz, con declarar un objeto textfield
    lo debemos declarar como público con el mismo nombre del diseño ya está.
    */
    public TextField tfValue;
    private TimerTask timerTask;
    public ProgressBar progressBar;
    public Label statusLabel;

    public AppController(){

    }

    @FXML
    public void startCounter (Event event) {
        if (tfValue.getText().equals("")) {
            tfValue.setText("Debes introducir un valor para iniciar la cuenta atrás.");
        }else {
            int count = Integer.parseInt(tfValue.getText());

            //Instancio el timerTask y le paso la cuenta, tienes que contar hasta lo que indique el usuario .
            timerTask = new TimerTask(count);

            /*
            introduzco un listener que es un codigo que dejo programado para indicarle a algo, que cuando pase "X" haga "Y"
            Lo hago antes de lanzar la tarea para que sepa lo que tiene que hacer.
             */

            /*
            //este hace que cuando haya algun cambio de estado llama al método para que haga lo que nosotros programemos.
            timerTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
                @Override
                public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state, Worker.State t1) {

                }
            });
             */

            /*
            Esto también podemos hacerlo usando un lambda, antes de java 8 también clases anónimas.
            le indico que valor quiero observar y reviso el estado antiguo y el estado nuevo, así siempre se
            de donde vengo y adonde voy
             */
            timerTask.stateProperty().addListener((observableValue, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("La tarea ha termidado");
                    alert.show();


                }else if (newState == Worker.State.CANCELLED){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("La tarea ha sido cancelada por el usuario");
                    alert.show();
                }
            });

            //Desliga la barra de cualquier otra tarea a la que yo la haya ligado.
            progressBar.progressProperty().unbind();
            //ahora le indico a lo que tiene que estar ligada.
            progressBar.progressProperty().bind(timerTask.progressProperty());

            /*quiero que me cuente como va la tarea. messageProperty, valueProperty varias opciones, tengo que indicarlo
            en la tarea.
            */
            timerTask.valueProperty().addListener((observable, oldValue, newValue) -> {
                statusLabel.setText((newValue.toString()));
            });

            // para lanzar la tarea, meto la tarea y lanzo la tarea. Esto es el modo simple, lanzando un hilo.
            new Thread(timerTask).start();
        }

    }

    @FXML
    public void stopCounter (Event event) {
        //esto sirve para para parar la tarea, controlo que lo haga si la tarea se ha iniciado sino ya no la paro.
        if (timerTask != null)
            timerTask.cancel();
    }
}
