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

  > After the credentials are verified, there will be an "active" button on the left top cornor of the main page. Click on the "active" button, you will receive a message that indicates the server status.
  
  > "spacetraders is currently online and available to play" indiates that the system is alive.
  
* A new user can obtain and store credentials (these can just be stored in RAM, no need for saving to disk)

  > New user can enter their username in the first GUI page, and the servor would generate a token for it, user should take it down. After the token is generated, the game will redirect to the main page, where the user would be able to check his or her initial user status. If a message saying "User exists! ...", then it indicates that the username has been created before, and, sadly, you will have to pick another one.

* An existing user can enter and store their existing credentials

  > User can enter their username in the first GUI page, and the servor would determine if he or she is an existing user, if true, then it will redirect to the token entering window, where the user is required to enter the token. If the token is verified, then the user will be redirected to the main page.

* The user can see their info

  > User can see their username and credits data on the main page. Ships data can be retrieved by clicking on the "ships" button, and the loans data can be retrieved by clicking on the "loans" button.

* The user can list available loans and their details

  > User can retrieve data about loans that are available by clicking on the "Available Loans" button.

* The user can obtain a loan

  > User can request a loan by entering the Loan Type and then clicking on the "Request New Loan" button on the "Available Loans" page. A message could be received to indicating whether the request is successful.

* The user can list active loans

  > All the active loans data will be listed on the "Your Loans" page.

* The user can pay off a loan

  > User can pay off a loan by entering the Loan ID and then clicking on the "Pay Off A Loan" button on the "Your Loans" page.

* The user can list available ships and their details

  > User can retrieve data about ships that is available for purchasing by clicking on the "Ships" button on the main page, and then clicking on the "View Available Ships" button, which will redirect to the .
  
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
