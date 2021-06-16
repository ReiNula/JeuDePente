import java.util.ArrayList;
import java.awt.Font;
import java.lang.Math;
import MG2D.*;
import MG2D.geometrie.*;

public class Main {
    public static void main(String[] args) {
        int height = 800;
        int width = 1200;
        Clavier keyboard;
        Souris mouse;
        boolean menu = true;

        ArrayList < Point > listePointHori1 = new ArrayList < Point > ();
        ArrayList < Point > listePointHori2 = new ArrayList < Point > ();
        ArrayList < Point > listePointVert1 = new ArrayList < Point > ();
        ArrayList < Point > listePointVert2 = new ArrayList < Point > ();
        ArrayList < Ligne > listeLignesHori = new ArrayList < Ligne > ();
        ArrayList < Ligne > listeLignesVert = new ArrayList < Ligne > ();

        // Création des joueurs et du jeu
        Joueur j1 = new Joueur();
        j1.setName();
        j1.setColor();
        Joueur j2 = new Joueur();
        j2.setName();
        j2.setColor();
        Game g = new Game(j1, j2);

        /************* Interface de menu *************/
        Fenetre f = new Fenetre("Pente", width, height);
        keyboard = f.getClavier();
        mouse = f.getSouris();

        int middle = width / 2;
        Point a = new Point(middle - 250, 100);
        Font textFont = new Font("Arial", Font.BOLD, 20);
        Font textFont50 = new Font("Arial", Font.BOLD, 50);
        // Background
        Texture game = new Texture("img/test.jpg", new Point(0, 0));
        f.ajouter(game);

        // Ajout Texte
        Texte gameName = new Texte(new String("Jeu de Pente"), textFont, new Point(middle, 780));
        f.ajouter(gameName);
        Texte creators = new Texte(new String("Jeu developpe par Maud, Maxime, Laurine, Florian"), textFont, new Point(middle, 20));
        f.ajouter(creators);
        Rectangle startButton = new Rectangle(new Couleur(180, 16, 82), a, 500, 50, 3, 3, true);
        f.ajouter(startButton);
        Texte howtoStart = new Texte(new String("Appuyez sur Entree pour demarrer"), textFont, new Point(middle, 125));
        f.ajouter(howtoStart);
        Texte j1Menu = new Texte(new String("Joueur 1 : ") + j1.getName(), textFont50, new Point(middle, 500));
        f.ajouter(j1Menu);
        Texte j2Menu = new Texte(new String("Joueur 2 : ") + j2.getName(), textFont50, new Point(middle, 300));
        f.ajouter(j2Menu);
        f.rafraichir();

        // Passage à l'interface de jeu quand entrée est appuyé
        while (menu) {
            if (keyboard.getEntreeTape()) {
                f.supprimer(startButton);
                f.supprimer(howtoStart);
                f.supprimer(creators);
                f.supprimer(j1Menu);
                f.supprimer(j2Menu);
                g.whoStartFirst();
                menu = false;
            }
            f.rafraichir();
            try {
                Thread.sleep(1);
            } catch (Exception e) {

            }
        }


        /************* Interface de jeu *************/
        // Génération du tableau du damier de jeu
        g.setPlateauJeu(19, 19);
        boolean win = false;

        int interfaceHeight = 720;
        int interfaceWidth = 720;
        int widthMargin = (width - interfaceWidth) / 2;
        int heightMargin = (height - interfaceHeight) / 2;
        Point topRightCorner = new Point(interfaceWidth + widthMargin, interfaceHeight + heightMargin);
        Point bottomLeftCorner = new Point(widthMargin, heightMargin);
        Rectangle background = new Rectangle(new Couleur(255, 255, 255), bottomLeftCorner, topRightCorner, true);
        f.ajouter(background);
        // Création lignes horizontales
        for (int x = widthMargin; x <= topRightCorner.getX(); x = x + 40) {
            listePointVert1.add(new Point(x, bottomLeftCorner.getY()));
            listePointVert2.add(new Point(x, topRightCorner.getY()));
        }
        for (int i = 0; i < listePointVert1.size(); i++) {
            listeLignesHori.add(new Ligne(listePointVert1.get(i), listePointVert2.get(i)));
        }
        for (int i = 0; i < listeLignesHori.size(); i++) {
            f.ajouter(listeLignesHori.get(i));
        }

        // Création lignes verticales
        for (int y = heightMargin; y <= topRightCorner.getY(); y = y + 40) {
            listePointHori1.add(new Point(bottomLeftCorner.getX(), y));
            listePointHori2.add(new Point(topRightCorner.getX(), y));
        }
        for (int i = 0; i < listePointHori1.size(); i++) {
            listeLignesVert.add(new Ligne(listePointHori1.get(i), listePointHori2.get(i)));
        }
        for (int i = 0; i < listeLignesVert.size(); i++) {
            f.ajouter(listeLignesVert.get(i));
        }

        // Désigne le joueur qui commence et celui qui joue après
        String playername;
        String nextplayername;
        if (g.player == 1) {
            playername = j1.getName();
            nextplayername = j2.getName();
            g.nextplayer = 2;
        } else {
            playername = j2.getName();
            nextplayername = j1.getName();
            g.nextplayer = 1;
        }

        // Affichage au 1er tour des joueurs
        Texte whoPlays = new Texte(new String("Le joueur " + (g.player) + " " + playername + " joue et le joueur " + (g.nextplayer) + " " + nextplayername + " joue au prochain tour"), textFont, new Point(middle, 20));
        f.ajouter(whoPlays);

        // Affichage des pions que possède les joueurs
        Texte pionsJ1 = new Texte(new String("J1 : " + j1.getName()+ " a "+ j1.getNombrePions() + " pions "), textFont, new Point(110,600));
        Texte pionsJ2 = new Texte(new String("J2 : " +j2.getName()+" a "+ j2.getNombrePions() + " pions "), textFont, new Point(1100,600));;
        f.ajouter(pionsJ1);
        f.ajouter(pionsJ2);
        f.rafraichir();

        // Tour d'un joueur
        while (!win) {
            if (mouse.getClicGauche()) {
                Point mousePosition = mouse.getPosition();
                int xIntersection = Math.round((mousePosition.getX() + 20) / 40) * 40;
                int yIntersection = Math.round((mousePosition.getY() + 20) / 40) * 40;
                int cases_x = (xIntersection - 240) / 40;
                int cases_y = (yIntersection - 40) / 40;
                Point intersection = new Point(xIntersection, yIntersection);
                // Ajoute un pion de la couleur du Joueur et vérifie la victoire
                if (g.plateauJeu[cases_y][cases_x] == 0) { // S'il n'y a pas de pion déjà présent
                    if (g.player == 1) {
                        g.plateauJeu[cases_y][cases_x] = 1;
                        Cercle pion = new Cercle(j1.getColor(), intersection, 10, true);
                        f.ajouter(pion);
                        j1.oneMorePion();
                        if (g.verifVertical(cases_x)) {
                            System.out.println("Win J1 !");
                            win = true;
                        } else if (g.verifHorizontal(cases_y)) {
                            System.out.println("Win J1 !");
                            win = true;
                        } else if (g.verifRightDiagonal()) {
                            System.out.println("Win J1 !");
                            win = true;
                        }
                        g.player = 2;
                        g.nextplayer = 1;
                    } else if (g.player == 2) {
                        g.plateauJeu[cases_y][cases_x] = 2;
                        Cercle pion2 = new Cercle(j2.getColor(), intersection, 10, true);
                        f.ajouter(pion2);
                        j2.oneMorePion();
                        if (g.verifVertical(cases_x)) {
                            System.out.println("Win J2 !");
                            win = true;
                        } else if (g.verifHorizontal(cases_y)) {
                            System.out.println("Win J2 !");
                            win = true;
                        } else if (g.verifRightDiagonal()) {
                            System.out.println("Win J2 !");
                            win = true;
                        }
                        g.player = 1;
                        g.nextplayer = 2;
                    }
                    // Affichage plateau de Jeu sur console
                    g.showPlateauJeu();
                    System.out.println("case en abscisse :" + (cases_x + 1));
                    System.out.println("case en ordonné :" + (cases_y + 1));
                    // Changement sur l'interface de qui joue
                    String change = playername;
                    playername = nextplayername;
                    nextplayername = change;
                    f.supprimer(whoPlays);
                    f.supprimer(pionsJ1);
                    f.supprimer(pionsJ2);
                    whoPlays = new Texte(new String("Le joueur " + (g.player) + " " + playername + " joue et le joueur " + (g.nextplayer) + " " + nextplayername + " joue au prochain tour"), textFont, new Point(middle, 20));
                    pionsJ1 = new Texte(new String("J1 : " + j1.getName()+ " a "+ j1.getNombrePions() + " pions "), textFont, new Point(110,600));
                    pionsJ2 = new Texte(new String("J2 : " +j2.getName()+" a "+ j2.getNombrePions() + " pions "), textFont, new Point(1100,600));;
                    f.ajouter(pionsJ1);
                    f.ajouter(pionsJ2);
                    f.ajouter(whoPlays);
                    f.rafraichir();

                    if (win) {

                    }
                } else {
                    System.out.println("Rejoue sur une case non prise !");
                }
            }
            try {
                Thread.sleep(1);
            } catch (Exception e) {}
        }

        /************* Interface de victoire *************/
        boolean menu2 = true;
        Texture game2 = new Texture("img/victoire.jpg", new Point(0, 0));
        f.ajouter(game2);
        while (menu2) {
            if (keyboard.getEntreeTape()) {
                f.effacer();
                f.ajouter(game2);
                menu2 = false;
                Texte winner = new Texte(new String("Le joueur gagnant : ") + j1.getName(), textFont50, new Point(middle, 500));
                f.ajouter(winner);
            }
            f.rafraichir();
            try {
                Thread.sleep(1);
            } catch (Exception e) {

            }

        }
    }
}