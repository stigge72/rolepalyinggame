/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rolepalyinggame.character;

import java.util.List;

/**
 *
 * @author stigge
 */
public class GameCharacter {
    private Career myCareer;
    private Ancestry myAncestry;
    
    private int level;
    
    public GameCharacter(boolean pure, List<Integer> ancestors, List<Integer> modifiers) {
        System.out.println("rolepalyinggame.character.GameCharacter.<init>(): pure: " + pure + ", ancestors: " + ancestors.size() + ", modifiers: " + modifiers.size());
        myCareer = new Career();
        myAncestry = Ancestry.createAncestry(pure, ancestors, modifiers);
    }
    
    public void increaseLevel(int theClass) {
        myCareer.increaseLevel(theClass);
    }
    
    @Override
    public String toString() {
        return myAncestry.toString() + " " + myCareer.toString();
    }
}
