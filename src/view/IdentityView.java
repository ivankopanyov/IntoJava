package view;

import abstractions.View;

import java.util.Scanner;

public class IdentityView implements View {

    Scanner scanner;

    public IdentityView() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String[] input(String[] titles) {

        if (titles == null || titles.length == 0)
            return new String[0];

        String[] result = new String[titles.length];
        for (int i = 0; i < titles.length; i++) {
            System.out.println(titles[i]);
            result[i] = scanner.nextLine();
        }

        return result;
    }

    @Override
    public int select(String[] titles) {
        return 0;
    }

    @Override
    public void print(String message) {

    }
}
