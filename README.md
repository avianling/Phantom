Phantom
=======

A simple java 2D game engine

== Changelog:

Currently working on seperating the shapes from the physics engine into a distinct package.
The reasons for this is to increase the cohesion of the physics package - it should all be about physics, not about physics and collision detection and shapes.
Should the shapefactory be in the physics engine? Making shapefactory a singleton.

Might be worthwhile to change the way singletons are treated? For example, shape factory, should be able to get from anywhere?
Started working on testing for the geometry package.