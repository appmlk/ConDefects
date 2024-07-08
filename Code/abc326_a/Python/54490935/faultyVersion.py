X, Y = map(int, input().split())
floors_to_move = Y - X

if floors_to_move == 2 or floors_to_move == -3:
    print('Yes')
else:
    print('No')