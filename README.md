# Parking Lot
This project demonstrates the sample parking lot application that automates ticket booking in a single floor parking lot.

## System Requirements
1. Linux Operating System
2. maven 3.x
3. Java 8

## Dependencies
Junit - 4.12

## Install and Run
1. #####Add executables to PATH variable
- `export PATH=$PATH:/home/ubuntu1/parking-lot/bin`
2. ##### Make files executable:
- Run `chmod a+rwx` on `parking-lot/bin/setup` and `parking-lot/bin/setup`
3. #####Setup the project
- Go to project folder (parking-lot)
- Run `bin/setup`
4. #####Run Program
- Go to project folder (parking-lot)
- For interactive mode: `bin/parking-lot`
- For file mode: `bin/parking-lot <file>`
## Usage
##### Following are the example commands
```$xslt
create_parking_lot 6
park KA-01-HH-1234 White
park KA-01-HH-9999 White
park KA-01-BB-0001 Black
park KA-01-HH-7777 Red
park KA-01-HH-2701 Blue
park KA-01-HH-3141 Black
leave 4
status
park KA-01-P-333 White
park DL-12-AA-9999 White
registration_numbers_for_cars_with_colour White
slot_numbers_for_cars_with_colour White
slot_number_for_registration_number KA-01-HH-3141
slot_number_for_registration_number MH-04-AY-1111
```