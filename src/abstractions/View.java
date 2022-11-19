package abstractions;

public interface View {

    String[] input(String[] titles);

    int select(String[] titles);

    void print(String message);
}
