N = int(input())
P = list(map(int, input().split()))

if P[0] == 1:
    if P[1] == 2:
        print(0)
    else:
        print(2)
elif P[-1] == 1:
    if P[-2] == 2:
        print(1)
    else:
        print(3)
else:
    idx = P.index(1)

    if P[idx+1] == 2:
        print(min(idx, N-idx+2))  # 1を先頭に持ってくる
    else:
        print(min(idx+1+1, 1+(N-idx-1)))  # 1を末尾に持ってくる
