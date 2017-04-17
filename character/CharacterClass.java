/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rolepalyinggame.character;

/**
 *
 * @author stigge
 */
class CharacterClass {
    private final static int BARD_CLASS=0;
    private final static int WARRIOR_CLASS=1;
    private final static int MAGICIAN_CLASS=2;
    private final static int THIEF_CLASS=3;
    private final static int MONK_CLASS=4;
    
    private final static int FIGHTING_GROUP=0;
    private final static int RELIGIOUS_GROUP=1;
    private final static int THIEVING_GROUP=2;
    private final static int MAGICAL_GROUP=3;
    private final static int MUSICAL_GROUP=4;

    private final static String[] GROUPS = {"Fighting", "Religious", "Thieving", "Magical", "Musical"};
    private String name;
    private int classNumber;
    private int level;
    private int group;
    
    public CharacterClass(int theClass) throws CharacterException {
        switch(theClass) {
            case BARD_CLASS : name = "Bard"; group = MUSICAL_GROUP; break;
            case WARRIOR_CLASS : name = "Warrior"; group = FIGHTING_GROUP; break;
            case MAGICIAN_CLASS : name = "Magician"; group = MAGICAL_GROUP; break;
            case THIEF_CLASS : name = "Thief"; group = THIEVING_GROUP; break;
            case MONK_CLASS : name = "Monk"; group = RELIGIOUS_GROUP; break;
            default: throw new CharacterException("No such class");
        }
        
        classNumber=theClass;
        level=1;
        System.out.println("New class: " + name);
    }
    
    public void increaseLevel() {
        ++level;
        System.out.println("New level: " + level);
    }
    
    public int getClassNumber(){
        return classNumber;
    }
    
    public int getLevel() {
        return level;
    }
    
    @Override
    public String toString() {        
        return name + " (" + GROUPS[group] + ") " + level;
    }
}
