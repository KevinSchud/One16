Run the jar file found in folder Runnable_jar using CLI (open explorer browse to Runnable_jar and type in cmd in the address bar. This should open up a CLI already in the correct folder) 

>java -jar application-0.0.1-SNAPSHOT.jar

After that launch postman and import the collection 
```
Kevin_Schuddinck_One16.postman_collection.json
```

There are 2 parameters to request:
```
    1. wordLength (parameters): maximum combination length
    2. file (body): file that needs to be sent
```

Note: the collection will already have a file input. However this file has my folder location. To get the correct file simply remove it and add yours.