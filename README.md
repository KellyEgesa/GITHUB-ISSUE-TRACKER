# GITHUB ISSUE TRACKER

#### An android application that allows users to search github usernames, get their repositories and view the issues in the repositories.

#### By **Bartholomew Kelly Egesa**

## Description
An android application which a user can user can use to find the repositories username. It displays the users bio, number of repositories. When you click on a repositories it takes to the next activity that displays all the issues in the repository. It displays the date and the comments for each individual issues.

## Behavior Driven Development

| BEHAVIOR:Our program should handle |                  Input Example When it receives                   |           Output Example It should return           |
| ---------------------------------- | :---------------------------------------------------------------: | :-------------------------------------------------: |
| Search a User                           |        Inputs:-userName:"KellyEgesa"|              Navigates to the next activity and displays KellyEgesa repositories              |
| Search a User                           |    Inputs:-userName:"Kelly Egesa"|       Will display a toast saying user not found because no such user exists in Github         |
| Scroll repositories                           |               Scroll in the Repository activity           |               The repositories are displayed with the updated date, languages and description if available                 |
|Click on a repository                          |   A repository is clicked on          |      Displays the date, a search bar and filter options                 |
|Scroll issues                         |              Scroll in the issue activity        |                The issues are displayed with the issue title, status, user and open date id available          |
|Click on date                         |             Date text is clicked       |                The issues are filtered based on the date option clicked          |
|Scroll FilterBy                         |              FilterBy is clicked        |                The issues are filtered based on filterby option clicked         |
## Known Bugs

There are currently no known bugs

## Setup/Installation Requirements
- Setup git
- Open the terminal application by either clicking on the terminal icon or by clicking Ctrl + alt + T.
- Create a new folder called github-issue-tracker by pressing mkdir football-app and pressing enter.
- Navigate to github-issue-tracker by pressing cd github-issue-tracker and pressing enter.
- Go to KellyEgesa github user name on the browser, click on repositories, Click on github-issue-tracker then click on clone or download option
- Copy paste the given Url
- Press git clone plus the url on the terminal then press center.
- Inorder to run the application one needs to retrieve their own individual access tokens on github. It is then placed in gradle.properties as "GITHUB_API_KEY:***Your individual api Key***"
## Running Tests
- Once the instructions under Setup/Installation Requirements are done. Right click on app/src/androidTest/java/com/savannahInformatics/githubissuetracker and click run to run the instrumentation tests.
- Right click on app/src/test/java/com/savannahInformatics/githubissuetracker and click run to run the unit tests.

## Technologies Used
### DEVELOPMENT
- JAVA
- ANDROID
- XML
### TESTING
- ROBOELECTRIC
- ESPRESSO
- JUNIT
### APIS CALL
- RETROFIT

## Support and contact details
You can contact me via Email at kelly.egesa@gmail.com or via +254726359282.

### License

_M.I.T_
Copyright (c)2021 **KELLY EGESA**
