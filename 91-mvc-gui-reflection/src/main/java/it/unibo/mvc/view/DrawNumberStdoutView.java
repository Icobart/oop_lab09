package it.unibo.mvc.view;

import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberStdoutView implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";

    private List<Integer> attempts;

    private DrawNumberController controller;

    public DrawNumberStdoutView() {
        this.attempts = List.of(10, 20, 50, 60, 34, 9, 81);
    }

    @Override
    public void setController(DrawNumberController observer) {
        this.controller = observer;    
    }

    @Override
    public void start() {
        for (int i=0; i<attempts.size(); i++) {
            controller.newAttempt(attempts.get(i));
        }
    }

    @Override
    public void result(DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> {
                System.out.println(res.getDescription());
                return;
            }
            case YOU_WON -> System.out.println(res.getDescription() + NEW_GAME);
            case YOU_LOST -> System.out.println(
                res.getDescription() + NEW_GAME);
            default -> throw new IllegalStateException("Unknown game state");
        }
        controller.resetGame();
    }

}