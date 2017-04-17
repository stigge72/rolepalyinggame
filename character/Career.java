/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rolepalyinggame.character;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stigge
 */
class Career {
    private List<CharacterClass> myClasses;
    
    public Career() {
        myClasses = new ArrayList<>();
    }
    
    public void increaseLevel(int theClass) {
        for (CharacterClass iClass : myClasses) {
            if(iClass.getClassNumber()==theClass) {
                iClass.increaseLevel();
                return;
            }
        }
        try {
            myClasses.add(new CharacterClass(theClass));
        }
        catch(CharacterException e) {
            System.err.println("rolepalyinggame.character.Career.increaseLevel(): " + e.getMessage());
        }
    }
    
    public int level() {
        int lvl = 0;
        lvl = myClasses.stream().map((iClass) -> iClass.getLevel()).reduce(lvl, Integer::sum);
        
        return lvl;
    }

    @Override
    public String toString() {
        String str = "";
        boolean first = true;
        
        for (CharacterClass iClass : myClasses) {
            if(first){
                first = false;
            } else {
                str += ", ";
            }
            str += iClass.toString();
        }
        return str;
    }
    
    
}
