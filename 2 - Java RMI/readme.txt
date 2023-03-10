Answers

9 - Thread safety is ensured by making the function synchronized

10 - It looks like a singleton since the client variable is increased at every run of the client, means it's fetching the same object from the registry which was created during the initial server run.
     However the class defined at the server side is not a singleton. A probable way to get a new instance at each call would be to create a static method for the class
     such as get instance and create and return a new MathServer object at each call.