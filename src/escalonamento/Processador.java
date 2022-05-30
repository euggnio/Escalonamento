package escalonamento;

import java.util.ArrayList;

    public class Processador {

        ArrayList<Processos> fila;
        int auxiliar = 1000;
        int tempo;
        int pos = 0;
        int aux4;



        String temp = "Tempo em execução:    ";
        String rest = "Tempo restante Proc:  ";
        String proc = "Processo em execução: ";
        String procespera = "Tempo de espera do processo: ";
        String procfila = "\n IDS dos Processos e seus tempos de execução: \n";

        public void processar(int tipo){
            //adiciona as informações dos processos a uma string
            for (int i = 0; i < fila.size(); i++) {
                procfila = procfila + "ID: " + fila.get(i).id + " | Prioridade: " + fila.get(i).prioridade+" | Tempo Exec.: " + fila.get(i).tempo_execucao + " | Temp Chegada: " + fila.get(i).tempo_chegada + "\n";
            }
            
            //processos terminados.
            int aux5 = 0;
            //for premptivo sjf e não premptivo
            for (int i = 0; i < auxiliar; i++) {
                //verificar tempo de chegada para SJF premptivo
                // se for premptivo, senão, passa direto.
                if(tipo == 1){
                pos = checkTempoChegada(pos, tempo);
                }
                
                //quando acaba um processo
                if(fila.get(pos).tempo_restante == 0){
                
                //verificar menor tempo restante para SJF premptivo e nao premptivo
                //senão, verifica apenas tempo de chegada para FIFO e PRIORITARIO.
                    if(tipo == 1 || tipo == 2){
                        aux4 = checkTempoMenor(pos,tempo, fila.get(pos));
                     }
                    else{
                        aux4 = checkTempoChegada(pos, tempo);
                    }
                    
                    if(aux4 != pos){
                        aux5 ++;
                        pos = aux4;
                    }
                    else{
                        aux5 ++;
                        pos++;
                    }
                //não exceder tamanho da fila
                if(aux5 == fila.size()){
                    break;
                }
                }
                //fim SJF premptivo e não premptivo
                
                
                //fila vazia ==descontinuado==
                if(fila.isEmpty()){
                    break;
                }
                
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
                
                imprimir(tempo,fila.get(pos).tempo_restante,fila.get(pos).id);

                System.out.println(temp);
                System.out.println(rest);
                System.out.println(proc);
                System.out.println(procfila);

                tempo++;
                fila.get(pos).tempo_restante--;
                
                System.out.println("MAIOR PRIORIDADE: " + calcularPrioridade(pos));

            }
        }
        
        public void imprimir(int tempo, int restante, int id){
            if (tempo > 9) {
                temp = temp + " | " + tempo;
            } else {
                temp = temp + " |  " + tempo;
            }
            //temp rest
            if (restante > 9) {
                rest = rest + " | " + fila.get(pos).tempo_restante;
            } else {
                rest = rest + " |  " + fila.get(pos).tempo_restante;
            }
            //id proc
            if (id > 9) {
                proc = proc + " | " + fila.get(pos).id;
            } else {
                proc = proc + " |  " + fila.get(pos).id;
            }
        }

        public void calcularMedia(){
            
        }

        private int checkTempoChegada(int pos, int tempo) {
            for (int i = 0; i < fila.size(); i++) {
//se tiver entrado algo no ciclo
                if(fila.get(i).tempo_chegada == tempo){
//se for menor que o atual no ciclo
                    if(fila.get(i).tempo_restante < fila.get(pos).tempo_restante){
//retorna ele
                        System.out.println("CheckTempoTrue");
                        return fila.get(i).id;
                    }
                }
            }
//senão retorna normal;
            System.out.println("CheckTempoFalse");
            return pos;
        }
        
        private int calcularPrioridade(int pos){
            int aux = 6;
            for (int i = 0; i < fila.size(); i++) {
                
                for (int j = 0; j < fila.size(); j++) {
                    if(fila.get(j).prioridade == aux && fila.get(j).tempo_restante > 0){
                        return fila.get(i).id;
                    }
               }

                aux--;
                
                
            }
            return pos;
        }

        private int checkTempoMenor(int pos, int tempo, Processos aux2) {
            int aux = 10000;
            int aux3;

            for (int i = 0; i < fila.size(); i++) {
                aux3 = fila.get(i).tempo_chegada;
                if(fila.get(i).tempo_restante > 0){
                    if(fila.get(i).tempo_restante < aux){
                        if(aux3 < tempo){
                            aux = fila.get(i).tempo_restante;
                            aux2 = fila.get(i);
                            System.out.println("CheckTempoMenorTrue" + fila.get(i).id);
                            pos = fila.get(i).id;
                        }
                    }
                }
            }

            System.out.println("CheckTempoMenorFalse");
            return pos;
        }

        }

    
    
    
    

