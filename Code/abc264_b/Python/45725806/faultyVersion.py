r, c = map(int, input().split())

if min(r,c, 15-r, 15-c) % 2 == 1:
    print('black')
else:
    print('white')