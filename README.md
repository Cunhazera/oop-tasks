# Task manegement

[![Build Status](https://travis-ci.org/cunhazera/oop-tasks.svg?branch=master)](https://travis-ci.org/cunhazera/oop-tasks)

[![Coverage Status](https://coveralls.io/repos/github/cunhazera/oop-tasks/badge.svg?branch=master)](https://coveralls.io/github/cunhazera/oop-tasks?branch=master)


## Do you wanna run it? Follow these steps:

### 1) Install maven

`sudo apt-get install maven`

### 2) Download Wildfly 10

[Just click here](http://download.jboss.org/wildfly/10.0.0.Final/wildfly-10.0.0.Final.tar.gz)

Go to the folder where you saved the WildFly server and give it the permission to run: `chmod +x folder`

### 3) Clone the project

`git clone https://github.com/cunhazera/oop-tasks.git`

### 4) Run the maven command at the project directory

```
cd oop-tasks
mvn clean install
```

### 5) Deploy the WAR file in the server

When the maven command ends, it generates a file called `task-0.0.1-SNAPSHOT.war`, so you have to copy it to the server

cd into the target folder inside the `oop-tasks` folder

`cp /target/task-0.0.1-SNAPSHOT.war ~/wildfly-10.0.0.Final/standalone/deployments/`

Verify that the path to your Wildfly is correct, the path I wrote is just an example.

Check that the .war file is at the deployments folder.

Once that it's there, go to the bin folder at Wildfly server: `~/wildfly-10.0.0.Final/bin`

Run the standalone script: `./standalone.sh`

### 6) Access this: http://localhost:8080/task/rest/status
<br>

<br>
## App EndPoints

 - http://localhost:8080/task/rest/task/new (New task)
 - http://localhost:8080/task/rest/task/all (All tasks)
 - http://localhost:8080/task/rest/task/{id} (Get task with id 1, 2...)
 - http://localhost:8080/task/rest/task/edit (Edits tasks)
 - http://localhost:8080/task/rest/task/delete/{id} (Delete task by id)

<br>
### Create new task:

```shell
curl -H "Content-Type: application/json" -X POST -d '{"name":"Nome","dateFinish":"2016-09-09", "done":"false"}' http://localhost:8080/task/rest/task/new
```

### Edit task:

Just know one thing: the app uses the `id` from JSON to edit the Task.

```shell
curl -H "Content-Type: application/json" -X PUT -d '{"id":"1","name":"NameToEdit","dateFinish":"2014-09-09", "done":"false"}' http://localhost:8080/task/rest/task/edit
```

### Get all tasks:

```shell
curl -H "Content-Type: application/json" -X GET http://localhost:8080/task/rest/task/all
```

### Get task by id:

```shell
curl -H "Content-Type: application/json" -X GET http://localhost:8080/task/rest/task/{id}
```

### Delete tasks:

```shell
curl -H "Content-Type: application/json" -X GET http://localhost:8080/task/rest/task/overdue
```

### Get overdue tasks:

```shell
curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/task/rest/task/delete/{id}
```
