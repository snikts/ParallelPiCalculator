# Parallel Pi Calculator

Parallel Pi Calculator is a Java program that calculates the digits of Pi using a number of parallel running threads equal to the number of available processors the current machine has. This program will calculate up to 1024 digits of Pi.

## Running the Parallel Pi Calculator

Open a command line prompt at the directory containing ParallelPi.java. Run the following command:

```bash
javac ParallelPi.java
```
To calculate all the digits of Pi in order, run:
```bash
java ParallelPi 
```
To calculate the digits of Pi in a random order, run:
```bash
java ParallelPi -random
```
To calculate the digits of Pi in reverse order, run:
```bash
java ParallelPi -reverse
```
