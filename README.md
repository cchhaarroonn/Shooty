# Shooty
Minecraft plugin that can be used as minigame on servers to get better aim with bow, it works like valorant practice when you kill mob (zombie) it will spawn it again on random location and you have to shoot him and kill him (mobs are 1 shot).


## Supported versions

Currently supported versions are:

```
✔️ 1-8-1.18.2
```

## Permissions

This plugin only has few permissions next to permission you have who should have this permission

```
shooty.ammo (everyone)
shooty.bow (everyone)
shooty.spawn (everyone)
shooty.join (everyone)
shooty.setspawn (administration)
shooty.setborder (administration)
```

## How to use commands

```
/ajoin <name of arena (name of world folder)> - Join arena to fight with enemies
/aspawn - Teleports you to arena spawn
/asetspawn - Set spawn for arena for players to spawn when they join or when they use /aspawn
/abow - Get your bow to shoot enemy
/aammo - Get arrows for your bow
/asetborder <minx/maxx/minz/maxz/height) - It will get cordinates for location where you stand use F3 for setting border it will be much easier
```

## Arenas

Please have in mind that when you use /asetborder it will use name of you world folder so for example in server files you have world folder if you want that to be your only arena then arena name will be world. Also if you want multiple arneas (which is quite nice if you will have more players playing this minigame) you will have to use /ajoin <name of arena (name of map where world is stored). Also you can use signs to make it easier for players to join in arena.

![image](https://user-images.githubusercontent.com/90192366/169647884-777365fc-8c67-4791-bce4-962ef53705ad.png) 

This is the name of mine example world/arena that players will use to join so for example /ajoin peder


## Signs 

If you use signs for your players to join you can place them however you want but you have to follow this steps. On first line you have to write [JOIN ARENA] and on second line you have to write exact name of your world folder so if your world folder is called Swamp then your arena is called Swamp and you will write Swamp on second line of your sign that you placed.

![image](https://user-images.githubusercontent.com/90192366/169647867-31982553-9098-454a-baea-427fb9a7ef6d.png)


## Future updates (maybe)

In future i might add big change so that plugin spawn npc instead of mob so it can use real valorant skins but i will see what will i do about that idea!


## Note 

Compiled .jar file for your server is located in target folder and you can use Shooty-1.0.jar
