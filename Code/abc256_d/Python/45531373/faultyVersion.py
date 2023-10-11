N = int(input())
LR = [list(map(int, input().split())) for _ in range(N)]
LR.sort()

pl, pr = LR[0]
for i in range(1, N):
    l, r = LR[i]

    if pr < l:
        print(pl, pr)
        pl = l
    pr = r

print(pl, pr)