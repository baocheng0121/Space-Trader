# SpaceTraders API
> SpaceTraders is a multiplayer space trading game built on REST endpoints. The platform allows users to use any programming language to play the game, create a custom client, or automate your fleet of ships.


## Table of contents
* [Setup](#setup)
* [Operations](#operations)
* [Contact](#contact)

## Setup
>To setup the game, you will first need to pull it to your local machine from the github repository -- make sure that your repo has been initialized to git

`git pull https://github.sydney.edu.au/bwan3675/SCD2_2021_T3.git`

>After the pulling action, you will next need to setup the automation by entering the following command:

`gradle build`

Note that there are two working mode of the game, online and offline, you could receive the actual response from the web server in the online mode, while all the responses in the offline mode are preprogramed.

Specify the working mode you would like to play in when you are starting the game by entering the following commnad:

Online:

`gradle run --args="online"`

Offline:

`gradle run --args="offline"`

Aftet that, the game GUI will display, and you can start playing...


## Operations
There are 19 operations designed in the game:

* The user can see whether the server is currently active
* A new user can obtain and store credentials (these can just be stored in RAM, no need for saving to disk)
* An existing user can enter and store their existing credentials
* The user can see their info
* The user can list available loans and their details
* The user can obtain a loan
* The user can list active loans
* The user can pay off a loan
* The user can list available ships and their details
* The user can purchase a ship
* The user can list their ships and their details
* The user can purchase ship fuel
* The user can view the marketplace details for a given location
* The user can purchase goods from the marketplace
* The user can list nearby locations and their details
* The user can create a flight plan and journey to a nearby location
* The user can view their current flight plan (this does not need to automatically refresh, manual is ok)
* The user can sell goods to the marketplace
* These features should be available ‘half-way’ – that is, if a user closes the application and re-opens it, they should be able to pick up where they left off. This must be based on the server state, not any locally saved data.

## Contact
Created by bwan3675@uni.sydney.edu - feel free to contact me!
