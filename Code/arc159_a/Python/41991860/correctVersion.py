from collections import deque
n, k=map(int, input().split())
A =[[] for _ in range(n)]
for i in range(n):
    a =list(map(int, input().split()))
    for j in range(n):
        if a[j] ==1:
            A[i].append(j)
dist2 =[[-1] *n for _ in range(n)]
for i in range(n):
    d =deque()
    d.append(i)
    v =d.pop()
    for j in A[v]:
        dist2[i][j] =1
        d.append(j)
    while d:
        v =d.pop()
        for j in A[v]:
            if dist2[i][j] !=-1:
                continue
            dist2[i][j] =dist2[i][v] +1
            d.appendleft(j)
q =int(input())
for _ in range(q):
    s, t=map(int, input().split())
    s-=1
    t-=1
    print(dist2[s%n][t%n])