# SE-MasterMind

[![Build Status](https://travis-ci.org/Zelle97/SE-MasterMind.svg?branch=develop)](https://travis-ci.org/Zelle97/SE-MasterMind)
[![Coverage Status](https://coveralls.io/repos/github/Zelle97/SE-MasterMind/badge.svg?branch=develop)](https://coveralls.io/github/Zelle97/SE-MasterMind?branch=develop)

## Description
Software Project for the Topic Software Engineering at HTWG-Konstanz written in Scala.

## Releases

You can either download the JAR [here](https://github.com/Zelle97/SE-MasterMind/releases)
or head over to [Docker Hub](https://hub.docker.com/r/zellesdocker/se-mastermind) and grab the container (TUI interaction only).

## How to play

Depending on if you play with Docker or the JAR you have different options:

 - JAR: You can run the jar like this: `java -jar mastermind.jar <input>`. Input can be either 0 for TUI or 1 for GUI. If no input is given it will run parallel.
 - Docker: With docker you only can play with the TUI. Simply run `docker run -it zellesdocker/se-mastermind`

### Commands
Interacting with the GUI should be self explanatory.
The TUI also has a help command. simply input `h` to display the help.

## The Game
Mastermind is originally a board game. We decided to make our own digital version of it.
Here is a link that describes the game in detail: [Mastermind](https://en.wikipedia.org/wiki/Mastermind_(board_game)).

## Scala
The entire project is written in [scala](https://scala-lang.org/). More infos about scala found in the link.



##### Author & Licence
 
If you have any questions or improvement Ideas feel free to open an Issue or a feature request.

 > Authored and maintained by Zelle97, PaulSeifried and GerhardGoetz · GitHub [@Zelle97](https://github.com/Zelle97) / GitHub [@PaulSeifried](https://github.com/PaulSeifried) / GitHub [@GerhardGoetz](https://github.com/GerhardGoetz)


