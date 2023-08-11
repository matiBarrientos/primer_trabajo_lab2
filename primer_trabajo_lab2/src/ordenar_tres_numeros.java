import java.util.Scanner;

public class ordenar_tres_numeros {
    public static void main(String[] arg){
        Scanner s = new Scanner(System.in);
        int[] numeros = new int[4];

        System.out.println("ingrese el primer numero");
        numeros[0] = s.nextInt();
        System.out.println("ingrese el segundo numero");
        numeros[1] = s.nextInt();
        System.out.println("ingrese el tercer numero");
        numeros[2] = s.nextInt();
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3; j++) {
                if (numeros[i] > numeros[j]) {
                    numeros[3] = numeros[i];
                    numeros[i] = numeros[j];
                    numeros[j] = numeros[3];
                }
            }
        }
        System.out.println(numeros[0]+" > "+numeros[1]+" > "+numeros[2]);
    }
}
