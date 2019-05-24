/**
 * 
 */
package com.cli.game.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.cli.game.beast.FightingGame;
import com.cli.game.constant.enums.Gender;
import com.cli.game.constant.enums.PotionType;
import com.cli.game.constant.enums.SpecialPowerType;
import com.cli.game.constant.enums.WeaponType;
import com.cli.game.constant.strings.GameConstants;
import com.cli.game.engine.exceptions.GameCommandNotSupportedException;
import com.cli.game.engine.exceptions.GenderNotSupportedException;
import com.cli.game.engine.exceptions.PlayLogiceException;
import com.cli.game.engine.exceptions.SaveGameNotWorkingException;
import com.cli.game.engine.exceptions.SpecialPowerNotSupportedException;
import com.cli.game.engine.exceptions.WeaponNotSupportedException;
import com.cli.game.model.base.Armor;
import com.cli.game.model.base.Enemy;
import com.cli.game.model.base.Inventory;
import com.cli.game.model.base.Potion;
import com.cli.game.model.base.SpecialPower;
import com.cli.game.model.base.Weapon;
import com.cli.game.model.extended.Hero;
import com.cli.game.model.extended.Zombie;

/**
 * @author Sankha
 *
 */
public class PlayingLogic {
	private final Scanner scan;
	private final boolean godMode;
	private static boolean gameChoice = true;
	private Random random = new Random();
	private Path saveFolderPath = Paths.get(GameConstants.GAME_SAVE_FOLDER_LOCATION);

	public PlayingLogic(Scanner scan, boolean godMode) {
		this.scan = scan;
		this.godMode = godMode;
	}

