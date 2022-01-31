@main
def theDoors(): Unit = 
      
    println("\nYou face doors. Do you go through door 1 or door 2? Or do you do something else?")

    var door = scala.io.StdIn.readLine("> ")

    while (door == "1") do       
        print("""There's a giant bear here eating a cheese cake. What do you do? 
        1. Take the cake. 
        2. Scream at the bear.""")

        val bear = scala.io.StdIn.readLine("\n> ")
        
        if bear == "1" then {print("The bear looks at you and then slaps your face off. Good job!\n"); gameOver()}
            if bear == "2" then {print("The bear gets pissed off and chews your leg off. Good job!\n"); gameOver()}
                else {print(s"Well, doing ${bear} is probably better. The bear walks away. victory()\n"); victory()}
        door += 1

    if door == "2" then ctulhusRetina()
        else gameOver()