Quick Instructions / Help
=========================
This **README** will try to describe the general structure and idea behind the repository.
The root folder of the repository is an [Eclipse] workspace named *exchange_mvk13*. All Projects and Documentation will be saved within this workspace.

Setup
-----
1. [Download and Install Eclipse]
2. [Download and Install ADT]
  * Remember to Install all necessary Build-Tools from the Android SDK Manager
3. Clone Git Repository to Your HDD and Setup the Remote Repo
  * I recommend using GitHub's App ([Mac][MacGit] or [Windows][WinGit])
4. Start an Eclipse session with the new workspace
![alt text][workspaceimg]

Project Naming
--------------
All project folders are prefixed dependent on the project type.

* Normal Projects have the prefix *app_**
* Library Projects have the prefix *lib_**
* Test Projects have the prefix *test_**

Documentation
-------------
The JavaDocs that are generated will be put in the Documentation folder and will be named *doc_** for each project.

Example Project
---------------
The **Example** Project is there to showcase the structure. **DO NOT** edit them.

.gitignore
----------
The *.gitignore* file **MUST NOT** be edited. It currently contains all temporary OSX,Linux and Windows files as well as all unnecessary files for Android Development with Eclipse. If any alteration is required contact Axel Boldt-Christmas.

[Eclipse]: http://www.eclipse.org/
[Download and Install Eclipse]: http://www.eclipse.org/downloads/
[Download and Install ADT]: https://developer.android.com/sdk/installing/installing-adt.html
[MacGit]: http://mac.github.com/
[WinGit]: http://windows.github.com/
[workspaceimg]: http://i.imgur.com/T33jZ7u.png "Select Eclipse Workspace"