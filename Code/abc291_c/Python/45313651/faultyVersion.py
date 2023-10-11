N = int(input())
S = input()

def Move(s, x, y):
    if s=="R":
        x += 1
    elif s=="L":
        x -= 1
    elif s=="U":
        y += 1
    elif s=="D":
        y += 1
    
    return x, y

X, Y = 0, 0
visited = set()
visited.add((X, Y))

for i in range(N):
    X, Y = Move(S[i], X, Y)
    if (X, Y) in visited:
        print("Yes")
        exit()
        
    visited.add((X, Y))

print("No")        
        