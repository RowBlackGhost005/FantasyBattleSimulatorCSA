# Fantasy Battle Simulator
**Fantasy Battle Simulator** is a console base game where you can create your own team and fight agains the enemy team in a turn based combat with multiple habilities giving you the ability to simulate lots of combats.

This game features:
- **4 Different Playable Characters** ([Knight, Mage, Archer, Assassin]) each with different stats and an unique special hability).
- **Deep combat mechanics** (Every character stat affects the gameplay and the outcome of their actions).
- **Turn based combat** (Each turn you can select any standing character to attack with).
- **Pseudo AI opponent** (Your opponent uses their characters based on chances of performing every action so you can play and have different battles every time).
- **Battle Creation** (Create your own team of up to 32 characters to fight agains the enemy team also defined by you giving you a lot of possible combinations) [Good luck trying to keep track of more than 4 characters per team].

# Meet the characters
Below you have the information about every character currently in the game.
| Character | Health | Damage | Defense | Speed | Dexterity | Energy
| -- | -- | -- | -- | -- | -- | -- |
| Knight | 100 | 20 | 5 | 25 | 10 | 5 |
| Mage| 80| 30 | 0 | 35 | 15 | 5 |
| Assassin | 75 | 15 | 5 | 25 | 30 | 5 |
| Archer | 50| 35 | 0 | 35 | 20 | 5 |
---
**STATS EXPLANATION**
| Stat | Description
| -- | -- |
| **Health** | Health points of the character, if reaches 0 the character is defeated. |
| **Damage** | Base damage points that will affect the targets health if the target has no Defense. |
| **Defense** | Points to be absorbed of the incoming Damage before deducting Health Points.|
| **Speed** | Determines the speed of the character to take its turn (Not in use in this version yet).|
| **Dexterity** | Determines the probability of avoiding an incoming attack regardless of its type. |
| **Energy** | Determines the threshold at which a character can use its special ability, one point is ganed after every turn played. |

---
**CHARACTERS SPECIAL ABILITY**
| Character|  Ability| Stats |
|--|--|--|
| **Knight** | Guards up and prepares for battle increasing its Damage and Defense | +5 Damage / +5 Defense |
| **Mage** | Protects itself by generating a magic shield around him | +20 Defense |
| **Assassin** | Focuses on the enemies movements increasing its perception of incoming attacks | +5 Damage / +20 Dexterity |
| **Archer** | Shoots a special arrow with extreme precision to damage its oponents | +5 Damage |

---
Due to time constraints this game doesn't feature this characteristics but its path of implementation was set:
- Inventory system for items.
- Different energy recovery based on actions.
- Different energy requirements for special ability.
- Multi ability characters.
- Unique names for every character at play (i.e Knight 1 , Knight 2).
- Option for sequential gameplay (Every character has is turns instead of being free to select).
- Option for speed based gameplay (The character turn is based on its speed [i.e First reaching 100 points and reset after its turn])
- Extra text explaining each hability of the character.
- Random arena  (Select your team and play against random teams).
