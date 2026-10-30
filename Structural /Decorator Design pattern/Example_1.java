// ==========================================
// 1. THE COMPONENT (Base Interface)
// ==========================================
interface Hero {
    String getDescription();
    int getAttackPower();
}

// ==========================================
// 2. THE CONCRETE COMPONENTS (Base Heroes)
// ==========================================
class Warrior implements Hero {
    public String getDescription() { return "Warrior"; }
    public int getAttackPower() { return 10; }
}

class Mage implements Hero {
    public String getDescription() { return "Mage"; }
    public int getAttackPower() { return 5; } // Mages are weak physically
}

class Archer implements Hero {
    public String getDescription() { return "Archer"; }
    public int getAttackPower() { return 8; }
}

// ==========================================
// 3. THE ABSTRACT DECORATOR
// ==========================================
abstract class HeroDecorator implements Hero {
    protected Hero hero;

    public HeroDecorator(Hero hero) {
        this.hero = hero;
    }

    public String getDescription() { return hero.getDescription(); }
    public int getAttackPower() { return hero.getAttackPower(); }
}

// ==========================================
// 4. CONCRETE DECORATORS (Items)
// ==========================================
class FlamingSword extends HeroDecorator {
    public FlamingSword(Hero hero) { super(hero); }
    public String getDescription() { return super.getDescription() + " wielding a Flaming Sword"; }
    public int getAttackPower() { return super.getAttackPower() + 25; }
}

class DragonShield extends HeroDecorator {
    public DragonShield(Hero hero) { super(hero); }
    public String getDescription() { return super.getDescription() + " with a Dragon Shield"; }
    public int getAttackPower() { return super.getAttackPower() + 15; }
}

class MagicStaff extends HeroDecorator {
    public MagicStaff(Hero hero) { super(hero); }
    public String getDescription() { return super.getDescription() + " holding a Magic Staff"; }
    public int getAttackPower() { return super.getAttackPower() + 20; }
}

class PoisonDagger extends HeroDecorator {
    public PoisonDagger(Hero hero) { super(hero); }
    public String getDescription() { return super.getDescription() + " holding a Poison Dagger"; }
    public int getAttackPower() { return super.getAttackPower() + 12; }
}

// ==========================================
// 5. THE CLIENT (Main)
// ==========================================
public class Main {
    public static void main(String[] args) {
        
        // Scenario 1: A Warrior with a Sword and a Shield
        Hero warrior = new DragonShield(new FlamingSword(new Warrior()));
        System.out.println(warrior.getDescription());
        System.out.println("Attack: " + warrior.getAttackPower() + "\n");

        // Scenario 2: A Mage with a Magic Staff and a Shield
        Hero mage = new DragonShield(new MagicStaff(new Mage()));
        System.out.println(mage.getDescription());
        System.out.println("Attack: " + mage.getAttackPower() + "\n");

        // Scenario 3: An Archer with a Poison Dagger
        Hero archer = new PoisonDagger(new Archer());
        System.out.println(archer.getDescription());
        System.out.println("Attack: " + archer.getAttackPower() + "\n");

        // Scenario 4: An Archer with EVERYTHING (The Swiss Army Archer)
        Hero superArcher = new DragonShield(
                                new FlamingSword(
                                    new MagicStaff(
                                        new PoisonDagger(new Archer())
                                    )
                                )
                            );
        System.out.println(superArcher.getDescription());
        System.out.println("Attack: " + superArcher.getAttackPower());
    }
}
