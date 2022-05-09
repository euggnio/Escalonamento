package escalonamento;

import java.util.ArrayList;

public class Processador {

    int tempo , aux, aux2;

    String temp = "Tempo em execução:    ";
    String rest = "Tempo restante Proc:   ";
    String proc = "Processo em execução: ";

    public void processar(ArrayList<Processos> fila){

        for (int i = 0; i < fila.size(); i++) {

            for (int j = 0; j < fila.get(i).tempo_execucao; j++) {
                if(tempo <=10) {
                    temp = temp + " |" + tempo;
                }
                else{
                    temp = temp + "|" + tempo;
                }
                if( fila.get(i).tempo_restante <10){
                    rest = rest + "| "+ fila.get(i).tempo_restante;
                }
                else{
                    rest = rest + "|" + fila.get(i).tempo_restante;
                }
                proc = proc +" |"+ fila.get(i).id;

                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println(temp);
                System.out.println(rest);
                System.out.println(proc);
                fila.get(i).tempo_restante--;
                tempo++;
            }

        }


    }

}
