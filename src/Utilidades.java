import java.util.Scanner;

public class Utilidades {

    public static String validarString() {
        Scanner input = new Scanner(System.in);
        try {
            String str = input.nextLine();
            return str.equals("") ? validarString(): str;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            input.next();
            return validarString();
        }
    }


}
