# Readme

# CS611-Assignment 4
## Legends: Monsters and Heroes
---------------------------------------------------------------------------
- Name: Yijia Chen
- Email: cyj0709@bu.edu
- Student ID: U98033467

## Files
---------------------------------------------------------------------------

```
src/
├── Item.java                       # A comprehensive collection of all weapons and equipment.
├── ItemFactory.java                # Generate all kinds of items using factory pattern and generic type.
├── Armor.java                      # Inherits from Item. Indicates various attributes of Armor.
├── ArmorFactory.java               # Inherits from ItemFactory. Generate all armors using factory pattern.
├── Spell.java                      # Inherits from Item. Indicates various attributes of Spell.
├── SpellFactory.java               # Inherits from ItemFactory. Generate 3 kinds of spells using factory pattern.
├── FireSpell.java                  # Inherits from the Spell. Initialize the fire spells.
├── IceSpell.java                   # Inherits from the Spell. Initialize the ice spells.
├── LightningSpell.java             # Inherits from the Spell. Initialize the lightning spells.
├── Potion.java                     # Inherits from Item. Indicates various attributes of Potion. The `used` function indicates that when the hero uses this potion, the affected attributes are improved accordingly.
├── PotionFactory.java              # Inherits from ItemFactory. Generate all potions using factory pattern. Use a hash map to record the increased values ​​of different attributes.
├── Weapon.java                     # Inherits from Item. Indicates various attributes of Weapon.
├── WeaponFactory.java              # Inherits from ItemFactory. Generate all weapons using factory pattern.
├── Role.java                       # Represents the collection of all roles in the game. An attack method is implemented in it to represent the logic of fight between Monsters and Heroes, which only represents physical damage.
├── RoleFactory.java                # Generate all kinds of roles using factory pattern and generic type.
├── Hero.java                       # Inherits from Role. It shows the various attributes of the hero and their changes, as well as the equipment he currently has. It also implements the logic of the hero buying things, leveling up, and setting hero pieces. What's more, it also calculates the hero's weapon and spell damage and defense according to the formula.
├── HeroFactory.java                # Inherits from RoleFactory. Generate 3 kinds of heroes using factory pattern.
├── Paladin.java                    # Inherits from Hero. Initialize and calculate his unique upgrade rules according to the rules.
├── Sorcerer.java                   # Inherits from Hero. Initialize and calculate his unique upgrade rules according to the rules.
├── Warrior.java                    # Inherits from Hero. Initialize and calculate his unique upgrade rules according to the rules.
├── Monster.java                    # Inherits from Role and initialized. I decreased the damage of the monsters to make the game more reasonable.
├── MonsterFactory.java             # Inherits from RoleFactory. Generate 3 kinds of monsters using factory pattern.
├── Dragon.java                     # Inherits from Monster and initialized.
├── Exoskeleton.java                # Inherits from Monster and initialized.
├── Spirit.java                     # Inherits from Monster and initialized.
├── Piece.java                      # Represents the total set of various pieces on the board, with name and position attributes. The `event` method triggers events in a specific area.
├── Common.java                     # Inherits from Piece and represents the common places. The event on common places contains the logic for randomly spawning monsters and engaging them in fight.
├── Inaccessible.java               # Inherits from Piece and represents the inaccessible places.
├── Market.java                     # Inherits from Piece and represents the market places. The market requires manual entry. Events on the market include heroes buying and selling items. During this time, players can also view all items in the store and the hero's inventory.
├── Tile.java                       # Represents the grids on the board, on which different pieces are placed.
├── Board.java                      # Represents the game board. It randomly generates various locations on the map. The hero's position is represented by "H", which is randomly located in any common space at the beginning. It includes initializing the board, representing the logic of player movement and board update, and checking the legality of its movement, and displaying the board.
├── Game.java                       # Represents the logic of the game, showing the board and allowing players to perform various actions.
├── Inventory.java                  # Record the heroes' inventories.
├── Main.java                       # Start the game, display the help menu, and select the heroes.


```

## Notes
---------------------------------------------------------------------------
In this project, I use factory pattern to generate all items and roles. The Factory Pattern supports programming to interfaces. This allows code to rely on abstract types rather than concrete implementations, promoting loose coupling and better adherence to the Open/Closed Principle.


## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory after unzipping the files
2. Run the following instructions:
javac *.java
java Main