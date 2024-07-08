X, Y = map(int, input().split())
floors_to_move = Y - X

if 0 < floors_to_move <= 2 or -3 <= floors_to_move < 0:
    print('Yes')
else:
    print('No')