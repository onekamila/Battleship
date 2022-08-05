![2]
![3]

# <u>Battleship</u>
*v0.2.1*


This is a program that simulates playing a game of Battleship between two players.

## Running the game
Navigate to the folder that you saved the code to, then run the following commands:
```shell
javac -cp out -sourcepath src\main -d out src\main\Battleship\Driver.java
java -cp out Battleship.Driver
```
*For my less shell-savvy friends*: an installer has been included for your convenience. Simply download the .msi file, 
run it, and then you will be able to run the game from there. (Currently only for Windows. I'll probably get around to 
packaging it into a macOS and Unix installer at some point soon.)


## Overview
This project is simply a recreation of a normal Battleship game. \
I'm using this as a way to test and showcase my design skills. If you have any recommendations, please share. 


## Upcoming Features
Some of you may notice that this is still only version 0.2.1. This is because I am planning on doing making several key 
changes to the system. The following lists are features I plan to include, but is by no means exhaustive and will be 
subject to expansion.


### Expected Features
These are features I definitely plan to implement at some point in the future. They include, but are not limited to:
* Backend redesign (moving to an event-based architecture)
* Adding an AI opponent
* Adding an option for a single or multiplayer game

### Possible Features
These are features I am contemplating adding to the system at some point. These are **potential** additions, so there is
no guarantee that these will actually be implemented. These additions include:
* Updating the UI to hide each player's screen after each turn (prevents cheating)
* Creating a GUI for the game (might enlist the help of a graphic designer. If interested, please contact me)


## Credits:
<u>System design</u>: Garrett Kamila Crayton \
<u>System development</u>: Garrett Kamila Crayton \
For any questions, please email me: [garrett.crayton@gmail.com][1]



[1]: mailto:garrett.crayton@gmail.com
[2]: https://img.shields.io/github/license/onekamila/Battleship
[3]: https://img.shields.io/github/v/release/onekamila/Battleship