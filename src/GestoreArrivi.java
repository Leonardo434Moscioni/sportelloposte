/**
 * Classe che implementa il thread per il totem touch screen che aggiunge
 * i clienti alla lista di attesa e genera il numero di attesa
 * rappresenta il produttore
 * @author frida
 * @version 1.0
 */
public class GestoreArrivi implements Runnable {

    /* variabili d'istanza sono;
     * la risorsa condivisa listaClienti
     * e la costante per il numero massimo di arrivi */
    private ListaClienti listaClienti;

    private final int attesaArrivi = 2000;
    private int IDtotem;
    /**
     * constructor
     * @param listaClienti
     */
    public GestoreArrivi(ListaClienti listaClienti, int IDtotem) {
        this.IDtotem = IDtotem;
        this.listaClienti = listaClienti;
    }
    /**
     * Su addCliente all 11 cliente restituisce NULL, il break dopo
     * mi fa uscire dallo while ed a quel punto viene sempre eseguito il finally.
     * @see Runnable
     */
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(attesaArrivi);
                //tempo di servizio variabile
                Integer clienteArrivato = listaClienti.addCliente();
                if (clienteArrivato == null) {
                    break;
                }
                System.out.println("Arrivo Cliente Numero\t" + clienteArrivato + "\tdal totem\t" + IDtotem);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrotto durante lo sleep");
        } finally {
            System.out.println("Posta Chiusa lato totem\t" + IDtotem);
        }
    }
}
