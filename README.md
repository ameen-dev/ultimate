# Ultimate AI

aibot is a Java application which exposes a rest endpoint to accept a user input, find the input's intent and send back an appropriate response to the user.

## Rest endpoints

Following is the endpoint exposed. It takes a list which has the IDs of watches as the parameter. Ex: ["001","002"]

```
localhost:8080/aibot/chat
```

## Intent data pre-load

The file "intents.json" that is present in the project's root folder is used to pre-load the intent data into Mongo database.
The database could be viewed in MongoDB compass using the following connection string.

```
Connection string: mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false
Schema: dataObject
```

## Integration test cases
The integration test cases could be found in "AibotApplicationTests" file inside "src/test/java" directory.

##Sample Request and Respone

```
Request url: 
localhost:8080/aibot/chat?botId=5f74865056d7bb000fcd39ff&message=Hi

Response:
Hello :) How can I help you?
```

```
Request url: 
localhost:8080/aibot/chat?botId=5f74865056d7bb000fcd39ff&message=I want to talk to a person

Response:
Let me transfer you to the first available agent.
```

## Comments and JavaDocs
I have added comments throughout the project wherever necessary and Javadocs at the beginning of each functions to specify about parameters, output and what the function actually does.

## Exception handling
Basic exception handling has been done. Custom defined exception could be added in futur if needed.

##Constants
I have included a constants file which has a confidence threshold value of 0.5. This means that the chatbot will repond the user with the intent appropriate reply if and only if the confidence level is more than 0.5 and this value is configurable.
