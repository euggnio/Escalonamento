package escalonamento;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Escalonamento {

    public static void main(String[] args) {
        //Scanner, tamanho, fila , random
        Scanner sc = new Scanner(System.in);
        int n , opc;
        ArrayList<Processos> fila = new ArrayList<>();
        Random gerador = new Random();

        //tamanho da quantidade de processos
        System.out.println("Digite a quantidade de processos: ");
        n = sc.nextInt();
        
        //gera os processos na fila.
        if(n > 0 && n < 25){
            for (int i = 0; i < n; i++) {
                Processos po = new Processos();
                fila.add(po);
                fila.get(i).id = i;
            }
        }
        else{
            System.out.println("Tamanho invalido!");
        }
                
        //aleatorizar tempo de execução ou não
        System.out.println("Digite 1 para aleatorizar tempo de execução/tempo de chegada e 2 para inserir manualmente");
        opc = sc.nextInt();
        if(opc == 1){
            for (int i = 0; i < fila.size(); i++) {
                fila.get(i).tempo_execucao = gerador.nextInt(16) + 1;
                fila.get(i).tempo_restante = fila.get(i).tempo_execucao;
                if(i >= 1){
                fila.get(i).tempo_chegada = i + (i + gerador.nextInt(2));
                }
            }
        }
        else if(opc == 2){
            for (int i = 0; i < fila.size(); i++) {
                //tempo de execução
                System.out.println("Digite o valor do processo " + fila.get(i).id);
                fila.get(i).tempo_execucao = sc.nextInt();
                fila.get(i).tempo_restante = fila.get(i).tempo_execucao;
                //obriga o primeiro processo a ter tempo 0
                if(i == 0){
                    fila.get(i).tempo_chegada = 0;
                }
                //libera para preencher o tempo de chegada
                else{
                System.out.println("Digite o tempo de chegada do processo " + fila.get(i).id);
                fila.get(i).tempo_chegada = sc.nextInt();
                }
            }
        }
        //opção errada.
        else{
            System.out.println("Valor invalido");
        }
        
        Processador cpu = new Processador();
        cpu.fila = fila;
        cpu.imprimirInfos(fila);
        cpu.processar(0);
    

    }

}


