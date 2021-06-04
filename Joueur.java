/** Classe joueur modélisant un joueur */

import java.util.Scanner;

public class Joueur {
/************* Attributs *************/
    String name;
    String color;
    Scanner scanner = new Scanner (System.in);
    boolean active;
    boolean win;
    int pairCaptured;
    int nombrePions;

/************* Constructeurs *************/
    // Constructeur par défaut
    public Joueur() {
        this.name = "Joueur";
        this.color = "NOIR";
        this.active = false;
        this.win =false;
        this.pairCaptured = 0;
        this.nombrePions = 0;
    }

/************* Setteurs *************/
    /**
     * Assigne un nom à l'attribut nom par la saisie au clavier
     * (utilisation de Scanner)
     * @return this.name, attribut nom de type String
     */
    public void setName(){
        System.out.println("Entrez le nom du joueur");
        this.name = scanner.nextLine();
    }

    /**
     * Assigne une couleur à l'attribut couleur 
     * @return this.color, attribut de type String
     */
    public void setColor() {
        System.out.println("Entrez la couleur du joueur : ");
        this.color = scanner.nextLine();
        this.color = this.color.toUpperCase();
    }

/************* Getteurs *************/
    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public int getPairCaptured() {
        return this.pairCaptured;
    }

    public int getNombrePions() {
        return this.nombrePions;
    }

/************* Méthodes *************/
    /**
     * Permet au joueur de jouer
     * @return win, boolean
     */
    public boolean getActive() {
        this.active = true;
        return this.active;
    }

    /**
     * Ajoute un pion au compte de pions du joueur
     * @return nombrePions, le nombre de pions que possède le joueur
     */
    public int oneMorePion() {
        this.nombrePions = getNombrePions() + 1;
        return this.nombrePions;
    }

    /**
     * Affiche les caractéristiques de l'objet Joueur
     */
    public String toString() {
        return "Joueur : " + this.name + ", " + this.color + ", " + this.active + ", " + this.win + ", " + this.pairCaptured;
    }
}

