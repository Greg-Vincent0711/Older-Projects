/**
 * @author Greg Vincent
 * ICSI 333
*/
#include <stdio.h> 
#include <stdlib.h>
/*Needed for boolean logic*/
#include <stdbool.h>
/* Macro for array size. Constant */
#define SIZE 4

/*Function prototypes to avoid implicit declaration errors */
void initialization();
void display();
bool isMoveValid();
int teardown();
int move();

/*Matrix traversal variables*/
int i,j,a,b;

/*user matrix*/
int matrix[SIZE][SIZE];

/*Inputted value*/
int userVal = 0;

/*User input for requested decision*/
char userChar;

/*Position holding variables for swapping*/
int tempI, tempJ;
int I1, J2;

/* Creates initial array */
void intialization(){
    int gameLength = 15;
   for(i = 0; i< SIZE; i++){
       for (j = 0; j < SIZE; j++){
           /**
            * 16 total elements from 15 to 0
           */
            matrix[i][j] = gameLength;
            gameLength--;
       }
   }   
}
/* Displays array when called. Also for array organization */
void display(){
    for(i = 0; i < SIZE; i++){
        for(j = 0; j < SIZE; j++){
            /*One space printed for matrix values greater than or equal to 10*/
            if(matrix[i][j] >= 10){
                printf("%d ", matrix[i][j]);
            }
            /*If matrix values are less than 10 and not zero*/
            if(matrix[i][j] < 10 && matrix[i][j] != 0) {
                printf("%d  ",matrix[i][j]);
            }
            /*If index equals zero, print an empty space.*/
            if(matrix[i][j] == 0){
                printf("   ");
            }
            /*Printing a newline for array format*/
            if(j == 3){
                printf("\n");
            }
        }
    }
}
/**
 * @return bool
 * Pre condition every entry must go through before move() is called
 * Just checks move validity.
*/
bool isMoveValid(){
    printf("Enter in your chosen number to switch: ");
    scanf("%d", &userVal);
     /**Move can't be greater than 16 in size*/
    if(userVal >= 16){
        printf("Invalid size.\n");
        return false;
        }
    /**Move can't be less than 0 in size*/
    else if(userVal < 0){
        printf("Invalid size.\n");
        return false;
        }
    /*Reading through matrix*/    
    for(i = 0; i < SIZE; i++){
        for(j= 0; j < SIZE; j++){
            /*Recording where the 0 value is*/
            if(matrix[i][j] == 0){
                I1 = i;
                J2 = j;
            }
            /*Recording where the input value is*/
            if(matrix[i][j] == userVal){
                tempI = i;
                tempJ = j;
            }
        }    
    }       
    /**
     * One above, same column 
    */
    if(I1 - tempI == 1 && J2 == tempJ)
    {
        /*Printf statements like this one help user understand their moves*/
        printf("Moving Up\n");
        return true;
    }
    /**
     * One below, same column
    */
    else if(I1 - tempI == -1 && J2 == tempJ)
    {
        printf("Moving down\n");
        return true;
    }
    /**
     * One to the right, same row
    */
    else if(J2 - tempJ == 1 && I1 == tempI)
    {
        printf("Moving left\n");
        return true;
    }
    /**
     * One to the left, same row
    */  
    else if(J2 - tempJ == -1 && I1 == tempI)
    {
        printf("Moving right\n");
        return true;
    }
    /*Default for any move not any of the other conditions*/     
    else
    {
        printf("Invalid move!\n");
        return false;
    }
}    
/**
 * @return int
 * Ends the game
*/
int teardown(){
    printf("Ending the game.\n");
    return 0;
}
/**
 * @return int
 * Assuming the required move passes the isMoveValid conditions, 
 * this method does the actual swapping
*/
int move(){
    matrix[I1][J2] = userVal;
    matrix[tempI][tempJ] = 0;
    return 0;
}
/*Infinite loop that lasts while the player does not quit*/
void acceptInput(){
    
    do{
        /*User decisions*/
        printf("Menu: [p]rint, [m]ove, [q]uit?\n");
        scanf(" %c", &userChar);
        if(userChar == 'p'){
            display();
        }
        else if(userChar == 'm'){
            if(isMoveValid()){
                move();
            }
        }
        else if(userChar != 'p' && userChar !='m' && userChar !='q'){
            printf("Invalid request.\n");
        }
    }while(userChar != 'q');
    if(userChar == 'q'){
        teardown();
    }
}    
/**
 * @return int
 * Main method, work is abstracted in other parts of the program.
 * Calls helper functions to actually start the game.
*/
int main()
{
  intialization();
  acceptInput();
  return 0;
}
