# Paimon2.0

## Your Personal Genshin Impact Helper

Genshin Impact is a popular RPG video game featuring collectible characters, weapons,
artifacts, level-up materials, and more. Like any other game, Genshin Impact has
many stats, strategies, and time-sensitive in-game activities that are hard to keep
track of. As you get into late-game, all these elements are crucial to keeping up with
to be successful in the game. That's where my app comes in.

*Paimon2.0* Acts as an online tracker for all your Genshin Impact needs. With this app.
you will be able to make lists of your characters, access their stats,
keep track of in-game goals, and more. Here is a more in-depth list of all the
functions that this app will have by the end of this term:

**CURRENT FUNCTIONS**
- Make lists of characters and assign it a name
    - This can help keep track of characters you own, characters you want,
      and teams that you want to make
- Create a new character with traits and stats
    - This can keep track of your character stats and additional information
- Add and remove created characters to lists    
- View created lists
    - see which characters are in your created lists
      
**FUTURE FUNCTIONS**
- Access character information
    - Once a character is added, you will be able to add additional stats, traits,
      and notes about a character
- Keep track of in-game activity
    - You can make to-do lists of goals you want to achieve in the game. For example,
      you can keep track of weekly bosses, domains, battle pass, and resin
      
**AUDIENCE**

This app is made for players of Genshin Impact at **any** level. Whether you're at
Adventure Rank 55, or just started the game, it is always good to keep a log of your
goals and in-game activity to be organized and efficient.

**BACKSTORY**

I wanted to create this project because I am an avid player of Genshin Impact and
always find that there isn't one centralized place where I can easily keep track
of my Genshin Needs. With this digitized solution, I don't have to rely on
sticky notes or loose papers.


## Phase 1 User Stories

- As a user, I want to be able to create a new list/ team of characters
- As a user, I want to be able to assign a name to a new list/ team of characters
- As a user, I want to be able to create a new character and assign traits to it  
- As a user, I want to be able to add characters to my created lists
- As a user, I want to be able to remove characters from my created lists
- As a user, I want to be able to view all my lists/ teams created

## Phase 2 User Stories
- As a user, I want to be able to have the option of saving when I quit the application
- As a user, I want to have the option to save in the option menu without quitting
- As a user, I want to have the option to load my previous lists

## Phase 3 User Stories
- As a user, I want to be able to access the application through a GUI

## Phase 4 User Stories
- As a user, I want to be able to see a log of all the events related to the model package that occured
printed to the console after I close the application

## Phase 4: Task 2
Example of event log:


Wed Mar 30 21:10:58 PDT 2022

Diona added to list Healers

Wed Mar 30 21:10:58 PDT 2022

Diona added to list idklist

Wed Mar 30 21:11:01 PDT 2022

Bennett removed from list Owned Characters

Wed Mar 30 21:11:06 PDT 2022

Diona removed from list Healers

No events are logged when the program runs if the user decides to not perform any actions 
with the two actions that are being logged. For an example, a user might just want to view 
their lists instead of adding or deleting from them. 

## Phase 4: Task 3
I think that my design for this application is very simple and basic. I don't think there 
is anything structurally wrong with it, but in the future if I had more time I would make use of
the abstract and interface classes more to make my application have better cohesion and coupling, and just
look better overall. For example, I would make Character an abstract class, and I would have the subclasses 
as the different characters with different visions. This way I wouldn't have to use an enumeration for the 
different visions. Other than that, everything else I would change would have to do with adding more things,
not refactoring. 