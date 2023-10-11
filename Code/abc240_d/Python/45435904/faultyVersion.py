from collections import deque
N = int(input())
A = list(map(int, input().split()))

Q = deque()
ans = 0

for a in A:
    ans += 1
    if len(Q) == 0:
        Q.append([a,1])
    if Q[-1][0] != a:
        Q.append([a,1])
    else:
        Q[-1][1] += 1
        if Q[-1][1] == a:
            Q.pop()
            ans -= a
    print(ans)
    
