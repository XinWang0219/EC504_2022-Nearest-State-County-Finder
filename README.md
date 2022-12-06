# EC504_2022-Nearest-State-County-Finder
Boston University Electronic and Computer Engineering EC504 2022 Project

## Project: Nearest State/County Finder

## Contributor
- Team 2
  - Xin Wang
  - Jianxiao Yang
  - Yuxi Ge
  - Chenjiayi He
  - Shangzhou Yin

## Introduction
Given a huge number of reference points in the US. The project system will first load the points in an `Efficient Nearest Neighbor Search Amenable Data Structure`. Then Users can get K nearest states and countys from the point by entering the `latitude`, `longitude`, and `K`.

## Distance Equation
$$x = (&lambda;2 - &lambda;1) * cos((&phi;1+&phi;2)/2);$$
$$y = (&phi;2 - &phi;1);$$
$$distance = R * \sqrt{x * x + y * y};$$
where:
  - $&phi;$ is latitude
  - $&lambda;$ is longitude 
  - R is earth's radius (6371km)
  

## Data formate

|State|County|Latitude   |Longitude  |
|-----|------|-----------|-----------|
|AR   |Benton|36.4805825 |-94.4580681|
|AZ   |Apache|36.4611122 |-109.4784394|
|...  |...   |...        |...        |

## Getting Started
### Requirements
`g++ (Ubuntu 9.4.0-1ubuntu1~20.04.1) 9.4.0`\
`javac 1.8.0_352`\`
`GNU Make 4.2.1`
### Makefile
- compile all codes\
`make`
```
(base) ~:/EC504_2022-Nearest-State-County-Finder/src$ make    
g++ -g -std=c++1y   -c -o kNearestCountyFinder.o kNearestCountyFinder.cpp
g++ -g -std=c++1y  kNearestCountyFinder.o   -o KnearestCounty
javac main.java Point.java KD_tree.java
```
The generated application would be "KnearestCounty"
- clean all median files and binary files\
`make clean`
```
(base) ~:/EC504_2022-Nearest-State-County-Finder/src$ make clean
rm -f KnearestCounty kNearestCountyFinder.o main.class Point.class KD_tree.class core 
```
### Start Application
After comile the all files by make,\
Run the application "KnearestCounty" by Command\
`./KnearestCounty`
```
(base) ~:/EC504_2022-Nearest-State-County-Finder/src$ make
g++ -g -std=c++1y   -c -o kNearestCountyFinder.o kNearestCountyFinder.cpp
g++ -g -std=c++1y  kNearestCountyFinder.o   -o KnearestCounty
javac main.java Point.java KD_tree.java
(base) ~:/EC504_2022-Nearest-State-County-Finder/src$ ./KnearestCounty
Welcome to use Nearest State/County Finder System
    Please excute following commands:
    -load                               # load data
    -disp                               # display load data
    -search                             # search for nearest k number of counties by input latitude and longitude
    -exit                               # exit application
```
### Load data and Display
After running the application, the terminal will shows hints for operations:
```
    "Welcome to use Nearest State/County Finder System\n\
    Please excute following commands:\n\
    -load                               # load data\n\
    -disp                               # display load data\n\
    -search                             # search for nearest k number of counties by input latitude and longitude\n\
    -exit                               # exit application\n";
```
Type `-load` and push the 'Enter' Key, the application will load all the information of counites in United States.\
Type `-disp` and push the 'Enter' Key, the application will display every counties' name, state, latitude and longitude, such as:
```
County: "McPherson" State: "NE" latitude: 41.568199 longitude: -101.060402

County: "Kenedy" State: "TX" latitude: 26.928499 longitude: -97.701698

County: "King" State: "TX" latitude: 33.616501 longitude: -100.255798

County: "Loving" State: "TX" latitude: 31.849300 longitude: -103.580002

...
```
### Search State/County
In this project, there are two different way to search k nearest counties: KNN and KD-tree.\
After starting the appliaction and load the data,\
Type `-search` and push the 'Enter' Key, the application will request for search method name (knn / kdt), input latitude , input longitude, and the number of k value (1-10):
```
Welcome to use Nearest State/County Finder System
    Please excute following commands:
    -load                               # load data
    -disp                               # display load data
    -search                             # search for nearest k number of counties by input latitude and longitude
    -exit                               # exit application

-search
Enter 'method', 'latitude', 'longitude', 'k'
knn
31.845
-103.58
10
Search Time Cost: 2.6022 ms
0: county: "Loving" state: "TX"
1: county: "Winkler" state: "TX"
2: county: "Ward" state: "TX"
3: county: "Reeves" state: "TX"
4: county: "Eddy" state: "NM" 
5: county: "Ector" state: "TX"
6: county: "Culberson" state: "TX"
7: county: "Andrews" state: "TX"
8: county: "Lea" state: "NM"
9: county: "Crane" state: "TX"
```
or
```
Welcome to use Nearest State/County Finder System
    Please excute following commands:
    -load                               # load data
    -disp                               # display load data
    -search                             # search for nearest k number of counties by input latitude and longitude
    -exit                               # exit application

-search
Enter 'method', 'latitude', 'longitude', 'k'
kdt
31.845
-103.58
10
run command kdt
name:"Crane" x:31.428 y:-102.515
name:"Lea" x:32.792 y:-103.412
name:"Andrews" x:32.305 y:-102.637
name:"Culberson" x:31.447 y:-104.517
name:"Ector" x:31.869 y:-102.542
name:"Eddy" x:32.471 y:-104.304
name:"Reeves" x:31.323 y:-103.693
name:"Ward" x:31.509 y:-103.102
name:"Winkler" x:31.85 y:-103.048
name:"Loving" x:31.849 y:-103.58
search time:8 ms
```

### Exit Application
Type `-exit` and push 'Enter' Key to exit the program.

## License
[MIT License](LICENSE)

## Acknowledges

