package escalonamento;

import java.util.ArrayList;

public class Processador {

    ArrayList<Processos> fila;

    final int ciclos = 1000;
    int tempo = 0;
    int pos = 0;
    int contagem;
    int tipo;
    double tempoMedioEspera;

    String temp = "Tempo em execução:    ";
    String rest = "Tempo restante Proc:  ";
    String proc = "Processo em execução: ";
    String procespera = "Tempo de espera do processo: ";
    String procfila = "\n IDS dos Processos e seus tempos de execução: \n";



    public void processar(int tipo) {
        //adiciona as informações dos processos a uma string
        for (int i = 0; i < fila.size(); i++) {
            procfila = procfila + "ID: " + fila.get(i).id + " | Prioridade: " + fila.get(i).prioridade + " | Tempo Exec.: " + fila.get(i).tempo_execucao + " | Temp Chegada: " + fila.get(i).tempo_chegada + "\n";
        }
        //processos terminados.
        int terminados = 0;
        int contar = 0;

        switch (tipo) {
            case 5:
                //round robin
                for (int i = 0; i < ciclos; i++) {
                    //verifica se  o processo terminou
                    //se sim, incrementa terminados, zera contar, pega proximo processo. e chama calculo de tempo de espera.
                    if (checarFimDoProcesso(pos)) {
                        terminados++;
                        contar = 0;
                        calcularMedia(tipo, tempo, pos);
                        pos = checarProximoProcesso(pos);
                    }
                    //se conta for igual contagem, zera a conta e passa a pos.
                    if (contar == contagem) {
                        contar = 0;
                        pos = checarProximoProcesso(pos);
                    }

                    //confere se a contagem de processos terminados bate com o número de processos
                    if (terminados == fila.size()) {
                        break;
                    }
                    //manda o resultado do ciclo para o metodo que ajusta as strings
                    ajustarResultado(tempo, fila.get(pos).tempo_restante, fila.get(pos).id);
                    //incrementos
                    tempo++;
                    contar++;
                    fila.get(pos).tempo_restante--;
                }
                break;
            //prioritario
            case 4:
                for (int i = 0; i < ciclos; i++) {
                    pos = checarTempoChegadaPrioritario(pos, tempo);

                    if (checarFimDoProcesso(pos)) {
                        calcularMedia(tipo, tempo, pos);
                        pos = checarProximoProcessoPrioritario(pos);
                        terminados++;
                    }
                    if (terminados == fila.size()) {
                        break;
                    }

                    ajustarResultado(tempo, fila.get(pos).tempo_restante, fila.get(pos).id);
                    tempo++;
                    fila.get(pos).tempo_restante--;
                }
                break;
            case 1:
            case 2:
            case 3:
                //fifo sjf e sjf premptivo
                for (int i = 0; i < ciclos; i++) {
                    if (tipo == 1) {
                        pos = checkTempoChegada(pos, tempo);
                    }
                    if (checarFimDoProcesso(pos)) {
                        calcularMedia(tipo, tempo, pos);
                        terminados++;
                        if (tipo == 1 || tipo == 2) {
                            pos = checarTempoMenor(pos, tempo);
                        } else {
                            pos = checarProximoProcesso(pos);
                        }
                    }
                    if (terminados == fila.size()) {
                        break;
                    }

                    ajustarResultado(tempo, fila.get(pos).tempo_restante, fila.get(pos).id);
                    tempo++;
                    fila.get(pos).tempo_restante--;
                }
                break;
        }
        imprimirResultado();
        System.out.println("TIPO DE ESC: " + tipo);
    }

    //ajusta o resultado do processo em strings a cada laço//
    public void ajustarResultado(int tempo, int restante, int id) {
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
    //fim ajustar resultado//

    public void imprimirResultado() {
        System.out.println(temp);
        System.out.println(rest);
        System.out.println(proc);
        System.out.println(procfila);
        System.out.println("Contagem de " + contagem + " em " + contagem);
        System.out.printf("Tempo médio de espera: %.2f ", tempoMedioEspera / fila.size());
    }

    //calcular tempo Médio de espera//
    public void calcularMedia(int tipo, int tempo, int pos) {
        //tempo médio para sjf, sjf premptivo, round robin
        if (tipo == 1 || tipo == 2 || tipo == 5) {
            tempoMedioEspera += (tempo - fila.get(pos).tempo_chegada - fila.get(pos).tempo_execucao);
        }
        //tempo médio para fifo
        if (tipo == 3) {
            tempoMedioEspera += tempo - fila.get(pos).tempo_execucao;
        }
    }
    //fim calcular tempo médio de espera//

    //checar tempo de chegada//
    private int checarTempoChegadaPrioritario(int pos, int tempo) {
        for (int i = pos; i < fila.size(); i++) {
            if (fila.get(i).tempo_chegada == tempo) {
                if (fila.get(i).prioridade > fila.get(pos).prioridade) {
                    System.out.println("CheckPrioridadeTrue");
                    return fila.get(i).id;
                }
            }
        }
        return pos;
    }

    private int checkTempoChegada(int pos, int tempo) {
        for (int i = 0; i < fila.size(); i++) {
            //calcular tempo para prioritario
            if (fila.get(i).tempo_chegada == tempo) {
                //callucar tempo de chegada para o sjf, e sjf premptivo.
                if (fila.get(i).tempo_restante < fila.get(pos).tempo_restante) {
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
    //fim checar tempo de chegada//

    //checar prioridade do processo//
    private int checarProximoProcessoPrioritario(int pos) {
        int nivelPrioridade = 6;
        for (int i = nivelPrioridade; i > 0; i--) {
            for (int j = 0; j < fila.size(); j++) {
                if (fila.get(j).prioridade == i && !checarFimDoProcesso(j)) {
                    return j;
                }
            }
        }
        return checarProximoProcesso(pos);
    }
    //fim calcular proximo prioritario   //

    //checar processo de menor tempo//
    private int checarTempoMenor(int pos, int tempo) {
        int aux = 10000;
        int menorTempo;

        for (int i = 0; i < fila.size(); i++) {
            menorTempo = fila.get(i).tempo_chegada;
            if (fila.get(i).tempo_restante > 0) {
                if (fila.get(i).tempo_restante < aux) {
                    if (menorTempo <= tempo) {
                        aux = fila.get(i).tempo_restante;
                        pos = fila.get(i).id;
                    }
                }
            }
        }
        return pos;
    }
    //fim checar processo de menor tempo//

    public int checarProximoProcesso(int pos) {
        int posRecebida = pos;
        for (int i = pos; i < ciclos; i++) {
            pos++;
            if (pos == fila.size()) {
                pos = 0;
            }
            if (pos == posRecebida) {
                return pos;
            }
            if (!checarFimDoProcesso(pos)) {
                return pos;
            }
        }
        return pos;
    }

    //checa se o processo chegou ao fim//
    public boolean checarFimDoProcesso(int pos) {
        return fila.get(pos).tempo_restante <= 0;
    }
}