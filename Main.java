/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rolepalyinggame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import rolepalyinggame.character.GameCharacter;

/**
 *
 * @author stigge
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rnd = new Random(42);
        
        List<Integer> family = new ArrayList<>(), oneRace = new ArrayList<>(), familyMods = new ArrayList<>(), oneMod = new ArrayList<>();
        int cls, familySize=72;

        for (int i = 0; i < familySize; i++) {            
            family.add(rnd.nextInt(9)+1);
            familyMods.add(rnd.nextInt(5));
        }
        for(int ancestor : family) {
            System.out.print(ancestor + " ");
        }
        System.out.println();
        for(int ancestor : familyMods) {
            System.out.print(ancestor + " ");
        }
        System.out.println();
        
        oneRace.add(rnd.nextInt(3)+1);
        oneMod.add(rnd.nextInt(3)+1);
        
        GameCharacter gc1 = new GameCharacter(true, oneRace, oneMod);
        GameCharacter gc2 = new GameCharacter(false, family, familyMods);
        
        for (int i = 0; i < 15; i++) {
            cls = rnd.nextInt(5);
            gc1.increaseLevel(cls);
            cls = rnd.nextInt(5);
            gc2.increaseLevel(cls);

            System.out.println(gc1);
            System.out.println(gc2);
        }
    }    
}
