package org.example.q3.model;

import org.example.q1.model.Pessoa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgenciaBancaria implements OperacoesAgencia{
    private String nome;
    private Map<String, Conta> contas;

    public AgenciaBancaria(String nome) {
        this.nome = nome;
        this.contas = new HashMap<>() {{
            put("123", new Conta("123", "Maria", 10.0, 13.14));
            put("124", new Conta("124", "João", 0.0, 13.14));
        }};
    }

    public String deposito(String contaId, Double valor) {
        var conta = this.contas.get(contaId);

        if (conta != null) {
            conta.deposito(valor);
            return "Depósito de R$" + valor + " realizado com sucesso";
        } else {
            return "Conta " + contaId + " não encontrada";
        }
    }

    public String saque(String contaId, Double valor) {
        var conta = this.contas.get(contaId);

        if (conta != null) {
            try {
                conta.saque(valor);
            } catch (Exception e) {
                return "Saldo insuficiente";
            }
            return "Saque de R$" + valor + " realizado com sucesso";
        } else {
            return "Conta " + contaId + " não encontrada";
        }
    }

    
    public String saldo(String contaId) {
        var conta = this.contas.get(contaId);
        
        if (conta != null) {
            return "Seu saldo é de R$" + conta.verificarSaldo();
        } else {
            return "Conta " + contaId + " não encontrada";
        }
    }

    
    public String taxaJuros(String contaId) {
        var conta = this.contas.get(contaId);

        if (conta != null) {
            return "A taxa de juros é de " + conta.verificarTaxaJuros() + "%";
        } else {
            return "Conta " + contaId + " não encontrada";
        }
    }


    public String calcularJuros(String contaId) {
        var conta = this.contas.get(contaId);

        if (conta != null) {
            conta.calcularJuros();
            return "Juros calculados com sucesso";
        } else {
            return "Conta " + contaId + " não encontrada";
        }
    }

    public String transferir(String contaId, Double valor, String agenciaIdDestino , String contaIdDestino) {
        var conta = this.contas.get(contaId);
        //var contaDestino = agenciaDestino.getContas().get(contaIdDestino);
        var contaDestino = this.contas.get(contaIdDestino);

        if (conta != null && contaDestino != null) {
            try {
                conta.transferencia(valor, contaDestino);
            } catch (Exception e) {
                return "Saldo insuficiente";
            }
            return "Transferência de R$" + valor + " realizada com sucesso";

        }else if(contaDestino == null){
            return "Conta " + contaIdDestino + " não encontrada";

        } else {
            return "Conta " + contaId + " não encontrada";

        }
    }

    public void adicionarConta(Conta conta) {
        this.contas.put(conta.getNumeroConta(), conta);
    }

    public void encerrarConta(String contaId) {
        this.contas.remove(contaId);
    }

    public Map<String, Conta> getContas() {
        return contas;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Agência: ").append(nome).append('\n');

        sb.append("Contas:\n");
        for (Map.Entry<String, Conta> entry : contas.entrySet()) {
            String numeroConta = entry.getKey();
            Conta conta = entry.getValue();

            sb.append("\tNúmero da Conta: ").append(numeroConta).append(", ");
            sb.append("\tTitular: ").append(conta.getTitularConta()).append(", ");
            sb.append("\tSaldo: ").append(conta.getSaldo()).append(' ');
            sb.append('\n');
        }

        return sb.toString();
    }
}
