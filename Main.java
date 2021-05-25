import java.util.ArrayList;
import java.awt.Font;
import java.lang.Math;
import MG2D.*;
import MG2D.geometrie.*;

public class Main {
    public static void main ( String [] args ){
        int height = 800;
        int width = 1200;
        Clavier keyboard;
        Souris mouse;
        boolean menu = true;

        ArrayList<Point> listePointHori1 = new ArrayList<Point>();
        ArrayList<Point> listePointHori2 = new ArrayList<Point>();
        ArrayList<Point> listePointVert1 = new ArrayList<Point>();
        ArrayList<Point> listePointVert2 = new ArrayList<Point>();
        ArrayList<Ligne> listeLignesHori = new ArrayList<Ligne>();
        ArrayList<Ligne> listeLignesVert = new ArrayList<Ligne>();

    // Création des joueurs et du jeu
        Joueur j1 = new Joueur();
        j1.setName();
        Joueur j2 = new Joueur();
        j2.setName();
        Game g = new Game(j1, j2);
        
/************* Interface de menu *************/
        Fenetre f = new Fenetre("Pente", width, height);
        keyboard = f.getClavier();
        mouse = f.getSouris();

        int middle = width/2;
        Point a = new Point(middle - 250,100);
        Font textFont = new Font("Arial", Font.BOLD,20);
        Font textFont50 = new Font("Arial", Font.BOLD,50);
        // Background
        Texture game = new Texture("img/test.png",new Point(0,0));
        f.ajouter(game);

        Texte gameName = new Texte(new String("Jeu de Pente"), textFont, new Point(middle,780));
        f.ajouter(gameName);
        Texte creators = new Texte(new String("Jeu developpe par Maud, Maxime, Laurine, Florian"), textFont, new Point(middle,20));
        f.ajouter(creators);
        Rectangle startButton = new Rectangle (new Couleur (180, 16, 82), a, 500, 50, 3, 3, true);
        f.ajouter(startButton);
        Texte howtoStart = new Texte(new String("Appuyez sur Entree pour demarrer"), textFont, new Point(middle,125));
        f.ajouter(howtoStart);
        Texte j1Menu = new Texte(new String("Joueur 1 : ") + j1.getName(), textFont50, new Point(middle,500));
        f.ajouter(j1Menu);
        Texte j2Menu = new Texte(new String("Joueur 2 : ") + j2.getName(), textFont50, new Point(middle,300));
        f.ajouter(j2Menu);
        f.rafraichir();
        // Passage à l'interface de jeu quand entrée est appuyé
        while(menu) {
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
        int interfaceHeight = 720;
        int interfaceWidth = 720;
        int widthMargin = (width-interfaceWidth)/2;
        int heightMargin = (height-interfaceHeight)/2;
        Point topRightCorner = new Point(interfaceWidth+widthMargin, interfaceHeight+heightMargin);
        Point bottomLeftCorner = new Point(widthMargin, heightMargin);
        Rectangle background = new Rectangle (new Couleur (255,255,255), bottomLeftCorner, topRightCorner, true);
        f.ajouter(background);
        // Création lignes horizontales
        for(int x = widthMargin;x<=topRightCorner.getX();x=x+40){
            listePointVert1.add(new Point(x,bottomLeftCorner.getY()));
            listePointVert2.add(new Point(x,topRightCorner.getY()));
        }
        for(int i = 0;i<listePointVert1.size();i++){
            listeLignesHori.add(new Ligne(listePointVert1.get(i),listePointVert2.get(i)));
        }
        for(int i=0;i<listeLignesHori.size();i++){
            f.ajouter(listeLignesHori.get(i));
        }

        // Création lignes verticales
        for(int y = heightMargin;y<=topRightCorner.getY();y=y+40){
            listePointHori1.add(new Point(bottomLeftCorner.getX(),y));
            listePointHori2.add(new Point(topRightCorner.getX(),y));
        }
        for(int i = 0;i<listePointHori1.size();i++){
            listeLignesVert.add(new Ligne(listePointHori1.get(i),listePointHori2.get(i)));
        }
        for(int i=0;i<listeLignesVert.size();i++){
            f.ajouter(listeLignesVert.get(i));
        }

        // Désigne le joueur qui commence
        String playername;
        if (g.player == 0) {
            playername = j1.getName();
        } else {
            playername = j2.getName();
        }
        Texte whoPlays = new Texte(new String("Le joueur " + (g.player + 1) + " " + playername + " joue"), textFont, new Point(middle,20));
        f.ajouter(whoPlays);
        f.rafraichir();

        // Dessine un pion en fonction du clic de la souris
        /*boolean play = false;
        while(!play) {
            if (mouse.getClicGauche()) {
                Point mousePosition = mouse.getPosition();
                int xIntersection = Math.round(mousePosition.getX() / 40);
                int yIntersection = Math.round(mousePosition.getY() / 40);
                System.out.println(xIntersection);
                System.out.println(yIntersection);
                System.out.println(mousePosition);
                Point intersection = new Point(xIntersection, yIntersection);
                System.out.println(intersection);
                Cercle pion = new Cercle(Couleur.BLEU, intersection, 5, true);
                f.ajouter(pion);
                play = true;
            }
            f.rafraichir();
            try {
                Thread.sleep(1);
            } catch (Exception e) {

                }
        }*/

          /* compteur de test */
          int i=0;
          /* changer la condition du while si la var était win existe */
          while(i<=40) {
              if (mouse.getClicGauche()) {
                  Point mousePosition = mouse.getPosition();
                  int xIntersection = Math.round((mousePosition.getX()+20)/40)*40;
                  int yIntersection = Math.round((mousePosition.getY()+20)/40)*40;
                  System.out.println(xIntersection);
                  System.out.println(yIntersection);
                  System.out.println(mousePosition);
                  Point intersection = new Point(xIntersection, yIntersection);
                  System.out.println(intersection);
                  Cercle pion = new Cercle(Couleur.BLEU, intersection, 5, true);
                  f.ajouter(pion);
                  i=i+1;
              }
              f.rafraichir();
              try {
                  Thread.sleep(1);
              } catch (Exception e) {
  
                  }
          }
    }
}