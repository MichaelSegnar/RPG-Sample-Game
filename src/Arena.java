import java.util.Random;
import java.util.Scanner;

import javax.management.remote.SubjectDelegationPermission;

public class Arena {
	public static class Character{
		String name; 
		int HP, ATK, DEF, MAX_HP;
	}

	public static class Hero extends Character{
		public void init()
		{
			name = "???";
			HP = 25;
			ATK = 4;
			DEF = 1;
			MAX_HP = HP;
		}
	}
	
	public static class ENME extends Character{
		String attack;
		public void init(int encounter)
		{
			switch(encounter) {
			case 0:
				name = "Slime"; HP = 20; ATK = 3; DEF = 2; attack = "Ram"; break;
			case 1:
				name = "Skeleton"; HP = 25; ATK = 1; DEF = 3; attack = "Bone throw"; break;
			default:
				name = "Keanu Reeves"; HP = 999; ATK = 999; DEF = 999; attack = "John Wicking the situation"; break;
			}
			MAX_HP = HP;
		}
	}
	
	public static void main(String [] args)
	{
        Random chance = new Random();
        Scanner in = new Scanner(System.in);
        
		Hero Player = new Hero();Player.init();
		ENME Enemy = new ENME();Enemy.init(chance.nextInt(2));
        
        System.out.println("What is your name: ");
        Player.name = in.nextLine();
        System.out.println("Alright " + Player.name + ", you will fight " + Enemy.name + "!");
        
        do {
        	System.out.println(Player.name + "'s turn!\n1. Sword Slash\n2. Flare\n3. Heal");
        	int select = Integer.parseInt(in.nextLine());
        	int damage = 0;
        	switch(select)
        	{
        	case 1:
        		damage = DMG(Player.ATK,Enemy.DEF,2,chance.nextInt(3));
        		Enemy.HP -= damage;
        		System.out.println("\nSword Slash attack! " + Enemy.name + " took " + damage + " damage!");
        		break;
        	case 2:
        		damage = DMG(Player.ATK,Enemy.DEF,1,chance.nextInt(5));
        		Enemy.HP -= damage;
        		System.out.println("\nFlare attack! " + Enemy.name + " took " + damage + " damage!");
        		break;
        	case 3:
        		damage = HEAL(2,chance.nextInt(5),Player.MAX_HP,Player.HP);
        		Player.HP += damage;
        		System.out.println("\nHeal activated " + Player.name + " restored " + damage + " HP!");
        		break;
        	default:
        		System.out.println("No attacks selected! 0 damage delt!");
        	}
        	if(Enemy.HP <= 0) {break;}
        	damage = DMG(Enemy.ATK,Player.DEF,2,chance.nextInt(5));
        	Player.HP -= damage; if(Player.HP < 0) {Player.HP = 0;}
        	System.out.println(Enemy.name + "'s turn!\n" + Enemy.attack + " attack! " + Player.name + " took " + damage + " damage!\n" + Player.name + " has " + Player.HP + " HP remaining!\n");
        }while(Player.HP > 0);
        
        if(Enemy.HP <= 0) {System.out.println(Enemy.name + " has been defeated! " + Player.name + " wins!");}
        else {System.out.println(Player.name + " has been defeated! " + Enemy.name + " wins!");}
	}
	
	public static int DMG(int player, int enemy, int base, int chance)
	{
		int output = player + base + chance - enemy;
		if(output <= 0) {return 0;}
		return output;
	}
	
	public static int HEAL(int base, int chance, int limit, int current)
	{
		int restore = base + chance;
		if((restore+current) > limit) {return limit - current;}
		return restore;
	}
}
