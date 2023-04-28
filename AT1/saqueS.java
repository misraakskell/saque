package AT1;

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.IndexOutOfBoundsException;

public class saqueS implements funcoes{

	float d;
	int c;
	Scanner sc = new Scanner(System.in);
	BigDecimal todoValorDisponivel = new BigDecimal(0);
	BigDecimal valorConta = new BigDecimal(0);
	List<BigDecimal> notas = new ArrayList<BigDecimal>();
	List<Integer> qntdSacada = new ArrayList<Integer>();
	List<Integer> cedulasDisponiveis = new ArrayList<Integer>();
	Map<BigDecimal, Integer> qntdSacadas = new TreeMap<>((Collections.reverseOrder())); //valor e chave respectivamente
	Map<BigDecimal, Integer> cedulasDisponiveis1 = new TreeMap<>((Collections.reverseOrder()));

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

//		for(int i = 0; i <= 12; i++) { //Integer i : qntdSacada
//            this.qntdSacada.add(i, 0);
//		}
	}

	public void valorDisponivel(){
		cedulasDisponiveis1.keySet().forEach(key -> {
					this.todoValorDisponivel = this.todoValorDisponivel.add(key.multiply
							(BigDecimal.valueOf(cedulasDisponiveis1.get(key)))).setScale(2, RoundingMode.HALF_EVEN);
				}
		);
		System.out.println("Valor disponivel para saque: " + this.todoValorDisponivel);
	}

	@Override
	public void caixa() {
		notas.forEach(nota -> {
			System.out.println("Quantas notas de " + nota.setScale(2, RoundingMode.HALF_EVEN) + " há disponivel?");
			c = sc.nextInt();
			cedulasDisponiveis1.put(nota,c);
		});
//		cedulasDisponiveis.forEach(cedula -> {
//			System.out.println(cedula);
//		});
	}

	@Override
	public void conta() {
		System.out.println("Quanto você possui em conta?");
		valorConta = sc.nextBigDecimal();
	}

	@Override
	public void recebe () {
		do {
			System.out.println("\nCaixa Eletrônico");
			System.out.println("Quanto dinheiro você quer sacar:");
			d = sc.nextFloat();
			if (d < 0) {
				System.out.println("valor inválido");
			} else if (d == 0) {
				System.out.println("valor inválido por ser nulo");
			} else if (this.todoValorDisponivel.compareTo(BigDecimal.valueOf(d)) < 0) {
				System.out.println("valor requerido é maior que o valor disponível");
			} else if (BigDecimal.valueOf(d).compareTo(valorConta) > 0){
				System.out.println("valor disponível em conta não é suficiente");
			}
		} while (d <= 0 || this.todoValorDisponivel.compareTo(BigDecimal.valueOf(d)) < 0 || this.valorConta.compareTo(BigDecimal.valueOf(d)) < 0);
	}

	@Override
	public void verfica() {
		while(true){
			try {
				recebe();
				break;
			} catch (Exception e) {
				System.out.println("valor inválido");
			}
		}
	}


	public void calculo(float d) {
		BigDecimal dBD = new BigDecimal(d);
		BigDecimal[] qs = new BigDecimal[1];
		qs[0] = dBD;
		int[] q2 = new int[1];

		dBD = dBD.setScale(2, RoundingMode.HALF_EVEN);
		int y = 0;
		int q;
		while(dBD.compareTo(BigDecimal.valueOf(0)) > 0 && y <= 12) {
			q = 0;
			while(dBD.compareTo(this.notas.get(y)) >= 0 && cedulasDisponiveis.get(y) > q){
				q++;
				dBD = dBD.subtract(this.notas.get(y));
			}
			qntdSacada.add(y, qntdSacada.get(y) + q);
			y++;
		}

		qntdSacadas.keySet().forEach(key -> {
			if (!qs[0].equals(BigDecimal.ZERO)) {
				q2[0] = 0;
					while (qs[0].compareTo(key) >= 0 && cedulasDisponiveis1.get(key) > q2[0]) {
							qs[0] = qs[0].subtract(key);
							q2[0]++;
					}
				}
			}
		);


		for(int i = 0; i <= 6; i++){
			if(qntdSacada.get(i) != 0){
				System.out.println(qntdSacada.get(i) + " Notas de " + this.notas.get(i) + " reais");
			}
		}

		for(int i = 7; i <= 12; i++){
			if(qntdSacada.get(i) != 0){
				if(this.notas.get(i).compareTo(BigDecimal.valueOf(1)) == 0){
					System.out.println(qntdSacada.get(i) + " Moeda(s) de " + this.notas.get(i) + " real");
				}
				if(!(this.notas.get(i).equals(BigDecimal.valueOf(1))) && qntdSacada.get(i) > 0){
					System.out.println(qntdSacada.get(i) + " Moeda(s) de " + (this.notas.get(i).multiply(BigDecimal.valueOf(100))) + " centavos");
				}
			}
		}
	}


	@Override
	public void operacao(){
		this.lista();
		this.caixa();
		this.conta();
		this.valorDisponivel();
		this.verfica();
		this.calculo(d);
	}

}
