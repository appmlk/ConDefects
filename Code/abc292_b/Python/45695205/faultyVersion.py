n, q = map(int, input().split())
player = [0] * n
for i in range(q):
    c, x =   map(int,input().split())
    if c == 1:
        player[x - 1] += 1
    elif c == 2:
        player[x - 1] += 2
    else:
        if player[x - 1] == 2:
            print("Yes")
        else:
            print("No")
