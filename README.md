![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11-brightgreen)
![Loader](https://img.shields.io/badge/Loader-Fabric-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Modrinth](https://img.shields.io/badge/Available%20on-Modrinth-orange)

# Stack Size Plus

A Fabric mod that increases the stack size of many normally non-stackable vanilla items to **64**.

The goal of this mod is to make inventory management easier while still respecting item data (for example enchantments, potion types, or dyed armor).

---

## Features

The following items now stack to **64**:

- Armor stands
- Banner patterns
- Banners
- Beds
- Boats (Regular and chest)
- Buckets (Empty)
- Cake
- Music discs
- Eggs (Regular, brown, and blue)
- Ender pearls
- Enchanted books
- Goat horns
- Honey bottles
- Minecarts
- Potions (Regular, splash, and lingering)
- Saddles
- Shulker boxes (Empty)
- Signs (Reuglar and hanging)
- Snowballs
- Stews
- Totems of Undying
- Writable books
- Horse armor
- Nautilus armor
- Wolf armor

---

## Data-aware stacking

Items that contain **data** will only stack if the data matches.

Examples:

| Item | Behavior |
|-----|-----|
| Enchanted Books | Only stack with the **same enchantment** |
| Potions | Only stack with the **same potion type** |
| Goat Horns | Only stack with the **same horn sound** |
| Dyed Armor | Only stack if the **color matches** |
| Empty Shulker Boxes | Only stack if **completely empty** |

---

## Requirements

- **Minecraft:** 1.21.11  
- **Loader:** Fabric  
- **Fabric API:** Required  

---

## Installation

1. Download the `.jar` from the **Releases** page.
2. Place the file in your:

```
.minecraft/mods
```

folder.

---

## Multiplayer

Works on multiplayer servers.

For best compatibility, install the mod on:

- the server
- the client

---

## Building from Source

Clone the repository and run:

```
gradlew build
```

The compiled mod will appear in:

```
build/libs
```

---

## Credits

This mod was originally inspired by the **Bigger Stack Size datapack** created by **Jodek**.

This project was made as a Fabric mod replacement for personal use and later expanded for general use.

Original datapack:
https://modrinth.com/datapack/bigger-stack-size

## License

This project is licensed under the **MIT License**.

See the `LICENSE` file for details.

---

## Links

GitHub repository:  
https://github.com/shmarkyboo/stacksizeplus
