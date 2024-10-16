package test02;

public class Main {
    public static void main(String[] args) {
        Input input = new Input();
        input.input("C:\\Users\\ASUS\\Desktop\\input.txt");
        Shift shift = new Shift(input.getLineTxt());
        shift.shift();
        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
        alphabetizer.sort();
        Output output = new Output(alphabetizer.getKwicList());
        output.output("C:\\Users\\ASUS\\Desktop\\output.txt");

    }
}
