package escalonamento;

import java.util.ArrayList;

public class Processador {

    int tempo , aux ;
    float aux2;

    String temp = "Tempo em execução:    ";
    String rest = "Tempo restante Proc:   ";
    String proc = "Processo em execução: ";
    String procespera = "Tempo de espera do processo: ";
    String procfila = "IDS dos Processos e seus tempos de execução: \n";

    public void processar(ArrayList<Processos> fila){
        for (int i = 0; i < fila.size(); i++) {
            procespera = procespera + "id"+ fila.get(i).id + " " + tempo + "s, ";
            procfila = procfila + "ID:" + fila.get(i).id + " Temp. de Exe.: " + fila.get(i).tempo_execucao + "s | ";
            aux2 = aux2 + (tempo);

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
                fila.get(i).tempo_restante--;
                tempo++;
            }


        }
        
        
        
       
       System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(procfila +"\n\n\n\n");
       System.out.println("Resultado;\n" + temp);
       System.out.println(rest);
       System.out.println(proc);
        
        aux2 = aux2 / fila.size();
        System.out.println(procespera);
        System.out.printf("Tempo médio de espera: %.2f ", aux2);
       


    }

}

    

    
    
    
    
    

