import java.util.Random;
/** Classe Game qui gère le fonctionnement du jeu */

public class Game {
/************* Attributs *************/
    Joueur j1;
    Joueur j2;
    int player = -1;
    int nextplayer = -1;
    int[][] plateauJeu = new int [19][19];

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
     * Initialise le plateau de jeu de début de partie
     * @return plateau, tableau statique
     */
    public int[][] setPlateauJeu(int x, int y) {
        for (int i = 0;i < x;i++) {
            for (int j = 0;j < y;j++) {
                this.plateauJeu[i][j] = 0;
            }
        }
        return this.plateauJeu;
    }

    /**
     * Affiche le plateau de jeu
     */
    public void showPlateauJeu() {
        for (int i = 0;i < this.plateauJeu.length; i++) {
            for (int j = 0;j < this.plateauJeu[i].length;j++) {
                System.out.print(this.plateauJeu[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     * Vérifie si 5 pions pareils sont alignés verticalement
     * @param cases_x, la ligne horizontal où le joueur a placé son pion
     * @return booléen, true si 5 pions, false sinon
     */
    public boolean verifVertical(int cases_x) {
        Integer count=0;
        if(this.player == 1){ // Pour le joueur 1
            for (int x = 0; x < this.plateauJeu.length; x++){
                int value = this.plateauJeu[x][cases_x];
                if (value == 1) {
                    count++;
                    if(count>=5){
                        return true;
                    }
                }else {
                    count=0;
                }
            }
        }
        else if(this.player == 2){ // Pour le joueur 2
            for (int x = 0; x < this.plateauJeu.length; x++){
                int value = this.plateauJeu[x][cases_x];
                if (value == 2) {
                    count++;
                    if(count>=5){
                        return true;
                    }
                } else {
                    count=0;
                }
            }
        }
        return false;
    }

    /**
     * Vérifie si 5 pions pareils sont alignés horizontalement
     * @param cases_y, la ligne verticale où le joueur a placé son pion
     * @return booléen, true si 5 pions, false sinon
     */
    public boolean verifHorizontal(int cases_y) {
        System.out.println(cases_y);
        Integer count=0;
        if(this.player == 1){ // Pour le joueur 1
            for (int x = 0; x < this.plateauJeu.length; x++){
                if(this.plateauJeu[cases_y][x] == 1){
                    count++;
                } else {
                    count=0;
                }
            }
        }
        else if(this.player == 2){ // Pour le joueur 2
            for (int x = 0; x  < this.plateauJeu.length; x++){
                if(this.plateauJeu[cases_y][x] == 2) {
                    count++;
                    if(count>=5){
                        return true;
                    }
                }else  {
                    count=0;
                }
            }
        }
        return false;
    }

    /**
     * Vérifie si 5 pions pareils sont alignés en diagonale de manière "/"
     * ( La méthode ne compte actuellement les pions 
     * dans la partie gauche haute de l'interface )
     * @return booléen, true si 5 pions, false sinon
     */
    public boolean verifRightDiagonal() {
        Integer count = 0;
        if(this.player == 1){
            for (int y = 0; y < this.plateauJeu.length; y++) {
                for (int x = 0; x < (this.plateauJeu.length - y); x ++) {
                    if (this.plateauJeu[y+x][x] == 1) {
                        count++;
                        if(count>=5){
                            return true;
                        }
                    } else {
                        count = 0;
                    }
                }
            }
        }
        if(this.player == 2){
            for (int y = 0; y < this.plateauJeu.length; y++) {
                for (int x = 0; x < (this.plateauJeu.length - y); x ++) {
                    if (this.plateauJeu[y+x][x] == 2) {
                        count++;
                        if(count>=5){
                            return true;
                        }
                    } else {
                        count = 0;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Désigne le joueur qui joue en premier
     * @return starter, int le numéro du joueur qui commence
     */
    public int whoStartFirst() {
        Random random = new Random();
        this.player = 1 + random.nextInt(3-1);
        return this.player;
    }

    /**
     * Ajoute le pion dans le tableau de jeu
     * @param xIntersection
     * @param yIntersection
     */
    public void addPionTab(int xIntersection, int yIntersection) {
        int x;
        int y;
        x = (xIntersection-240)/40;
        y = (yIntersection-40)/40;
        this.plateauJeu[y][x] = this.player + 1;
    }


    /**
     * Désigne le prochain joueur à jouer
     * @return player, int le numéro du joueur qui joue après
     */
    public int nextTurn() {
        if (this.player == 1) {
            this.player = 2;
            this.nextplayer = 1;
        } else {
            this.player = 1;
            this.nextplayer = 2;
        }
        return this.nextplayer;
    }
}