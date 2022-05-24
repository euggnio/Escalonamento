package escalonamento;

import java.util.ArrayList;

    public class Processador {

        ArrayList<Processos> fila;
        int auxiliar = 1000;
        int tempo;
        int pos = 0;



        String temp = "Tempo em execução:    ";
        String rest = "Tempo restante Proc:  ";
        String proc = "Processo em execução: ";
        String procespera = "Tempo de espera do processo: ";
        String procfila = "IDS dos Processos e seus tempos de execução: \n";

        public void processar(){
            int aux5 = 0;
            for (int i = 0; i < auxiliar; i++) {



                pos = checkTempoChegada(pos, tempo);

                if(fila.get(pos).tempo_restante == 0){
                    int aux4 = checkTempoMenor(pos,tempo, fila.get(pos));
                    if(aux4 != pos){
                        aux5 ++;
                        pos = aux4;
                    }
                    else{
                        aux5 ++;
                        pos++;

                    }

                if(aux5 == fila.size()){
                    break;
                }

                }
                if(fila.isEmpty()){
                    break;
                }


                System.out.println("\n\n\n\n\n\n\n\n\n\n");
                temp = temp + " | " + tempo;
                rest = rest + " | " + fila.get(pos).tempo_restante;
                proc = proc + " | " + fila.get(pos).id;
                System.out.println("ID: " + pos);

                System.out.println(temp);
                System.out.println(rest);
                System.out.println(proc);


                tempo++;
                fila.get(pos).tempo_restante--;

            }
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

    
    
    
    

