package nl.bestego;

import java.util.Scanner;

public class InputHandler {

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
