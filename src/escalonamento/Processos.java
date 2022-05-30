package escalonamento;

public class Processos {

        int id;
        int tempo_espera;
        int tempo_execucao;
        int tempo_restante;
        int tempo_chegada;
        int prioridade;

        public Processos(){
            
        }
        
        public Processos(int id, int tempo_espera, int tempo_execucao, int tempo_restante) {
        this.id = id;
        this.tempo_espera = tempo_espera;
        this.tempo_execucao = tempo_execucao;
        this.tempo_restante = tempo_restante;
        this.tempo_chegada = tempo_chegada;
        }


        
        
        


}