## wildlife-tracker
wildlife Tracker app using intelliji (java)

## Description

This a java application that allows a game park manage the animals within it by keeping records on the animals progress .

## Requirements

Java
Gradle

## Setup and Installation requirements

Go to the projects repository
Clone the project

Install gradle
sdk install gradle 5.3.1
Install java
sdk install java
Open the directory in terminal
move to main
cd build/classes/java/main

## Run the following command to execute the Terminal-java application

java App

## In PSQL:
CREATE DATABASE wildlife;
CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int,location varchar,ranger_name varchar);
CREATE TABLE animals (id serial PRIMARY KEY, name varchar);
CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);

## BDD
Test	input	output
