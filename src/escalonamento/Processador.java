package escalonamento;

import java.util.ArrayList;

public class Processador {

    int tempo , aux , aux3 ;
    float aux2;
    int salvar;
    ArrayList<Processos> fila;

    
    String temp = "Tempo em execução:    ";
    String rest = "Tempo restante Proc:   ";
    String proc = "Processo em execução: ";
    String procespera = "Tempo de espera do processo: ";
    String procfila = "IDS dos Processos e seus tempos de execução: \n";

    public void processar(int p){
        for (int i = p; i < fila.size(); i++) {

            procespera = procespera + "id"+ fila.get(i).id + " " + tempo + "s, ";
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
                     
                       System.out.println("\n\n");
                       System.out.println(procfila +"\n\n\n");
                       System.out.println("=======Resultado======\n" + temp);
                       System.out.println(rest);
                       System.out.println(proc);
                       System.out.println("==" + i);

                        aux2 = aux2 / fila.size();
                        System.out.println(procespera);
                        System.out.printf("Tempo médio de espera: %.2f ", aux2);
                
                
                //reduz o tempo por ter sido processado
                fila.get(i).tempo_restante--;
                //incrementa o tempo
                tempo++;
                //
                //verifica se o tempo restante é igual a 0, remove da fila, e sai do laço interno
                if(fila.get(i).tempo_restante == 0){
                    fila.remove(i);
                    break;
                }
                aux3 = checkFila(tempo, i, fila.get(i));
                if( aux3 != i){
                    processar(aux3);
                    
                }
            }
            

            
        }
       System.out.println("\n\n");
       System.out.println(procfila +"\n\n\n");
       System.out.println("=======Resultado======\n" + temp);
       System.out.println(rest);
       System.out.println(proc);
       
       aux2 = aux2 / fila.size();
       System.out.println(procespera);
       System.out.printf("Tempo médio de espera: %.2f ", aux2);
       
    }
    
    
        
    public void imprimirInfos(ArrayList<Processos> fila){
        for (int i = 0; i < fila.size(); i++) {
         procfila = procfila + "ID:" + fila.get(i).id + " Temp. de Exe.: " 
         + fila.get(i).tempo_execucao + "s  Tempo de Chegada:" + fila.get(i).tempo_chegada + "\n ";     
        }
    }

    public int checkFila(int tempo, int id, Processos atual){
        for (int i = 0; i < fila.size(); i++) {   
            //verifica se o tempo de chegada bate no tempo atual;
            if(fila.get(i).tempo_chegada == tempo){
                //verifica se o tempo do processo e menor que o atual;
                if(fila.get(i).tempo_restante < atual.tempo_restante){
                    //retorna o ID dele;
                    return fila.get(i).id;
                }
                //continua o id atual
                else{
                    return id;
                }
                          
            }
        }
        return id;
    }
    
    
    public ArrayList<Processos> organizarSjfNao(ArrayList<Processos> fila){
     Processos aux1;
        for (int i = 1; i < fila.size(); i++) {
            for (int j = 1; j < fila.size(); j++) {
                if (fila.get(i).tempo_execucao < fila.get(j).tempo_execucao) {
                    aux1 = fila.get(i);
                    fila.set(i, fila.get(j));
                    fila.set(j, aux1);
                   }
              
        
                }
                
            }
        
        return fila;
    }   
}

    

    
    
    
    
    

