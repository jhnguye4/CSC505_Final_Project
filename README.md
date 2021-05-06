# CSC505_Final_Project
CSC 505 Final Project: A Comparison of Hashing Techniques using Spell Checking

## Table of Contents

- [Table of Contents](#table-of-contents)

- [Motivation](#motivation)
- [Specifications](#specifications)
- [Experiment](#experiment)
- [Running Program](#running-program)
- [Collaborators](#collaborators)

## Motivation 
Hash tables are most commonly used for implementing in-memory tables to improve searching efficiency with the trade-off of using more memory to increase speed. The average time to search has O(1) complexity.If a set of keys are finite and known in advance, a hash table can be implemented where each key is mapped to a unique index, where no two keys will hash to the same value.

## Specification
Studies have shown that the 25,000 most common English words account for more than 99% of all English written text. Our goal is to compare various hash functions and collision resolution methods and study their performance. The metrics that we will be analyzing will be Average Number of Probes, Runtime(10ths of a second), and Load Factor, percentage of table that is full.

The components we will be implementing are:
* Hash Codes - Polynomial, Additive, Cyclic Shift
* Compression Functions - Multiplication, Division, Multiply add Divide 
* Collision Resolution Methods - Separate Chaining, Coalesced Chaining, Linear Probing

** For more information please refer to our report or slides
## Experiment 
For our experiment we will be using a dictionary of 25,000 words on various text sizes to test four hypotheses:

Hypothesis 1:
* A smaller load factor, i.e. the table is not full, will result in lower probe average and faster run time.

Hypothesis 2:
* Cyclic shift hash code and polynomial hash code will have significantly lower number of probes and run times compared to additive hash code.

Hypothesis 3:
* Separate Chaining and Coalesced Chaining collision methods will generate a lower probe average and run time compared to Linear Probing.

Hypothesis 4:
* The performance across the different hash codes will remain the same regardless of which compression function is used.

## Running The Program
The instructions mentioned below will help compile and run the program.

`javac Hash_One.java`

`java Hash_One`

There will then be five prompts:
* "Enter a Dictionary:"
* "Enter a Text File:"
* "Enter a Hash Code(polynomial(p), additive(a), cyclic(c)):"
* "Enter a Compression Function(multiplication(m), multiply add divide(mad), division(d)):"
* "Enter a Collision Resolution Method(separate(s), coalesced(c), linear(l)):"

Example: 
* "Enter a Dictionary: ../bin/dictionary.txt"
* "Enter a Text File: ../bin/harrypotter.txt"
* "Enter a Hash Code(polynomial(p), additive(a), cyclic(c)): p"
* "Enter a Compression Function(multiplication(m), multiply add divide(mad), division(d)): m"
* "Enter a Collision Resolution Method(separate(s), coalesced(c), linear(l)): s"

### Inputs: 

* alice            = 25,000 words
* thehobbit        = 100,000
* searchoflosttime = 200,000
* orderofphoenix   = 300,000
* stormofswords    = 450,000
* harrypotter    = 510,000 words

### Outputs: stdout

## Team Members 

* Jonathan Nguyen
* Justin Kirscher
* Yi Qiu
* Rahul Ramakrishnan
