import java.util.Random;
/** Classe Game qui gère le fonctionnement du jeu */

public class Game {
/************* Attributs *************/
    Joueur j1;
    Joueur j2;
    int player = -1;
    int nextplayer = -1;

/************* Constructeurs *************/
    // Constructeur par défaut
    public Game() {
        this.j1 = new Joueur();
        this.j2 = new Joueur();
    }

    // Constructeur par paramètre
    public Game(Joueur j1, Joueur j2) {
        this.j1 = j1;
        this.j2 = j2;
    }

/************* Méthodes *************/
    /**
     * Désigne le joueur qui joue en premier
     * @return starter, int le numéro du joueur qui commence
     */
    public int whoStartFirst() {
        Random random = new Random();
        this.player = random.nextInt(2);
        return this.player;
    }

    /**
     * Désigne le prochain joueur à jouer
     * @return player, int le numéro du joueur qui joue après
     */
    public int whoPlayNext() {
        if (this.player == 0) {
            this.nextplayer = 1;
        } else {
            this.nextplayer = 0;
        }
        return this.nextplayer;
    }
}
