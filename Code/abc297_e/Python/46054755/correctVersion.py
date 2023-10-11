import sys
import heapq
input = sys.stdin.readline
def miss():
    return map(int,input().split())
def lmiss():
    return list(map(int,input().split()))
def ii():
    return int(input())
def li():
    return list(input())
N, K = miss()
A = lmiss()
A.sort()
ans = set()
next = A.copy()
flag = set()
heapq.heapify(next)
while len(ans) != K:
    x = heapq.heappop(next)
    for a in A:
        if a+x in flag:
            continue
        flag.add(a+x)
        heapq.heappush(next,a+x)
    ans.add(x)
print(max(ans))