	public void executePlayLogic() throws PlayLogiceException {
		if (scan != null) {
			try {
				renderWelcomeMessage();
				boolean running = true;
				boolean beastModeRunning = false;
				GAME: while (running) {
					switch (scan.nextLine().toLowerCase()) {
					case "exit":
						System.out.println("!!!!Thank you for Playing!!!!!");
						running = false;
						System.exit(0);
						break;
					case "1":
						if (Files.exists(Paths.get(GameConstants.SAVE_FILE_LOCATION))) {
							System.out.println(
									"Do You want to Load previously Saved Game..Press\n\t L. To Load\n\t C. To Create New.");
							String input = scan.nextLine();
							if (input.equalsIgnoreCase("l")) {
								loadLastSavedGame();
							} else if (input.equalsIgnoreCase("c")) {
								StorySimulator.getInstance().getSoloAdventureSimulation();
								boolean heroChoice = true;
								while (heroChoice) {
									try {
										createHero();
										heroChoice = false;
									} catch (GenderNotSupportedException | SpecialPowerNotSupportedException
											| WeaponNotSupportedException we) {
										System.out.println("You choose wrong. Please start from Character creation!!");
									}
								}
							}
						} else {
							StorySimulator.getInstance().getSoloAdventureSimulation();
							boolean heroChoice = true;
							while (heroChoice) {
								try {
									createHero();
									heroChoice = false;
								} catch (GenderNotSupportedException | SpecialPowerNotSupportedException
										| WeaponNotSupportedException we) {
									System.out.println("You choose wrong. Please start from Character creation!!");
								}
							}
						}
						StorySimulator.getInstance().gameCommands();
						gameChoice = true;
						while (gameChoice) {
							try {
								processCommand();

							} catch (GameCommandNotSupportedException | SaveGameNotWorkingException se) {
								System.out.println(se.getMessage());
							}
						}
						if(!gameChoice) {
							clearScreen();
							renderWelcomeMessage();
						}
						break;
					case "2":
						
						System.out.println("Whats comming on Game of Bones");
						System.out.println("\t# Different type of enemies.");
						System.out.println("\t# You can control an Army of Soldiers.");
						System.out.println("\t# You can buy/sell your Inventory Items. ");
						System.out.println("\t# More CLI Graphics.");
						
						System.out.println("The greatness is in Making. Please choose 1 to for now.");
						break;
					case "beastmodeon":
						if (!beastModeRunning) {
							new FightingGame();
							beastModeRunning = true;
						}
						break;
					case "beastmodeoff":
						// FIXME: not working
						if (beastModeRunning)
							FightingGame.exitGame();
						break;
					default:
						System.out.println("Choice not Supported!!!");
						break;

					}
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				throw new PlayLogiceException(e.getMessage(), e);
			} finally {
				scan.close();
			}
		}
	}

	private void clearScreen() {
		//System.out.println("\033[H\033[2J");
		System.out.flush();
	}

	private void processCommand() throws GameCommandNotSupportedException, SaveGameNotWorkingException {
		String command = scan.nextLine().toLowerCase();
		switch (command) {
		case "1":
			if (StorySimulator.getInstance().isInBattle()) {
				System.out.println("You are in battle with enemy...Can't choose this option.");
			} else {
				gameProgress();
			}
			break;
		case "2":
			if (StorySimulator.getInstance().isInBattle()) {
				System.out.println("You are in battle with enemy...Can't choose this option.");
			} else {
				System.out.println(StorySimulator.getInstance().getHero().toString());
				System.out.println("What you want to do now?");
				StorySimulator.getInstance().gameCommands();
			}
			break;
		case "a":
			// Getting Hero and enemy information
			if (StorySimulator.getInstance().getCurrentEnemy() != null) {
				StorySimulator.getInstance().setInBattle(true);
				Enemy selectedEnemy = StorySimulator.getInstance().getCurrentEnemy();
				Hero selectedHero = StorySimulator.getInstance().getHero();
				int heroStrikeAmount = random
						.nextInt(selectedHero.getAttackDamage() + selectedHero.getWeapon().getHitDamage());
				int enemyStrikeAmount = random.nextInt(selectedEnemy.getAttackDamage());
				int heroHealth = selectedHero.getHealth() + selectedHero.getArmor().getHitProtection();
				// health check process
				if (heroHealth > selectedEnemy.getHealth()) {
					System.out.println(selectedHero.getName() + " Hero hits " + heroStrikeAmount);
					selectedEnemy.setHealth(selectedEnemy.getHealth() - heroStrikeAmount);
					// No damage if godmode
					if (!godMode) {
						if (selectedHero.getArmor().getHitProtection() > 0) {
							int armorDeduction = selectedHero.getArmor().getHitProtection() - enemyStrikeAmount;
							if (armorDeduction > -1)
								selectedHero.getArmor().setHitProtection(armorDeduction);
							else {
								selectedHero.setHealth(selectedHero.getHealth() + armorDeduction);
							}
						} else {
							selectedHero.setHealth(selectedHero.getHealth() - enemyStrikeAmount);
						}
					}
				} else {
					System.out.println("\t You are too weak to continue!!");
				}
				// After battle result
				afterBattleAction(selectedEnemy, selectedHero);
			} else {
				System.out.println("You are in battle with enemy...Can't choose this option.");
			}
			break;
		case "yes":
		case "y":
			if (StorySimulator.getInstance().getCurrentEnemy() != null && StorySimulator.getInstance().isInBattle()) {
				System.out.println("You are in battle with enemy...Can't choose this option.");
			} else {
				gameProgress();
			}
			break;
		case "no":
		case "n":
			if (StorySimulator.getInstance().getCurrentEnemy() != null && StorySimulator.getInstance().isInBattle()) {
				System.out.println("You are in battle with enemy...Can't choose this option.");
			} else {
				System.out.println("\tYou are out of the dungeon..");
				StorySimulator.getInstance().gameCommands();
			}
			break;
		case "b":
			if (StorySimulator.getInstance().isInBattle()) {
				Inventory selectedHeroInventory = StorySimulator.getInstance().getHero().getInventory();
				if (selectedHeroInventory.getListOfPotions().size() > 0) {
					Potion healthPotion = selectedHeroInventory.getListOfPotions().stream()
							.filter(potion -> potion.getType().equals(PotionType.HEALTH)).findFirst().orElse(null);
					if (healthPotion != null) {
						System.out.println("Drinking Health Potion..");
						StorySimulator.getInstance().getHero().setHealth(
								StorySimulator.getInstance().getHero().getHealth() + healthPotion.getPotionAmount());
						StorySimulator.getInstance().playerStatus();
					} else {
						System.out.println("No Health Potions Left!!!!");
					}
				} else {
					System.out.println("No Potions Left!!!!");
				}
			} else {
				System.out.println("You are not in Battle...Can't Drink Potion.");
			}
			break;
		case "c":
			if (StorySimulator.getInstance().isInBattle()) {
				List<SpecialPower> selectedHeroSpecialPowerList = StorySimulator.getInstance().getHero()
						.getSpecialPowerList();
				if (selectedHeroSpecialPowerList.size() > 1) {
					System.out.println("Choose Power... ");
					for (SpecialPower specialPower : selectedHeroSpecialPowerList)
						System.out.println("# " + specialPower.getType().getValue().toUpperCase().charAt(0) + "."
								+ specialPower.getType().getValue().toUpperCase() + " - " + specialPower.getAmount());
				} else if (selectedHeroSpecialPowerList.size() == 1) {
					System.out.println("You have only one power... "
							+ selectedHeroSpecialPowerList.get(0).getType().getValue().toUpperCase());
					if (StorySimulator.getInstance().getCurrentEnemy() instanceof Zombie) {
						System.out.println("Using it on Zombie..");
					} else {
						System.out.println("Using it on Enemy..");
					}
					// FIXME: use special power.
					int specialPowerHitDamage = selectedHeroSpecialPowerList.get(0).getAmount();
					StorySimulator.getInstance().getCurrentEnemy().setHealth(
							StorySimulator.getInstance().getCurrentEnemy().getHealth() - specialPowerHitDamage);
					afterBattleAction(StorySimulator.getInstance().getCurrentEnemy(),
							StorySimulator.getInstance().getHero());

				}
			} else {
				System.out.println("You can't use Special Power.. You are not in Battle");
			}
			break;

		case "f":
			if (StorySimulator.getInstance().isInBattle()) {
				System.out.println("You used Fire...Let it burn");
				StorySimulator.getInstance().getCurrentEnemy()
						.setHealth(StorySimulator.getInstance().getCurrentEnemy().getHealth() - 30);
				afterBattleAction(StorySimulator.getInstance().getCurrentEnemy(),
						StorySimulator.getInstance().getHero());

			} else {
				System.out.println("You can't use Special Power.. You are not in Battle");
			}
			break;
		case "t":
			if (StorySimulator.getInstance().isInBattle()) {
				System.out.println("You used Thunder...Electrify");
				StorySimulator.getInstance().getCurrentEnemy()
						.setHealth(StorySimulator.getInstance().getCurrentEnemy().getHealth() - 22);
				afterBattleAction(StorySimulator.getInstance().getCurrentEnemy(),
						StorySimulator.getInstance().getHero());
			} else {
				System.out.println("You can't use Special Power.. You are not in Battle");
			}
			break;
		case "r":
			if (StorySimulator.getInstance().isInBattle()) {
				System.out.println("You used Rage Kill...Let it Bleed");
				StorySimulator.getInstance().getCurrentEnemy()
						.setHealth(StorySimulator.getInstance().getCurrentEnemy().getHealth() - 25);
				afterBattleAction(StorySimulator.getInstance().getCurrentEnemy(),
						StorySimulator.getInstance().getHero());
			} else {
				System.out.println("You can't use Special Power.. You are not in Battle");
			}
			break;
		case "d":
			// TODO: put run away logic
			if (StorySimulator.getInstance().getCurrentEnemy() != null) {
				System.out.println("You Run away from enemy. You are back to previous stage.");
				StorySimulator.getInstance().setInBattle(false);
				StorySimulator.getInstance().setCurrentEnemy(null);
				StorySimulator.getInstance().gameCommands();
			} else {
				System.out.println("You can't Run away from enemy.. You are not in Battle");
			}
			break;
		case "3":
			// saveLogic
			if (StorySimulator.getInstance().getCurrentEnemy() != null) {
				System.out.println("You are in Battle ... You can");
			} else {
				saveGame();
			}
			break;
		case "4":
			if (StorySimulator.getInstance().getCurrentEnemy() != null) {
				System.out.println("You can't Exit now...Enemy is infront of you..");
				renderPlayerActions();
			} else {
				System.out.println("!!!!Thank you for Playing Solo Adventure!!!!!");
				this.gameChoice = false;
//				System.exit(0);
			}
			break;
		default:
			throw new GameCommandNotSupportedException(command + " is not Supported..");
		}
	}

	/**
	 * 
	 * @param selectedEnemy
	 * @param selectedHero
	 */
	private void afterBattleAction(Enemy selectedEnemy, Hero selectedHero) {
		if (selectedEnemy.getHealth() < 1) {
			System.out.println(
					"You have deafeted the Enemy !! Cleared " + StorySimulator.getInstance().getFloor() + " Stage");
			StorySimulator.getInstance().setFloor(StorySimulator.getInstance().getFloor() + 1);
			StorySimulator.getInstance().setInBattle(false);
			// Gain Exp and level up
			if (selectedHero.getLevel() < 200) {
				selectedHero.setLevel((short) (selectedHero.getLevel() + 1));
				System.out.println("You have gained Experience !!");
				if (selectedHero.getLevel() % 5 == 0) {
					selectedHero.setHealth(selectedHero.getHealth() + 1);
					selectedHero.setAttackDamage(selectedHero.getAttackDamage() + 1);
					System.out.println("Your Health is increased: " + selectedHero.getHealth());
					System.out.println("Your Attack Damage is increased: " + selectedHero.getAttackDamage());

				}
			} else {
				System.out.println("You have already reached Max Level!!!");
			}
			// Loot chance
			if (random.nextInt(100) < selectedEnemy.getLootDropChance()) {
				if (random.nextInt(100) < 10) {
					Weapon newWeapon = new Weapon(20, WeaponType.MACE);
					selectedHero.getInventory().addWeaponToInventory(newWeapon);
					System.out.println("You have looted " + newWeapon.toString() + " is added to your Inventory.");
				}
				if (random.nextInt(100) < 30) {
					Weapon newWeapon = new Weapon(20, WeaponType.SWORD);
					selectedHero.getInventory().addWeaponToInventory(newWeapon);
					System.out.println("You have looted " + newWeapon.toString() + " is added to your Inventory.");
				}
				if (random.nextInt(100) < 50) {
					Potion newPotion = new Potion(15, PotionType.HEALTH);
					selectedHero.getInventory().addPotionToInventory(newPotion);
					System.out.println("You have looted " + newPotion.toString() + " is added to your Inventory.");
				}
				if (random.nextInt(100) < 40) {
					Potion newPotion = new Potion(15, PotionType.MANNA);
					selectedHero.getInventory().addPotionToInventory(newPotion);
					System.out.println("You have looted " + newPotion.toString() + " is added to your Inventory.");
				}
				if (random.nextInt(100) < 20) {
					Armor newArmor = new Armor(20);
					selectedHero.getInventory().addArmorToInventory(newArmor);
					System.out.println("You have looted " + newArmor.toString() + " is added to your Inventory.");
				}
			}
			// Remove enemy from story list
			StorySimulator.getInstance().getEnemyList().remove(selectedEnemy);
			StorySimulator.getInstance().setCurrentEnemy(null);
			System.out.println("Player Status " + selectedHero.toString());
			System.out.println("\tDo you want to continue...(yes/no)");
		} else {
			StorySimulator.getInstance().playerStatus();
			renderPlayerActions();
		}
	}

	private void saveGame() throws SaveGameNotWorkingException {
		if (!Files.exists(saveFolderPath)) {
			try {
				Files.createDirectories(saveFolderPath);
			} catch (Exception e) {
				System.out.println("Could not create directory. Game wont be saved..\nError" + e.getMessage());
				throw new SaveGameNotWorkingException(e.getMessage(), e);
			}
		}
		try {
			String pwd = System.getProperty("user.dir");
			Path saveFilePath = Paths.get(pwd, GameConstants.SAVE_FILE_LOCATION);
			FileOutputStream fileOutputStream = new FileOutputStream(saveFilePath.toFile());
			ObjectOutputStream objectStream = new ObjectOutputStream(fileOutputStream);
			objectStream.writeObject(StorySimulator.getInstance());
			objectStream.close();
			fileOutputStream.close();
			System.out.println("Game Saved Successfully.!!!!");
			System.out.println("-----------------------------------------------------");
			System.out.println("What you want to do now?");
			StorySimulator.getInstance().gameCommands();
		} catch (Exception e) {
			throw new SaveGameNotWorkingException("Game save problem " + e.getMessage(), e);
		}
	}

	private void loadLastSavedGame() throws SaveGameNotWorkingException {
		if (Files.exists(saveFolderPath)) {
			try {
				String pwd = System.getProperty("user.dir");
				Path saveFilePath = Paths.get(pwd, GameConstants.SAVE_FILE_LOCATION);
				FileInputStream fileInputStream = new FileInputStream(saveFilePath.toFile());
				ObjectInputStream objectStream = new ObjectInputStream(fileInputStream);
				StorySimulator.setInstance((StorySimulator) objectStream.readObject());
				objectStream.close();
				fileInputStream.close();
				System.out.println("Game Loaded Successfully.!!!!");
				System.out.println("-----------------------------------------------------");
				System.out.println("What you want to do now?");
			} catch (Exception e) {
				throw new SaveGameNotWorkingException("Game Loading problem " + e.getMessage(), e);
			}
		}
	}

	private void gameProgress() {
		List<Enemy> enemyList = StorySimulator.getInstance().getEnemyList();
		Enemy enemy = enemyList.get(random.nextInt(enemyList.size()));
		StorySimulator.getInstance().setCurrentEnemy(enemy);
		StorySimulator.getInstance().progressStory(enemy);
		renderPlayerActions();
	}

	private void createHero()
			throws GenderNotSupportedException, SpecialPowerNotSupportedException, WeaponNotSupportedException {
		System.out.println("\t Say your name: ");
		String name = scan.nextLine();
		if (name != null && !name.isEmpty()) {
			System.out.println("\t Gender:");
			String genderInString = scan.nextLine();
			Gender gender;
			if (genderInString.equalsIgnoreCase("male") || genderInString.equalsIgnoreCase("m")) {
				gender = Gender.MALE;
			} else if (genderInString.equalsIgnoreCase("female") || genderInString.equalsIgnoreCase("f")) {
				gender = Gender.FEMALE;
			} else if (genderInString.equalsIgnoreCase("unknown") || genderInString.equalsIgnoreCase("u")) {
				gender = Gender.UNKNOWN;
			} else {
				throw new GenderNotSupportedException(genderInString + " Not supported!!");
			}
			Inventory inventory = new Inventory();
			inventory.addPotionToInventory(new Potion(10, PotionType.HEALTH));
			StringBuilder specialPowerBuilder = new StringBuilder();
			specialPowerBuilder.append("\tPlease choose your Special Power:");
			specialPowerBuilder.append("\n\t[A] FIRE");
			specialPowerBuilder.append("\n\t[B] THUNDER");
//			specialPowerBuilder.append("\n\t[C] HEAL");
			specialPowerBuilder.append("\n\t[C] RAGE KILL");
			System.out.println(specialPowerBuilder.toString());
			String firstSpecialPower = scan.nextLine();
			SpecialPower specialPower;
			switch (firstSpecialPower.toLowerCase()) {
			case "a":
				specialPower = new SpecialPower(30, SpecialPowerType.FIRE);
				break;
			case "b":
				specialPower = new SpecialPower(22, SpecialPowerType.THUNDER);
				break;
//			case "c":
//				specialPower = new SpecialPower(25, SpecialPowerType.HEAL);
//				break;
			case "c":
				specialPower = new SpecialPower(25, SpecialPowerType.RAGE_KILL);
				break;
			default:
				throw new SpecialPowerNotSupportedException(firstSpecialPower + " Not supported!! ");
			}
			Weapon weapon;
			StringBuilder weaponBuilder = new StringBuilder();
			weaponBuilder.append("\tPlease choose your Weapon:");
			weaponBuilder.append("\n\t[A] SWORD");
			weaponBuilder.append("\n\t[B] MACE");
			System.out.println(weaponBuilder.toString());
			String weaponString = scan.nextLine();
			switch (weaponString.toLowerCase()) {
			case "a":
				weapon = new Weapon(10, WeaponType.SWORD);
				break;
			case "b":
				weapon = new Weapon(12, WeaponType.MACE);
				break;
			default:
				throw new WeaponNotSupportedException(weaponString + " Not supported!! ");
			}

			Armor armor = new Armor(20);
			StorySimulator.getInstance().createHero(name, gender, inventory, specialPower, weapon, armor);

			System.out.println("#####################################################################");
			System.out.println("Created : " + StorySimulator.getInstance().getHero().toString());

		}
	}

	private void renderWelcomeMessage() {
		System.out.println("\n");
		BufferedImage bufferedImage = new BufferedImage(100, 120, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bufferedImage.getGraphics();

		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics2D.drawString("Rollenspiel", 12, 24);
		for (int y = 0; y < 50; y++) {
			StringBuilder stringBuilder = new StringBuilder();

			for (int x = 0; x < 100; x++) {
				stringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "*");
			}

			if (stringBuilder.toString().trim().isEmpty()) {
				continue;
			}

			System.out.println(stringBuilder);
		}
		StringBuilder welcomeMessage = new StringBuilder();
		if (godMode)
			welcomeMessage.append("\n God mode is on.. you can't die");
		welcomeMessage.append("\nPress 'choice' to Choose Story...");
		welcomeMessage.append("\n[1] Solo Adventure");
		welcomeMessage.append("\n[2] Game of Bones");

		System.out.println(welcomeMessage.toString());

	}

	private void renderPlayerActions() {
		System.out.println("Choose what you want to do....");
		StringBuilder commandBuilder = new StringBuilder();
		commandBuilder.append("\n# A. Attack");
		commandBuilder.append("\n# B. Drink Health Potion");
		commandBuilder.append("\n# C. Use Special Power!!");
		commandBuilder.append("\n# D. Run Away!!");
		System.out.println(commandBuilder.toString());
	}
}
