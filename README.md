# Conway's Game of Life
Demo-app for Android application to demonstrate Conway's Game of Life algorithm

##Branches

###master
Master branch contains the full code with the working app

###first-step
First step branch contains the first step.
- Setting up the application
- Deciding working versions (set to 21 only for this demo app, should be working on API version 19)
- Setting up activity with view and buttons for Clear, Step and Run
-- Clear is ment to clear the screen completely
-- Step is ment to do a single iteration
-- Run is ment to do run several iterations until stopped

###second-step
Second step branch is extending view and painting grid.
- The grid is dynamically calculated based on X_PIXEL_STEP and Y_PIXEL_STEP in Grid.class. These two constants can be parameterized later on to enable user to decide grid-sizes and/or number of "cells" (squares) in the view.

###third-step
Third step branch is enabling the GridView to paint and clear cells
- Enabling view for click to make user interactions possible
- Setting up view as global for activity to preserve resources (avoid creating object, but reusing existing view)
- User can click on blank cell to paint it; a.k.a. mark it as "alive"
- User can click on "live" cell to mark it as "dead"
- Activate button for clearing the entire grid