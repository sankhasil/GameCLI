/**
 * 
 */
package com.cli.game.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cli.game.constant.enums.Gender;
import com.cli.game.model.base.Armor;
import com.cli.game.model.base.Enemy;
import com.cli.game.model.base.Inventory;
import com.cli.game.model.base.SpecialPower;
import com.cli.game.model.base.Weapon;
import com.cli.game.model.extended.Hero;
import com.cli.game.model.extended.Zombie;

/**
 * @author Sankha
 *
 */
public class StorySimulator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7181911036503655599L;
	private static StorySimulator INSTANCE;
	private Hero hero;
	private List<Enemy> enemyList = new ArrayList<>();
	private Enemy enemy;
	private int floor = 0;
	private boolean inBattle = false;
	

	public boolean isInBattle() {
		return inBattle;
	}

	public void setInBattle(boolean inBattle) {
		this.inBattle = inBattle;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	private StorySimulator() {
	}

	public int getFloor() {
		return floor;
	}

	public static StorySimulator getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new StorySimulator();
		}
		return INSTANCE;
	}

	public static void setInstance(StorySimulator iNSTANCE) {
		if(iNSTANCE != null)
			INSTANCE = iNSTANCE;
	}

	public Hero getHero() {
		return hero;
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setCurrentEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public Enemy getCurrentEnemy() {
		return enemy;
	}

	public void getSoloAdventureSimulation() {
		System.out.println("\tYou choose wisely");
		enemyList.add(new Zombie(10, 75, 60, 10));
		enemyList.add(new Enemy(10, 60, 50));
		enemyList.add(new Enemy(10, 60, 50));
		enemyList.add(new Zombie(10, 75, 60, 10));
		enemyList.add(new Zombie(10, 75, 60, 10));
	}

	public Hero createHero(String name, Gender gender, Inventory inventory, SpecialPower specialPower, Weapon weapon,
			Armor armor) {
		if (this.hero == null) {
			hero = new Hero(name, gender, 80, 20, inventory, Arrays.asList(specialPower), weapon, armor);
		}
		return this.hero;
	}

	public void gameCommands() {
		StringBuilder commandBuilder = new StringBuilder();
		commandBuilder.append("\n# 1. Enter the Dungeon");
		commandBuilder.append("\n# 2. Check Player Status");
		commandBuilder.append("\n# 3. Save");
		commandBuilder.append("\n# 4. Exit");
		System.out.println(commandBuilder.toString());
	}

	public void progressStory(Enemy enemy) {
		System.out.println("You are walking down to floor " + floor);
		if (enemy instanceof Zombie) {
			System.out.println("\t Zombie Appeared.");
		} else {
			System.out.println("\t Enemy Appeared.");
		}
	}
	public void playerStatus() {
		System.out.println("Hero's HP: "+this.hero.getHealth());
		System.out.println("Hero's Armor: "+this.hero.getArmor().getHitProtection());
		System.out.println("Opponent's HP : "+this.enemy.getHealth());
	}
}
