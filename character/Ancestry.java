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
class Ancestry {

    private boolean pure;
    private String name;
    private Ancestry mother;
    private Ancestry father;
    private String modifier;

    private static final int BASRAS_RACE = 1;
    private static final int CENTAUR_RACE = 2;
    private static final int DEMON_RACE = 3;
    private static final int DRACONIAN_RACE = 4;
    private static final int DWARF_RACE = 5;
    private static final int ELF_RACE = 6;
    private static final int FAUN_RACE = 7;
    private static final int FELID_RACE = 8;
    private static final int HUMAN_RACE = 9;

    private static final int GENERIC_RACE_MODIFIER = 0;
    private static final int CAVE_RACE_MODIFIER = 1;
    private static final int FOREST_RACE_MODIFIER = 2;
    private static final int ASTRO_RACE_MODIFIER = 3;
    
    private Ancestry(int race, int modifier) throws CharacterException {
        pure = true;
        switch (race) {
            case HUMAN_RACE:
                name = "Human";
                break;
            case ELF_RACE:
                name = "Elf";
                break;
            case DWARF_RACE:
                name = "Dwarf";
                break;
            case BASRAS_RACE:
                name = "Basras";
                break;
            case DRACONIAN_RACE:
                name = "Draconian";
                break;
            case DEMON_RACE:
                name = "Demon";
                break;
            case CENTAUR_RACE:
                name = "Centaur";
                break;
            case FAUN_RACE:
                name = "Faun";
                break;
            case FELID_RACE:
                name = "Felid";
                break;
            default:
                throw new CharacterException("No such race");
        }
        switch (modifier) {
            case CAVE_RACE_MODIFIER:
                this.modifier = "Cave";
                break;
            case FOREST_RACE_MODIFIER:
                this.modifier = "Forest";
                break;
            case ASTRO_RACE_MODIFIER:
                this.modifier = "Astro";
                break;
            default:
                this.modifier = "";
        }
        System.out.println("Pure " + this.modifier + (this.modifier.length()>0?" ":"") + name);
    }

    private Ancestry(Ancestry mom, Ancestry dad) {
        pure = false;
        mother = mom;
        father = dad;

        System.out.println("Mixed: F: " + dad + ". M: " + mom);

        if(mom.isPure() && dad.isPure() && mom.equals(dad)){
            pure = true;
            name = mom.getName();
            modifier = mom.getModifier();
            mother = null;
            father = null;
            System.out.println(" -> Changed to pure " + modifier + " " + name);
        }
    }

    public static Ancestry createAncestry(boolean pure, List<Integer> ancestors, List<Integer> modifiers) {
        if (pure) {
            System.out.println("Creating pure ancestry");
            try {
                return new Ancestry(ancestors.get(0), modifiers.get(0));
            } catch (CharacterException e) {
                System.err.println("rolepalyinggame.character.Ancestry.createAncestry(): " + e.getMessage());
            }
        } else {
            System.out.println("Creating mixed ancestry");

            List<Integer> mom = new ArrayList<>();
            List<Integer> dad = new ArrayList<>();

            List<Integer> momMod = new ArrayList<>();
            List<Integer> dadMod = new ArrayList<>();

            boolean pureMom = false, pureDad = false;

            for (int i = 0; i < ancestors.size(); ++i) {
                System.out.println("Ancestor #" + i);
                if (ancestors.get(i) == 0) {
                    continue;
                } else {
                    if (i % 2 == 0) {
                        mom.add(ancestors.get(i));
                        System.out.print("Mom[" + mom.size() + "]= ");
                        if (modifiers.size() > i) {
                            momMod.add(modifiers.get(i));
                            System.out.print(modifiers.get(i));
                        } else {
                            momMod.add(0);
                            System.out.print(0);
                        }
                        System.out.println(" " + ancestors.get(i));
                    } else {
                        dad.add(ancestors.get(i));
                        System.out.print("Dad[" + dad.size() + "]= ");
                        if (modifiers.size() > i) {
                            dadMod.add(modifiers.get(i));
                            System.out.print(modifiers.get(i));
                        } else {
                            dadMod.add(0);
                            System.out.print(0);
                        }
                        System.out.println(" " + ancestors.get(i));
                    }
                }
            }
            if (mom.size() == 1) {
                pureMom = true;
                System.out.println("Mother is pure");
            }
            if (dad.size() == 1) {
                pureDad = true;
                System.out.println("Father is pure");
            }
            if(pureMom&&pureDad&&(mom.get(0).equals(dad.get(0)))&&momMod.get(0).equals(dadMod.get(0))) {
                try{
                    System.out.println("Mom and dad are the same.");
                    return new Ancestry(mom.get(0),momMod.get(0));                   
                } catch(CharacterException e) {
                    System.err.println("rolepalyinggame.character.Ancestry.createAncestry(same parents): " + e.getMessage());                    
                }
            }

            return new Ancestry(createAncestry(pureMom, mom, momMod), createAncestry(pureDad, dad, dadMod));
        }
        return null;
    }

    @Override
    public String toString() {
        if (pure) {
            return modifier + (modifier.length()>0?" ":"") + name;
        } else {
            return "[F: " + father + ", M: " + mother + "]";
        }
    }
    
    public boolean equals(Ancestry otherFamily) {
        System.out.print("The families are ");
        if (pure == otherFamily.isPure()) {
            System.out.print("as pure (" + pure + ") ");
            if (pure && name.equals(otherFamily.getName())) {
                System.out.print("and both " + name + " ");
                if (modifier.equals(otherFamily.modifier)) {
                    System.out.println("and both " + modifier + ".");
                    return true;
                }
            }
        }
        System.out.println("not the same.");
        return false;
    }

    public String getName() {
        return name;
    }
    
    public String getModifier() {
        return modifier;
    }
    
    public boolean isPure() {
        return pure;
    }
}
