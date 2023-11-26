package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws
        ClassNotFoundException,
        NoSuchMethodException,
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException,
        IllegalArgumentException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        final var drawst = Class.forName("it.unibo.mvc.view.DrawNumberStdoutView");
        final var drawswing = Class.forName("it.unibo.mvc.view.DrawNumberSwingView");
        for(int i=0; i<3; i++) {
            final var stdout = drawst.getConstructor().newInstance();
            final var swing = drawswing.getConstructor().newInstance();
            if(DrawNumberView.class.isAssignableFrom(stdout.getClass())) {
                app.addView((DrawNumberView)stdout);
            } else {
                throw new IllegalStateException(stdout.getClass() + " not valid");
            }
            if(DrawNumberView.class.isAssignableFrom(swing.getClass())) {
                app.addView((DrawNumberView)swing);
            } else {
                throw new IllegalStateException(swing.getClass() + " not valid");
            }
        }
        
    }
}
