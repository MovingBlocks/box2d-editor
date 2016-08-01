Physics Body Editor
==========
<p align="center"><img src="readmeImgs/slide-physics-body-editor.jpg" alt="Physics Body Editor"/></p>

Introduction
--------

Physics Body Editor is all about making your life easier with physics engines. Specifically, it targets the creation of collision shapes for your game objects: we call them rigid bodies. It can also let you combine these objects together and link them with joints to create complex objects: we call them dynamic objects.

The problem we want to solve is as follows: have a look at the image on the right, I wanted to create a bottle that can hold objects inside it. At first, I used a drawing tool to draw my shape points over the bottle image, and I reported the values in my game. For each point, I had to convert from pixel units to world units of course. Boring. Oh, and guess what? It didn’t work! Indeed, physics engines usually only work with convex polygons! On to decompose the shape into multiple convex polygons by hand… More than boring. And of course, each time I wanted to do a little change, I had to go over the same process.

I guess you understand why such automated tool can be handy: it converts pixel units to world units, decomposes the shape into multiple convex polygons, and lets you test the result directly!
Features

* Automatically decomposes concave shapes into convex polygons,
* Automatically traces your images if needed,
* Supports multiple outlines for a single body,
* Supports polygon and circle shapes,
* Reference point location can be changed,
* Visual configurable grid with snap-to-grid option,
* Built-in collision tester! Throw balls at your body to test it,
* Loader provided for LibGDX game framework,
* Simple export format (JSON), to let you create your own loader for any framework in any language.

<p align="center"><img src="readmeImgs/pbe-workflow.jpg" alt="From the editor to the game"/></p>
<p align="center"><img src="readmeImgs/pbe-02.jpg" alt="Features!"/></p>

Technologies
--------

The application uses the following technologies:

* [LibGDX](https://github.com/libgdx/libgdx), the most awesome game dev library, for the rendering of the canvas area,
* [Box2d](http://box2d.org/), as the embedded physics engine (available in Java thanks to libGDX),
* [Farseer engine](http://farseerphysics.codeplex.com/), for its auto-trace and polygon decomposition algorithms.

Getting Started
--------
* [Wiki](https://github.com/MovingBlocks/box2d-editor/wiki)
* [YouTube](https://youtu.be/KASY91EiTXQ)
