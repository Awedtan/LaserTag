# LaserTag
### For more information please visit https://github.com/Awedtan/LaserTag
### List of useful commands:
- git status - Gives information on the differences between a local copy and the remote repo
- git add . - Adds all changed files
- git commit -m "name of commit" - Puts all added files into a commit
- git checkout -b "name of branch" Creates the specified branch on your local repo
- git push -u origin "branch name" - Pushes the specified local branch to the remote repo


# List of all known bugs
### Player collisions with walls while moving diagonally
https://github.com/Awedtan/LaserTag/issues/13
- When moving up and diagonally into a multi-tile vertical wall, the player model catches on the part where two wall tiles meet

#### Steps to reproduce the behavior:
1. Go to the immediate right or left of a multi-tile vertical wall
2. Hold 'W' and 'A' or 'W' and 'D' to move into the wall

#### Expected behaviour
- The player model should just move along the side of the wall without stopping


### Player FOV is not centred on player model
https://github.com/Awedtan/LaserTag/issues/27
- The center of rotation for the player's vision is not centered on the player, seems to be down and to the right by a few dozen pixels

#### Steps to reproduce the behavior:
1. Just move the mouse around

#### Expected behaviour
- Tiles in front of the player model should always be visible, tiles behind the player model should never be


### Rectangles skipping through other rectangles
https://github.com/Awedtan/LaserTag/issues/44
- Moving rectangles in the game is just really short and rapid teleportation, so projectiles and other rectangles can skip right through other rectangles if they are "moving" fast enough or the wall is thin enough

#### Steps to reproduce the behavior:
1. Set projectile speeds to a high value (eg. 50)
2. Shoot towards a couple walls
3. Shoot at a couple enemies

#### Expected behaviour
- Projectiles should stop at walls and hit enemies every time without fail


### Larger projectile size makes wall collisions weird
https://github.com/Awedtan/LaserTag/issues/47
- If the projectile hitbox size is larger than about 5x5, the hitboxes for walls seem to grow in size for whatever reason, with a tendancy to grow upwards

#### Steps to reproduce the behavior:
1. Set projectile size to a larger value (eg. 5x20)
2. Shoot towards a couple walls

#### Expected behaviour
- Projectiles should only disappear when actually colliding with a wall or an entity


### Game menus look weird on resolutions other than 1920x1080
https://github.com/Awedtan/LaserTag/issues/66
- If your screen resolution isn't 1080p, OR if Windows display scaling is at anything but 100%, the menus will all look wack

#### Steps to reproduce the behavior:
1. Change resolution in Windows settings (or have a native resolution that isn't 1080p)
2. Play the game

#### Expected behaviour
- The game should look normal all the time


### Multiple enemies shooting at you results in weird shooting patterns
- When multiple enemies are actively shooting at you, some shoot way more than others, some don't shoot at all
- Seems that the shots are all originating at a few enemies instead of being distributed evenly amongst the group

#### Steps to reproduce the behavior:
1. Get a large group of enemies to shoot at you (set player health really high to make this easier)

#### Expected behaviour
- Enemies should have consistent shooting behaviours, no matter how many of them there are


### Sometimes starting a new game will give null exception errors?
- Honestly it feels random and I don't really know what may be causing it nor how to trigger it reliably


# Special instructions
- Please run the game at 1920x1080 and with Windows display scaling set to 100%
- Running any of the .java files should work fine, but just in case only run the body.java file
- The program will open a pdf file every time you run it
- There is no sound, so no speakers needed


# Planned but not implemented features
### Health bar
- The number on top of your player model is representative of your health instead

### Fancier graphics
- We did give projectiles their own sprites, but everything else is all just rectangles

### Pause menu
- We didn't get around to doing this, pretty low priority

### Save file of highscores
- Also didn't get around to it

### Sound effects
- Didn't get to it

### A button that would immediately start a new game without exiting to the menu first
- Was much harder to make than we first thought

### Holding down the mouse buttons for automatic fire
- Didn't get to it

### Making projectiles originate from the actual gun rectangles
- Didn't get to it, looks okay enough already

# Implemented but not planned features
### Multiple game modes
- We knew that we wanted multiple game modes, just didn't actually come up with any at the time
### The timer label
### The score label
- These two are tied to the multiple game modes
