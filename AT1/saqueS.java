package AT1;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.math.RoundingMode;
import java.util.stream.Collectors;

public class saqueS implements funcoes {

	float d;
	Scanner sc = new Scanner(System.in);
	BigDecimal zero = new BigDecimal(0);
	List<BigDecimal> notas = new ArrayList<BigDecimal>();
	List<Integer> qntd = new ArrayList<Integer>();

	public void lista() {
		notas.add(new BigDecimal(200));
		notas.add(new BigDecimal(100));
		notas.add(new BigDecimal(50));
		notas.add(new BigDecimal(20));
		notas.add(new BigDecimal(10));
		notas.add(new BigDecimal(5));
		notas.add(new BigDecimal(2));
		notas.add(new BigDecimal(1));
		notas.add(new BigDecimal(0.50));
		notas.add(new BigDecimal(0.25));
		notas.add(new BigDecimal(0.10));
		notas.add(new BigDecimal(0.05));
		notas.add(new BigDecimal(0.01));

		//ainda possui problema no calculo das moedas de 1 centavo

		for(int i = 0; i <= 12; i++) {
            this.qntd.add(i, 0);
		}

	}

	//List<Integer> qntd = new ArrayList<Integer>(13);
	// BigDecimal dinheiro = sc.nextBigDecimal();


	@Override
	public void recebe () {
		System.out.println("Caixa Eletrônico: \n");
		System.out.println("Quanto dinheiro você quer sacar:");
		d = sc.nextFloat();
		do {
			if (d < 0) {
				System.out.println("VALOR INVALIDO");
			}
		} while (d < 0);
	}

	@Override
	public void verfica() {
		while(true){
			try {
				recebe();
				break;
			} catch (Exception e) {
				System.out.println("VALOR INVALIDO");
			}
		}
	}

	
    public void calculo(float d) {
        BigDecimal dBD = new BigDecimal(d);
        dBD = dBD.setScale(2, RoundingMode.HALF_EVEN);
        int y = 0;
        int l = 0;
        int q = 0;
        while(dBD.compareTo(BigDecimal.valueOf(0)) > 0 && y <= 12 && l <= 12) {
            q = 0;
            while(dBD.compareTo(this.notas.get(y)) >= 0){
                q++;
                dBD = dBD.subtract(this.notas.get(y));
            }
            qntd.add(l, qntd.get(l) + q);
            l++;
            y++;
        }


        for(int i = 0; i <= 6; i++){
            if(qntd.get(i) != 0){
                System.out.println(qntd.get(i) + " Notas de " + this.notas.get(i) + " reais");
            }
        }

        for(int i = 7; i <= 12; i++){
            if(qntd.get(i) != 0){
                if(this.notas.get(i).compareTo(BigDecimal.valueOf(1)) == 0){
                    System.out.println(qntd.get(i) + " Moeda(s) de " + this.notas.get(i) + " real");
                }
                if(!(this.notas.get(i).equals(BigDecimal.valueOf(1))) && qntd.get(i) > 0){
                    System.out.println(qntd.get(i) + " Moeda(s) de " + (this.notas.get(i).multiply(BigDecimal.valueOf(100))) + " centavos");
                }
            }
        }
    }

	@Override
	public void operacao(){
		this.lista();
		this.verfica();
		this.calculo(d);
	}

	@Override
	public void calculo() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'calculo'");
	}

}
