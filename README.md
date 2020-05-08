# Roborally   [![Codacy Badge](https://api.codacy.com/project/badge/Grade/1d6990f9294f4eb5a56e59736575c6da)](https://www.codacy.com/gh/inf112-v20/wobbly_blues?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=inf112-v20/wobbly_blues&amp;utm_campaign=Badge_Grade)  [![Build Status](https://travis-ci.com/inf112-v20/wobbly_blues.svg?branch=master)](https://travis-ci.com/inf112-v20/wobbly_blues)

## How to play
When a win- or lose condition is met, the program closes and a will print if you've won or lost.

### Key binds:
* Arrow keys => Move UiB-Owl.
* S to switch between robots
* F skyte laser fra skip
* C for å fjerne laserlinjer
* L for a skute laserpunkter
* B for å kjøre belter
* R for rotator pads
* T for skiftenøkkler
* p for push pads
### Win condition:
* UiB-Owl steps on the flags in the right order.

### Lose conditions:
* UiB-Owl steps in a hole.
* UiB-Owl steps outside the board.

## How to run
run Main inside src/main/java/classes/run/

application primarily uses classes 

## Testing
test classes are inside src/test/java/run 

some tests use the GdxTestRunner class to work, inside src/test/java/testrunner 

## Known bugs
Currently throws "WARNING: An illegal reflective access operation has occurred", 
when the java version used is >8. This has no effect on function or performance, and is just a warning.

## Sources
GdxTestRunner: https://github.com/TomGrill/gdx-testing

