from collections import defaultdict

N, Q = map(int, input().split())
follow = defaultdict(bool)

for i in range(Q):
    t, a, b = map(int, input().split())
    if t==1:
        follow[(a, b)] = True
    
    elif t==2:
        follow[(a, b)] = False
    
    elif t==3:
        if follow[(a, b)] and follow[(b, a)]:
            print("Yes")
        else:
            print("No")