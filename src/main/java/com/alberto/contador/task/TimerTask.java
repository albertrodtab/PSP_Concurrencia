package com.alberto.contador.task;

import javafx.concurrent.Task;

/*
Task <v>, v es el valor por el cual mi tarea va a evolucionar. Debo implementar el metodo call que siempre devuelve
algo.
 */
public class TimerTask extends Task<Integer> {

    private int count;

    public TimerTask(int count){
        this.count = count;
    }

    /*
    Le indico lo que quiero que haga mi tarea, en este caso contara numeros y por cada numero dormira 1 segundo
     */
    @Override
    protected Integer call() throws Exception {
        for (int i = 0; i <= count; i++){
            Thread.sleep(1000);
            //quiero que notifique como va evolucionando la tarea
            updateValue(i);
            //quiero que actualice el progreso en la barra le digo en donde va y cuanto es el total.
            updateProgress(i, count);

        }

        return null;
    }
}
