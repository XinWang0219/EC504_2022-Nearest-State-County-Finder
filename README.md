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
### Load data
### Search State/County

## License
## Acknowledges

