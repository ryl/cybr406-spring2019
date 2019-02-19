# Git

## Submitting Homework

When you start your homework, add me as a contributor to your project.

* Add user "lowryrs" on Heroku.
* Add user "ryl" on GitHub.
* Please perform both these steps if you deploy through GitHub. Adding me on
  Heroku helps me find your projects from one central location.

Once you finish your homework you must commit and push your changes to a remote
repository. Otherwise, I can't grade your homework!

```
# Prepare your local changes for submission.
git add .
git commit -m "Finished my homework!"

# If you are hosting your project on Heroku.
git push heroku master

# If you are hosting your project on GitHub.
git push origin master
```

## Clone Class Project - Heroku

Follow these steps if you want to host your project's code on Heroku.

```
# Clone using HTTPS and name the remote repository "cybr406"
git clone https://github.com/ryl/cybr406-user.git -o cybr406

# Clone using SSH and name the remote repository "cybr406"
# Only use this option if you have configured SSH on GitHub.
git clone git@github.com:ryl/cybr406-user.git -o cybr406

# List the remote repositories your local copy is connected to.
git remote -v

# Add your personal project's remote Heroku repository
heroku login
heroku git:remote -a lowryrs-post

# Push code your personal remote Heroku repository.
git push heroku master

# Pull code from class remote GitHub repository.
git pull cybr406 master
```

## Clone Class Project - GitHub

Follow these steps if you want to host your project's code on GitHub.

```
# Clone using HTTPS and name the remote repository "cybr406"
git clone https://github.com/ryl/cybr406-user.git -o cybr406

# Clone using SSH and name the remote repository "cybr406"
# Only use this option if you have configured SSH on GitHub.
git clone git@github.com:ryl/cybr406-user.git -o cybr406

# List the remote repositories your local copy is connected to.
git remote -v

# Add your personal project's remote GitHub repository
git remote add origin https://github.com/ryl/test.git

# Push code your personal remote Heroku repository.
git push origin master

# Pull code from class remote GitHub repository.
git pull cybr406 master
```
