n, q = map(int, input().split())
p = [0] * n

for _ in range(q):
    t, x = map(int, input().split())
    x -= 1
    if t == 1:
        p[x] += 1
        
    elif t == 2:
        p[x] += 2
        
    else:
        if p[x] == 2:
            print("Yes")
        else:
            print("No")