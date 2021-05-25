/** Classe de test de Game */
public class GameTest {
    public static void main(String[] args){
        // Test des constructeurs
        Joueur j1 = new Joueur();
        j1.setName();
        System.out.println(j1);
        Joueur j2 = new Joueur();
        j2.setName();
        System.out.println(j2);
        Game g = new Game(j1, j2);

        // Test de la méthode whoStartFirst
        if (g.whoStartFirst() == 0) {
            System.out.println("C'est le joueur " + j1.getName() + " qui commence !");
        } else {
            System.out.println("C'est le joueur " + j2.getName() + " qui commence !");
        }

        // Test de la méthode whoPlayNext
        if (g.whoPlayNext() == 0) {
            System.out.println("C'est le joueur " + j1.getName() + " qui joue après !");
        } else {
            System.out.println("C'est le joueur " + j2.getName() + " qui joue après !");
        }
    }
}
