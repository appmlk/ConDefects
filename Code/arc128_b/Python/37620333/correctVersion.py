T = int(input())

def calc(a,b,c):
    a,b = max(a,b), min(a,b)

    if a == b:
        # 成功
        return a
    elif (a - b) % 3 == 0:
        return a
    else:
        return -1

for _ in range(T):
    R, G, B = [int(x) for x in input().split()]
    l = [
        calc(R,G,B),
        calc(R,B,G),
        calc(G,B,R),
    ]
    if len([x for x in l if x > -1]):
        print(min([x for x in l if x > -1]))
    else:
        print(-1)
