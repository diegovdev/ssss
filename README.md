# README:

##Architecture:
I separated 2 layers: the API and the Engine. API is just a dumb presentation layer with no logic that only publishes endpoints to access data and operations.
Inside the API there is a separation of components: exceptions, mappers, endopoints and controllers. Endpoints only define available operations and their
constraints, no logic whatsoever. The controller is just a wrapper to access the business logic that lives inside the Engine, if there were a need to add some 
logic in the API it would go here rather than in the endpoints.
The Engine is currently accessed by the API as a Jar dependency but could also be separated to run on a different machine and be accesed via a RPC mecanism.
Engine code was seprataed between components: entities, datastore and a business logic manager. Right now the Manager simply passes the call to the DataStore
but in a real system this layer would have a lot of logic in different files.

##Frameworks:
I used Maven to ease dependency injection, Jersey for the REST API since it makes it very easy to create endpoints and is very stable. To ease deployment as
a webserver I used Jetty wich is very easy to use, stable and lightweitght.
If API and Engine would live in seperated machines or processes I would use ZeroMQ for the internal communication.

##Data store:
Usually I would rely on DB search and sorting capabilities (may it be a Sql or No-Sql data storage), 
but since this app is not expected to use a DB, inside DataStore.java I am going to rely on lookup objects to emulate a database and do the basic searches.

##Performance:
For this solution to have good performance I believe the most critical focus is on storage and data filtering. 
Since I am not using a DB engine for simplicity, my only option is to store the objects in memory which is not desirable 
(say for 80000 objects, unless we are using a No-Sql DS). Also filtering in-memory data structures is not going to be performant-friendly.
So this implementation does not intend to show performant behaviour as is.

##Inheritance:
For this excercise there was no need to use inheritance (have an abstract class nor an interface) since in the instructions there is no 
specific behavior nor specific properties to separate between entities.

