T = int(input())
LR = [list(input().split()) for _ in range(T)]

def F(l, r):
    if len(l) == len(r):
        return int(r) - int(l) + 1
    elif r[0] == "1":
        return int(r) - max(int(r[1:]), int(l)-1, int(r)//10)
    else:
        return int(r) - 10**(len(r)-1) + 1

for l, r in LR:
    print(F(l, r))


