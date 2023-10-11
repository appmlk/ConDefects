import sys
def input():
    return sys.stdin.readline().rstrip()
from collections import deque
def main():
    N, M = map(int, input().split())
    link1 = [[] for _ in range(N)]
    link2 = [[] for _ in range(N)]
    inf = float('inf')
    dist1 = [inf]*N
    dist1[0] = 0
    dist2 = [inf]*N
    dist2[-1] = 0
    
    for i in range(N):
        S = [int(s) for s in input()]
        for j in range(M):
            if not S[j]:
                continue
            link1[i].append(i+j+1)
            link2[i+j+1].append(i)
    D = deque([0])
    while D:
        d = D.popleft()
        for l in link1[d]:
            if dist1[l] == inf:
                dist1[l] = dist1[d]+1
                D.append(l)
    D = deque([N-1])
    while D:
        d = D.popleft()
        for l in link2[d]:
            if dist2[l] == inf:
                dist2[l] = dist2[d]+1
                D.append(l)
    
    for i in range(1, N-1):
        ans = inf
        for j in range(max(0, i-M+1), i):
            for l in link1[j]:
                if l <= i:
                    continue
                ans = min(ans, 1+dist1[j]+dist2[l])
        if ans == inf:
            print(-1)
        else:
            print(ans)
    
if __name__ == "__main__":
    main()