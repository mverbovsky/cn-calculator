# Simple calculator

Simple calculator application which loads instructions from a given file. 
Instructions comprise of a keyword and a number that are separated
by a space per line. Instructions are loaded from file and results
are output to the screen. Any number of Instructions can be specified.
The instructions ignore mathematical precedence. The last instruction
has to be “apply” and a number (e.g., “apply 3”). The calculator is
then initialised with that number and the previous instructions
are applied to that number.

#### Example
```
[Input from file]
add 2
multiply 3
apply 4

[Output to screen]
18
```

Check out the project using a Git clone command:

```
git clone https://github.com/mverbovsky/cn-calculator.git
```

## Build Instructions
Project is tested to build on Java 8. It uses Maven as its build system
and should run on Maven 3.0 and above.

