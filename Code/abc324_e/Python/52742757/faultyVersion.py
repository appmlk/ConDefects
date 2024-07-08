import bisect

N,T = input().split()
N = int(N)
k = len(T)
left = []
right = []
for i in range(N):
    s = input()
    c = 0
    for j in s:
        if j == T[c]: c += 1
        if c == k: break
    left.append(c)
    c = 0
    for j in s[::-1]:
        if j == T[k-1-c]: c += 1
        if c == k: break
    right.append(c)
left.sort()
right.sort()
c = 0
for j in range(k+1):
    x = bisect.bisect(left,j)-bisect.bisect_left(left,j)
    y = N-bisect.bisect_left(right,k-1)
    c += x*y
print(c)