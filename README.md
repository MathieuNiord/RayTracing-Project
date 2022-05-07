# RayTracing-Project

Author : Mathieu Niord | University of Poitiers, Year 2021-2022


### Introduction:

    At the end of the year, we had to realize a ray tracing project as part of our 3D Algorithms course.

    The solution provided was developed in Java under Java 8 and allows to generate an image in .tga format using ray tracing.

    Four scenes are preconfigured:

        - a default scene that uses the example of the three spheres on a checkerboard;
        - a second scene which is an alternative to the first one;
        - a third scene which is composed of seven spheres on a grey checkerboard;
        - finally, a last scene which uses the concept of the Cornell box with a transparent sphere placed in the center
          and three smaller spheres around (the northest one only reflects).


### Hierarchy of the project:

    - raytracing
        |
        | - maths
        |       | - Vec3d           : A 3D vector class with double precision.
        |
        | - rendering
        |       | - Light           : Represents a light source inside a 3D space.
        |       | - Scene           : The scene in where planes, objects and lights are placed.
        |
        | - solids
        |       | - Solid           : A solid is a 3D object that can be intersected by a ray.
        |       | - Plane           : Inherited class from Solid which permits the generation of a plane according its normal.
        |       | - CheckerBoard    : Inherited class from Plane which permits the generation of a checkerboard pattern.
        |       | - Sphere          : Inherited class from Solid which permits the generation of a Sphere according its center point.
        |
        | - utils
        |       | - Color           : Colors and arithmetics of Colors.
        |       | - JavaTGA         : .tga generator (author : P. Meseure based on a Java Adaptation of a C code by B. Debouchages)
        |       | - SceneLoader     : Represents a loader of scenes which loads a scene. It is called during the execution.
        |
        | Main.java                 : Main program. Parse command line arguments, run the computation of the image and save it.


### Command line Usage:

    Compilation (in "src/raytracing/"): javac -d . ./maths/*.java ./utils/*.java ./solids/*.java ./rendering/*.java Main.java
    Run program: java raytracing.Main

    Options:
        -w <width>      : Width of the output image.
        -h <height>     : Height of the output image.
        -d <depth>      : Maximum ray depth.
        -z <zoom>       : Zoom factor.
        -o <output>     : Output file name.
        -s <scene>      : Scene number.
        --help          : Print help.

    Scenes recommanded configurations:
        - Default               : -z -1.5
        - Lesson example n°1    : -s 1 -z -1.5
        - Lesson example n°2    : -s 2 -z -1.5
        - Cornell Box           : -s 3 -z -0.2 -w 1080 -h 1080
  
