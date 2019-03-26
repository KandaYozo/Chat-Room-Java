# Chat-Room-Java
A single chat room with multi users, implementation is done using sockets and threading in Java

This project is part of the CMPN306 : Advanced Programming Techniques course at the Faculty of Engineering Cairo University, Spring 2019

## Editing notes

1. All inlcudes must exist or else errors will need to be handled manually
2. Make sure that the closing part of server + client are always there so the cpu is not consumed
3. Make sure to make the thread sleep when waiting for recieving because it will consume cpu 
4. Comment as much as possible when writing any line of code, because this is the first time we are using sockets so we need to help each other to understand the functions we are implementing
5. If you used a site to help you with implementing or importing anything please add the link to that page in the code file you used your import in.
6. Try to use eclipse when editing because we will be working with eclipse this time

## Github Notes

1. To clone repository use git bash command:
   `git clone <link>`
2. If you want to get updated version use the bash command:
   `git pull`
3. If you are trying to push follow this steps:

  1. `git status`: to check the updates done
  2. `git add .` : to add all your modified data
  3. `git commit -m "discription"`: to commit your update, please write a short comment about what you modified
  4. `git push`

## Running on IntelliJ

1. Navigate to `File → New → Project` from Existing Sources
2. Select your Eclipse project directory
3. When the Import Project wizard opens, select the `Create project from existing sources` option
4. Follow the on-screen instructions to continue

### Please note
This is not the best version due to that this is the first time we use sockets and threading feel free to modify or give an advice about any part of the code