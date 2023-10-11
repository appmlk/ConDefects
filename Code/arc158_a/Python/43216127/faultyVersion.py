T = int(input())

for i in range(T):
    x1, x2, x3 = map(int, input().split())
    S = x1+x2+x3
    if S % 3 == 0:
        D = abs(S//3-x1) + abs(S//3-x2) + abs(S//3-x3)
        print(D//4)
    else:
        print(-1)
