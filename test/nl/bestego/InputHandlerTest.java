package nl.bestego;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputHandlerTest {

    @Test
    public void getInput() {
        InputHandler ih = new InputHandler();
        String expected = "iets";
        String result = ih.getInput("[a-z]*", "geef letter: ", "foute invoer: ");
        assert true;
    }
}