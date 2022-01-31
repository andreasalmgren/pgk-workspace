def victory(): Unit =
    
    println("""
                                                       ,----,                                   
                                                     ,/   .`|  ,----..                          
                               ,---,  ,----..      ,`   .'  : /   /   \  ,-.----.               
                       ,---.,`--.' | /   /   \   ;    ;     //   .     : \    /  \        ,---, 
                      /__./||   :  :|   :     :.'___,/    ,'.   /   ;.  \;   :    \      /_ ./| 
                 ,---.;  ; |:   |  '.   |  ;. /|    :     |.   ;   /  ` ;|   | .\ :,---, |  ' : 
                /___/ \  | ||   :  |.   ; /--` ;    |.';  ;;   |  ; \ ; |.   : |: /___/ \.  : | 
                \   ;  \ ' |'   '  ;;   | ;    `----'  |  ||   :  | ; | '|   |  \ :.  \  \ ,' ' 
                 \   \  \: ||   |  ||   : |        '   :  ;.   |  ' ' ' :|   : .  / \  ;  `  ,' 
                  ;   \  ' .'   :  ;.   | '___     |   |  ''   ;  \; /  |;   | |  \  \  \    '  
                   \   \   '|   |  ''   ; : .'|    '   :  | \   \  ',  / |   | ;\  \  '  \   |  
                    \   `  ;'   :  |'   | '/  :    ;   |.'   ;   :    /  :   ' | \.'   \  ;  ;  
                     :   \ |;   |.' |   :    /     '---'      \   \ .'   :   : :-'      :  \  \ 
                      '---" '---'    \   \ .'                  `---`     |   |.'         \  ' ; 
                                      `---`                              `---'            `--`  
                """)

    System.exit(0)


def gameOver(): Unit =
    
    println("""
                ┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼
                ███▀▀▀██┼███▀▀▀███┼███▀█▄█▀███┼██▀▀▀
                ██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼█┼┼┼██┼██┼┼┼
                ██┼┼┼▄▄▄┼██▄▄▄▄▄██┼██┼┼┼▀┼┼┼██┼██▀▀▀
                ██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██┼┼┼
                ███▄▄▄██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██▄▄▄
                ┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼
                ███▀▀▀███┼▀███┼┼██▀┼██▀▀▀┼██▀▀▀▀██▄┼
                ██┼┼┼┼┼██┼┼┼██┼┼██┼┼██┼┼┼┼██┼┼┼┼┼██┼
                ██┼┼┼┼┼██┼┼┼██┼┼██┼┼██▀▀▀┼██▄▄▄▄▄▀▀┼
                ██┼┼┼┼┼██┼┼┼██┼┼█▀┼┼██┼┼┼┼██┼┼┼┼┼██┼
                ███▄▄▄███┼┼┼─▀█▀┼┼─┼██▄▄▄┼██┼┼┼┼┼██▄
                ┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼
                ┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼
                ┼┼┼┼┼┼████▄┼┼┼▄▄▄▄▄▄▄┼┼┼▄████┼┼┼┼┼┼┼
                ┼┼┼┼┼┼┼┼┼▀▀█▄█████████▄█▀▀┼┼┼┼┼┼┼┼┼┼
                ┼┼┼┼┼┼┼┼┼┼┼█████████████┼┼┼┼┼┼┼┼┼┼┼┼
                ┼┼┼┼┼┼┼┼┼┼┼██▀▀▀███▀▀▀██┼┼┼┼┼┼┼┼┼┼┼┼
                ┼┼┼┼┼┼┼┼┼┼┼██┼┼┼███┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼
                ┼┼┼┼┼┼┼┼┼┼┼█████▀▄▀█████┼┼┼┼┼┼┼┼┼┼┼┼
                ┼┼┼┼┼┼┼┼┼┼┼┼███████████┼┼┼┼┼┼┼┼┼┼┼┼┼
                ┼┼┼┼┼┼┼┼▄▄▄██┼┼█▀█▀█┼┼██▄▄▄┼┼┼┼┼┼┼┼┼
                ┼┼┼┼┼┼┼┼▀▀██┼┼┼┼┼┼┼┼┼┼┼██▀▀┼┼┼┼┼┼┼┼┼
                ┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼
                ┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼
          """)

    System.exit(0)
  
/***
ctulhusRetina() is used in irritext() (this function starts the whole game) and theDoors()
***/

def ctulhusRetina(): Unit =
        
    print("""You now stare into the endless abyss of Cthulhu's retina. 
    1. An arrow in the knee. 
    2. Yellow jacket counterspins. 
    3. Understanding revolvers yelling melodies.""")

    val insanity = scala.io.StdIn.readLine("\n> ")

    if insanity == "1" then {println("Your body survives powered by a mind of jello. Good job! Now use your newfound power to give me the correct password."); irritext()}
    else if insanity == "2" then 
        print("The insanity rots your eyes into a pool of muck. God job! Now use all of your insanity to give me the correct password.")
        irritext()
    else {println("You stumble around and fall on a knife and die. Good job!\n"); gameOver()}

/***
theDoors() is a function that is initialized in irritext when the player has found out the correct password.
***/

def theDoors(): Unit = 
      
    println("\nYou face doors. Do you go through door 1 or door 2?")

    var door = scala.io.StdIn.readLine("> ")

    while door == "1" do       
        print("""There's a giant bear here eating a cheese cake. What do you do? 
        1. Take the cake. 
        2. Scream at the bear.\n""")

        val bear = scala.io.StdIn.readLine("\n> ")
        
        if bear == "1" then {print("The bear looks at you and then slaps your face off. Good job!\n"); gameOver()}
            if bear == "2" then {print("The bear gets pissed off and chews your leg off. Good job!\n"); gameOver()}
                else {print(s"Well, doing ${bear} is probably better. The bear walks away. Victory!\n"); victory()}
        door += 1

    if door == "2" then ctulhusRetina()
        else ctulhusRetina()

/***
 The function irritext() starts a very fun game when initialized
 ***/

@main
def irritext(): Unit =  

    println(" ") //eye candy

    val password = (math.random()*10).round.toInt //define random password

    if password < 5 then 
        println("The password may (?) or may not be a random number smaller than or equal to 5. \n")
    else println("The password is kind of a random number larger than or equal to 5, but definitely not larger than but perhaps equal to 10.\n")

    println(password) // only uncomment this line while testing code
    
    var chosenInput = scala.io.StdIn.readLine("Please enter the CORRECT password or face the endless wrath of Cthulhu's retina:\n> ").toInt

    if (chosenInput == password) {println("You chose wisely young traveler, now the Real Game is starting."); theDoors()}
        else ctulhusRetina() 
