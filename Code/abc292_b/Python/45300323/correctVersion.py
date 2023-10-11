n, m = map(int, input().split())

cnts = [0]*n
for i in range(m):
    s = list(map(int, input().split()))
    player = s[1]-1
    if s[0] == 1:
        cnts[player] += 1
    elif s[0] == 2:
        cnts[player] += 2
    elif s[0] == 3:
        if cnts[player] >= 2:
            print("Yes")
        else:
            print("No")