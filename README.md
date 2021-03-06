# SpaceTraders API
> SpaceTraders is a multiplayer space trading game built on REST endpoints. The platform allows users to use any programming language to play the game, create a custom client, or automate your fleet of ships.


## Table of contents
* [Setup](#setup)
* [Operations](#operations)
* [Inspiration](#inspiration)
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

  > User can purchase a ship by entering the Location and Type of the ship, and then clicking on the "Buy" button. If any of the argument is not valid or missing, a message will be received.

* The user can list their ships and their details

  > All the data of ships that are owned by the user are demonstrated on the "Your Ships" page. To retrieve the Good information about a certain ship, user have to enter the ship id and then click on the "Check Goods" button. Once the input ship Id is valid, then all the good in the cargo will be demonstrated on the "Cargo" page.

* The user can purchase ship fuel

  > User will first need to enter the "Trade" page by entering a "ship Id" and then clicking on the "Trade" button. After that, a purchase could be made by entering "FUEL" as the Item name, as well as the Quantity to purchase. A message indicating whether the trade is successful would then be received.

* The user can view the marketplace details for a given location

  > Once the user enter the "Trade" page, all the market places data will be listed.

* The user can purchase goods from the marketplace

  > User will first need to enter the "Trade" page by entering a "ship Id" and then clicking on the "Trade" button. After that, a purchase could be made by entering an Item name, as well as the Quantity to purchase. A message indicating whether the trade is successful would then be received.

* The user can list nearby locations and their details

  > User could have nearby locations information listed, once he or she enter the "Nearby Market Place" page by clicking on the "View Nearby Marketplaces".

* The user can create a flight plan and journey to a nearby location

  > When user is visiting the ser could create a flight plan to the a nearby location by entering the "destination" and then clicking on the "Submit Flight Plan" button. These operations should be taken place on the "Nearby Market Place" page, which could be visited from "Market Place" page by clicking on the "View Nearby MarketPlaces" button.

* The user can view their current flight plan (this does not need to automatically refresh, manual is ok)

  > On the "Nearby Market Places" page, User can view a current flight plan by entering the Flight plan and clicking on the "Check Flight Plan" button. If the flight plan ID does not exists, a message will be sent to notify the user.

* The user can sell goods to the marketplace

  > On the "Market Place" page, user can sell their goods by entering the Item name and the Quantity, and then clicking on the "Sell" button. If the trade is not successful, a message will be sent to notify the user.

* These features should be available ???half-way??? ??? that is, if a user closes the application and re-opens it, they should be able to pick up where they left off. This must be based on the server state, not any locally saved data.


## Inspiration

The major idea of the java.swing scaffold code is from University of Sydney-SOFT2412-the vending machine project, which is carried out by bwan3675 and his 4 other teammates, which could be reviewed via the link 
> https://github.sydney.edu.au/SOFT2412-2020S2/T18A_Group1_Asm2.git

## Contact
Created by bwan3675@uni.sydney.edu - feel free to contact me!
