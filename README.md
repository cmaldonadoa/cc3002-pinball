# Pinball - Tarea CC3002

This project is an implementation of the logic of the elements of a pinball game, which are bumpers, targets, tables, bonuses and a 
game controller. A brief description of every element is detailed below.

Also, this project contains a graphic part of the game,

## Implemented classes

For every element mentioned there are some classes and interfaces.

### Bumpers

Bumpers are objects which give points when hit, and are upgrade after a determined amount of hits. A bumper implements 
the _Hittable_ and _Bumper_ interfaces. There are two kind of bumpers:

* **Kicker Bumper**
* **Pop Bumper**

For every kind there is a class, and the common behaviours is given by the abstract class _AbstractBumper_.

### Targets

Targets objects which have special interactions when hit. A target implements the _Hittable_ and _Target_ interfaces.
There are two kind of targets:

* **Drop Target**: can trigger the extra ball bonus and triggers the drop target bonus when all drop targets on the current
table are dropped.
* **Spot Target**: triggers the jackpot bonus.

For every kind there is a class, and the common behaviours is given by the abstract class _AbstractTarget_.

### Bonuses

Bonuses are benefits triggered by different situations. A bonus implements the _Bonus_ interface. There are three kind 
of bonuses:

* **Drop Target Bonus**: gives 1 million points and upgrades all bumpers on the current table.
* **Extra Ball Bonus**: adds a ball to the game.
* **Jackpot Bonus**: gives 100,000 points.

For every kind there is a class, and the common behaviours is given by the abstract class _AbstractBonus_.

### Table

A table is a container of game elements like bumpers and targets. The class _GameTable_ models its behaviour, 
implementing the _Table_ interface.

### Game

The controller that will make the pinball work. Its behaviour is detailed in the class _Game_

## Design patterns

To connect the classes, two design patterns were used: the observer pattern and the visitor pattern.

### Observer Pattern

The implementation of this pattern considers that a _Game_ instance must observe _Bumper, Target_  and _Table_ instances.
When one of this objects needs to make a change in another object, they will send a message to the _Game_ instance.

### Visitor Pattern

Whenever an objects needs to make a change to another object, it will notify the _Game_ instance with a message to do 
this change. This message will be a _Visitor_, which will do the change to the current game.

The main objective of the visitors implemented are:

* Trigger a bonus.
* Update the game when a hittable object has been hit.
* Reset targets.

## Graphic Design

For the graphic part of the game it was used the factory pattern, to implement every element of the game: Bumpers,
 Targets, Flippers, Ball and Walls. It has the score and balls left on a side, and the running game on the other.

Every element implemented by the factory has a specific type.

## Features

These are the major and minor features implemented.

#### Separated movement of the flippers
Every flipper has an independent movement, depending on the key pressed: A for the left flipper, and D for the right 
flipper.

#### Color change on target drop
Whenever a target is dropped, it will turn gray, and when its reactivated, it will go back to its original color.

#### Color change on bumper update
Whenever a bumper is upgraded, it will turn yellow, and when its downgraded, it will go back to its original color.

#### Different sounds on hitting different targets or bumpers
Every target and bumper makes a different sound when hit. The targets only make sound when active.

#### Sound when triggering a bonus
Every bonus makes the same sound when triggered.

## Running the game

To run the game, its only necessary to run PinballApp.java, which is the main controller of the game.

### Keyboard Binds

* **A** - Left Flipper
* **D** - Right Flipper
* **N** - New Table
* **SPACE** - Launch Ball

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
