import java.util.Scanner;
//import java.text.DecimalFormat;

public class testenota{
    
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    float notas[] = {200, 100, 50, 20, 10, 5, 2, 1, 0.5f, 0.25f, 0.10f, 0.05f, 0.01f};
    int aux[] = new int[13];

    System.out.println("Caixa Eletrônico: \n");

    System.out.println("Quanto dinheiro você quer sacar:");
    float dinheiro = sc.nextFloat();
    //String valorFormatado = new DecimalFormat("#,##0.00").format(dinheiro);

    do {
        if (dinheiro >= notas[0]) {
            aux[0] += 1;
            dinheiro -= notas[0];
        } else if (dinheiro >= notas[1]) {
            aux[1] += 1;
            dinheiro -= notas[1];
        } else if (dinheiro >= notas[2]) {
            aux[2] += 1;
            dinheiro -= notas[2];
        } else if (dinheiro >= notas[3]) {
            aux[3] += 1;
            dinheiro -= notas[3];
        } else if (dinheiro >= notas[4]) {
            aux[4] += 1;
            dinheiro -= notas[4];
        } else if (dinheiro >= notas[5]) {
            aux[5] += 1;
            dinheiro -= notas[5];
        } else if (dinheiro >= notas[6]) {
            aux[6] += 1;
            dinheiro -= notas[6];
        } else if (dinheiro >= notas[7]) {
            aux[7] += 1;
            dinheiro -= notas[7];
        } else if (dinheiro >= notas[8]) {
            aux[8] += 1;
            dinheiro -= notas[8];
        } else if (dinheiro >= notas[9]) {
            aux[9] += 1;
            dinheiro -= notas[9];
        } else if (dinheiro >= notas[10]) {
            aux[10] += 1;
            dinheiro -= notas[10];
        } else if (dinheiro >= notas[11]) {
            aux[11] += 1;
            dinheiro -= notas[11];
        } else if (dinheiro >= notas[12]) {
            aux[12] += 1;
            dinheiro -= notas[12];
            break;
        }
    } while (dinheiro > 0);

    System.out.println(aux[0] + " notas de R$200");
    System.out.println(aux[1] + " notas de R$100");
    System.out.println(aux[2] + " notas de R$50");
    System.out.println(aux[3] + " notas de R$20");
    System.out.println(aux[4] + " notas de R$10");
    System.out.println(aux[5] + " notas de R$5");
    System.out.println(aux[6] + " notas de R$2");
    System.out.println(aux[7] + " moedas de R$1");
    System.out.println(aux[8] + " moedas de R$0,50");
    System.out.println(aux[9] + " moedas de R$0,25");
    System.out.println(aux[10] + " moedas de R$0,10");
    System.out.println(aux[11] + " moedas de R$0,05");
    System.out.println(aux[12] + " moedas de R$0,01");
}

}