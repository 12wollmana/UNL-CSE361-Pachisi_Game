# Pachisi
<em>Jacob Bohac, Joshua Grossman, Aaron Wollman</em>

## Description
This project was written using libGdx libraries which allowed us to write a cross-platform application.

The project is seperated into 3 main folders. The Pachisi folder holds the majority of the code, including the logic, GUI, and game engine.
The pachisi-android and pachisi-desktop folders only contain lauchers for their respective platforms.

Regardless of the platform the system starts in the launcher which calls the create() function in the Pachisi game class. This sets up the main menu screen. 
From this point the system is structured so that the buttons contained on a screen allow navigation to one of the other screen classes. Each of the screens is its own seperate class file that is built up using libGdx Table and button libraries.

The GameSetupScreen creates the actual game and initializes the GameEngine, Players, Spaces. The way users interact with the is through Stages and Actors which are specific to the libGdx platform.
This user interaction implements its own listeners and handlers which are not on the Android platform.

In order to run the program in a desktop environment, the main.java in the android-desktop needs to be compiled.

In order to run the program in an android environment, the Android Development Kit is required to compile for an Android target device. This can then either be uploaded to an actual android device or be run on an emulator.

## Notes
I haven't run this code in years, so it might not work anymore.
