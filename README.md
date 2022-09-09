# To_visit_android_app

## Description

Android application to keep track of places to visit, made as a project to keep track of places to visit in the Stockholm area in a more fun way than in an excel sheet.
It is an extension to my already existing project of having a webapp to keep track of these places.

The RestAPI used in this application has been made by me, in JavaScript using NodeJs, and the source code can be found [here](https://github.com/elisakaisa/ToVisitWebApp).

A [webapp](https://pacific-spire-62523.herokuapp.com/) version also exists, in JavaScript using React.js, and the soure code can be found [here](https://github.com/elisakaisa/ToVisitWebApp_clientSide).

## Installation

The app can be used in 2 ways:

- The complicated way, that allows the user to create a profile and store their own data in the app: 
  - this requires the restApi needs to be set up first, how to do that can be found in the [README](https://github.com/elisakaisa/ToVisitWebApp#tovisitwebapp) of the restApi
  - then the app can be installed on the phone
- The easier way, that allows the user to view the data I have stored in my database, but not to modify it:
  - simply installing the app

To download the app: android studio is needed

    git clone https://github.com/elisakaisa/To_visit_android_app.git

Then run gradle sync.

## Implementation

The app is built on fragments and follows an MVVM pattern. Networking is handled by volley. 
The visits are fetched from the api and viewed in a recyclerView. 
Currently to update the data from the api (as in to make a new GET request), a SwipeRefreshLayout is used.
The information of the logged in user is stored in the SharedPreferences, so that the user doesn't have to log in every time.
The SharedPreferences are deleted when the user logs out.

## Known issues and improvements

Known issues:

- which fragment is selected is not always correctly displayed in the bottomNavigation
- ... (work in progress, so prabably many more)

Improvements in the works

- Individual visit view
- Filter
- Replicate functionalities of the webapp
- add statistics about the visits

Possible improvements in a less nearby future

- link with google maps api to view places on a map
- tests
- ...

## Authors

Elisa Perini [github](https://github.com/elisakaisa) | [linkedIn](https://www.linkedin.com/in/elisa-perini-2759ba227/)

