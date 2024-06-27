# Sokoban Game Implementation

This project was developed as the final assignment for the course [Advanced Object Oriented Programming](https://www.hh.se/sitevision/proxy/student/innehall-a-o/kursplan.html/svid12_464ca102168ed1f8d3b1293f/752680950/se_proxy/utb_kursplan.asp?kurskod=DT4014&revisionsnr=7&format=pdf&lang=en).

## Project Overview

We were tasked to implement the game Sokoban. For a detailed description of the game, please refer to its [Wikipedia page](https://en.wikipedia.org/wiki/Sokoban).

### Requirements

The project had the following requirements:
1. The game had to be represented by a model.
2. The game had to have a graphical representation of the game model.
3. We had to have a debug window.
4. The game had to support various methods of input.
5. The game had to be implemented using various design patterns such as:
    - Model-View-Controller
    - Observer
    - Strategy

### Additional Functionalities

Besides the minimum requirements, we added the following functionalities:
1. Generating sound for different events occurring in the game.
2. The ability to save and load the game using Java serialization.
3. Creating a framework to build any tile-based game upon.

### Implementation Details

The end product is a playable Sokoban game, supporting both keyboard and GUI-based controls. The design was heavily influenced by the course format and the object-oriented techniques we learned.

#### Design Patterns Used
- **Model-View-Controller**: For separating the game logic from the user interface.
- **Observer**: To update the GUI and debugging terminal after each interaction.
- **Strategy**: To handle different control methods and game algorithms.

### Testing

We used JUnit for automated testing, focusing on game model functionality like movement, collision, and buttons. Manual testing was also conducted to ensure robustness.

### Results

We successfully implemented a working version of Sokoban, meeting all specified requirements. The game features:
- Sound effects based on game events.
- A debugging terminal that prints information after each interaction.
- A GUI for playing the game.
- A modular design that allows easy addition of new functionalities and control methods.

**For a more detailed explanation of the project, please refer to the "Project Report.pdf" included in this repository.**

## Using This Repository

To use this repository:
1. Open IntelliJ.
2. Create a new Maven Project using the latest Java JDK.
3. Run `git init`.
4. Clone the repository.
5. Hopefully, everything works.

We hope this project provides a solid foundation for building tile-based games using object-oriented programming principles.
