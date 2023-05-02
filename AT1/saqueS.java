package AT1;

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class saqueS extends ClearBuffer implements funcoes{

	float d;
	int c;
	Scanner sc = new Scanner(System.in);
	BigDecimal todoValorDisponivel = new BigDecimal(0);
	BigDecimal valorConta = new BigDecimal(0);
	List<BigDecimal> notas = new ArrayList<BigDecimal>();
//	List<Integer> qntdSacada = new ArrayList<Integer>();
//	List<Integer> cedulasDisponiveis = new ArrayList<Integer>();
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
			while (true) {
				try {
					do {
						System.out.println("Quantas notas de " + nota.setScale(2, RoundingMode.HALF_EVEN) + " há disponivel?");
						c = sc.nextInt();
					} while(c < 0);
					break;
				} catch (Exception e) {
					clearBuffer(sc);
					System.out.println("proibido letra");
				}
			}
			cedulasDisponiveis1.put(nota.setScale(2, RoundingMode.HALF_EVEN), c);
		});
//		cedulasDisponiveis.forEach(cedula -> {
//			System.out.println(cedula);
//		});
	}

	@Override
	public void qntd(){
		notas.forEach(nota -> {
			qntdSacadas.put(nota.setScale(2, RoundingMode.HALF_EVEN), 0);
		});
	}

	@Override
	public void conta() {
		float x;
		while (true) {
			try {
				do {
					System.out.println("Quanto você possui em conta?");
					x = sc.nextFloat();
				} while (x < 0);
				break;
			} catch (Exception e) {
				clearBuffer(sc);
				System.out.println("proibido letra");
			}
		}
		valorConta = BigDecimal.valueOf(x);
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
			} else if (BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_EVEN).compareTo(todoValorDisponivel) > 0) {
				System.out.println("valor requerido é maior que o valor disponível");
			} else if (BigDecimal.valueOf(d).compareTo(valorConta) > 0){
				System.out.println("valor disponível em conta não é suficiente");
			}
		} while (d <= 0 || this.todoValorDisponivel.compareTo(BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_EVEN)) < 0
				|| this.valorConta.compareTo(BigDecimal.valueOf(d)) < 0);
	}

	@Override
	public void verfica() {
		while(true){
			try {
				recebe();
				break;
			} catch (Exception e) {
				clearBuffer(sc);
				System.out.println("proibido letra"); //loop infinito quando colocado uma letra??
			}
		}
	}


	public void calculo(float d) {
		BigDecimal dBD = new BigDecimal(d);
		BigDecimal[] qs = new BigDecimal[1];
		qs[0] = dBD;
		int[] q2 = new int[1];

		qntdSacadas.keySet().forEach(key -> {
			if (!qs[0].equals(BigDecimal.ZERO)) {
				q2[0] = 0;
				while (qs[0].compareTo(key) >= 0 && cedulasDisponiveis1.get(key) > q2[0]) {
					qs[0] = qs[0].subtract(key);
					q2[0]++;
				}
				qntdSacadas.put(key, q2[0]);
			}
		});

		qntdSacadas.keySet().forEach(key -> {
			if(qntdSacadas.get(key) != 0) {
				if (key.compareTo(BigDecimal.valueOf(1)) > 0) {
					System.out.println(qntdSacadas.get(key) + " notas de " + key + " reais");
				} else if (key.compareTo(BigDecimal.valueOf(1)) == 0) {
					System.out.println(qntdSacadas.get(key) + " moedas de " + key + " real");
				} else {
					System.out.println(qntdSacadas.get(key) + " moedas de " + key + " centavos");
				}
			}
		});
	}


	@Override
	public void operacao(){
		this.lista();
		this.caixa();
		this.qntd();
		this.conta();
		this.valorDisponivel();
		this.verfica();
		this.calculo(d);
	}

}
