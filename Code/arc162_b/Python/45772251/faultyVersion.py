N = int(input())
P = list(map(int,input().split()))
res = []
for i in range(1, N):
    pos = [-1 for _ in range(N)]
    for ind, j in enumerate(P):
        if j != i:
            continue
        if ind == N-1 and i == N-1:
            print("No")
            exit()
        elif ind == N-1 and i != N-1:
            P.pop()
            P.insert(N-3, i)
            ind = N-3
            res.append([N-2, N-1])
        index1 = ind
        index2 = ind+1
        value1 = j
        value2 = P[index2]
        break
    P.remove(value2)
    P.remove(value1)
    P.insert(i-1, value1)
    P.insert(i, value2)
    res.append([index1+1, i-1])
print("Yes")
print(len(res))
for i in res:
    print(*i)