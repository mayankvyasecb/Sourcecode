import random

def rock_paper_scissors():
    choices = ["Rock", "Paper", "Scissors"]

    while True:
        player_choice = input("Choose Rock, Paper, or Scissors (or 'exit' to quit): ").capitalize()
        if player_choice == 'Exit':
            break

        computer_choice = random.choice(choices)
        print(f"Computer chose: {computer_choice}")
        
        if player_choice == computer_choice:
            print("It's a tie!")
        elif (player_choice == "Rock" and computer_choice == "Scissors") or \
             (player_choice == "Paper" and computer_choice == "Rock") or \
             (player_choice == "Scissors" and computer_choice == "Paper"):
            print("You win!")
        else:
            print("Computer wins!")

if __name__ == "__main__":
    rock_paper_scissors()
