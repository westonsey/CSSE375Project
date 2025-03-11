Part 1. Team Planning of the Week

Dice roll/moving turns/other player class stuff - Weston

Resource cards (adding cards to player hands, letting users play cards, removing cards from players, other resource methods) - Henry

Board/Board-Setup/Board utility functions - Daniel

UI/Checkstyle - Spencer

Part 2. Team Progress of the Week

Henry: Finish features

Spencer: Setup/single-turn use cases

Weston: Definition of each game character/card

Daniel: Set up java methods/classes, and revise plantuml

Part 3. Individual Progress of the week

Team member 1: Spencer Halsey

- PRs created: 1

https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/13

- PRs done (meaning, approved):

[<link>](https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/13)

- PRs reviewed:

N/A (We all just reviewed the design/requirement docs together)



Team member 2: Weston Seybold

- PRs created:

https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/18
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/2
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/3
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/5
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/6
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/12
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/14
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/16
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/17
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/18

- PRs done (meaning, approved):
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/18
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/2
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/3
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/5
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/6
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/12
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/14
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/16
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/17
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/18

- PRs reviewed:

https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/4
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/7
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/13
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/15
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/20
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/21



Team member 3: Daniel Gaull

- PRs created:

* https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/15

- PRs done (meaning, approved):

* https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/15

- PRs reviewed:

* https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/14
* https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/18



Team member 4: Henry Jung

- PRs created:

https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/23
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/7

- PRs done (meaning, approved):

https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/23
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/7

- PRs reviewed:

https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/8
https://github.com/rhit-csse376/project-202430-s1-team04-202430/pull/9

# Intructor feedback
## Progress evaluation :scroll:
Not bad! Make sure to start the Game Setup Phase as soon as possible!

## Suggested next action items :fist:
1. Split the tasks for Game Setup Phase. Document the tasks in the GitHub Projects board.
2. Start the development for the Game Setup Phase. Make sure to ALWAYS do BVA first (document them in docs/bva). Then follow TDD.

## Highlight of your team's work :partying_face:
It seems that everyone is actively participating in the project. Good job!

## Improvements to consider :star:
1. Please format the progress report markdown (md) file properly.
2. Please follow the progress report template and include the planning date and evaluation date in the report.
3. Name the branches properly. I saw many instances of "name-patch-1" as the branch name. That is the default branch name GitHub uses. Please give branch meaningful names.
4. :bangbang::bangbang::bangbang::bangbang::bangbang: There are violations of TDD in the current commit history (for instance, [this commit](https://github.com/rhit-csse376/project-202430-s1-team04-202430/commit/0963827f7e5da00966519800890565ec00cbfa8b)). The development process must follow TDD (meaning, write BVA first, write test cases, write application code -- never commit application code without the corresponding test cases). Exceptions: GUI classes, constructors, and setters. If the project commits are all like the one in the link, the project will not get a higher grade than D. Please read the grading rubrics as a team if you have not.
