import java.util.LinkedList;

/**
 *
 * @author Bianca Amorim
 */

// Apenas criação e instânciamento dos processos

public class Main {
    
    public static void main(String[] args) {
        
        LinkedList<Processos> processo1 = new LinkedList<>();
        LinkedList<Processos> processo2 = new LinkedList<>();
        LinkedList<Processos> processo3 = new LinkedList<>();        
        
        processo1.add(new Processos(0,20));
        processo1.add(new Processos(1,10));
        processo1.add(new Processos(7,5));
        processo1.add(new Processos(4,6));
        processo1.add(new Processos(4,8));
        
        processo2.add(new Processos(0,20));
        processo2.add(new Processos(1,10));
        processo2.add(new Processos(7,5));
        processo2.add(new Processos(4,6));
        processo2.add(new Processos(4,8));
        
        processo3.add(new Processos(0,20));
        processo3.add(new Processos(1,10));
        processo3.add(new Processos(7,5));
        processo3.add(new Processos(4,6));
        processo3.add(new Processos(4,8));
        
        FCFS fcfs = new FCFS(processo1);
        System.out.println(fcfs.escalonar());
        
        SJF sjf = new SJF(processo2);
        System.out.println(sjf.escalonar());
        
        RR rr = new RR(processo3, 2); // Segundo parametro é o quantum       
        System.out.println(rr.escalonar());    

    }   
}
