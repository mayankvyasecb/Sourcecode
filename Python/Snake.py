import random

def print_board(snake, food, board_size):
    for row in range(board_size):
        for col in range(board_size):
            if [row, col] == food:
                print('F', end=' ')
            elif [row, col] in snake:
                print('S', end=' ')
            else:
                print('.', end=' ')
        print()
    print()

def place_food(snake, board_size):
    while True:
        food = [random.randint(0, board_size-1), random.randint(0, board_size-1)]
        if food not in snake:
            return food

def snake_game():
    board_size = 10
    snake = [[board_size // 2, board_size // 2]]  # Snake starts in the middle
    food = place_food(snake, board_size)
    direction = ''
    game_running = True

    while game_running:
        print_board(snake, food, board_size)
        direction = input("Move (w=up, s=down, a=left, d=right): ").lower()

        head = snake[0]
        new_head = head[:]
        
        if direction == 'w':
            new_head[0] -= 1
        elif direction == 's':
            new_head[0] += 1
        elif direction == 'a':
            new_head[1] -= 1
        elif direction == 'd':
            new_head[1] += 1

        # Check if new head collides with walls or itself
        if (new_head in snake) or new_head[0] < 0 or new_head[0] >= board_size or new_head[1] < 0 or new_head[1] >= board_size:
            print("Game Over! You crashed.")
            game_running = False
        else:
            snake.insert(0, new_head)  # Move the snake

            if new_head == food:  # If snake eats food
                food = place_food(snake, board_size)
            else:
                snake.pop()  # Remove tail if no food eaten

    print(f"Your snake length was: {len(snake)}")

if __name__ == "__main__":
    snake_game()
