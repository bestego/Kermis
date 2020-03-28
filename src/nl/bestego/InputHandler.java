package nl.bestego;

import java.util.Scanner;

public class InputHandler {

    public static void main(String[] args) {
        InputHandler ih = new InputHandler();
        String expected = "iets";
        String result = ih.getInput("[a-z]+|[A-Z]+", "geef letter: ", "foute invoer: ");
        System.out.println("result = " + result);
    }

    public String getInput(String regex, String prompt, String errorMessage) {
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        do {
            String line = input.nextLine();
            if (line.matches(regex)) {
                return line;
            } else {
                System.out.println(errorMessage);
            }
        } while (true);
    }
}
