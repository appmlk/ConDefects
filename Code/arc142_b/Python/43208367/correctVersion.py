from math import ceil
n=int(input())
l=[*range(1,n*n+1)]
idx=0
hlf=n*ceil(n/2)
for i in range(n//2):
    print(*l[idx:idx+n])
    print(*l[idx+hlf:hlf+idx+n])
    idx+=n
if n//2!=n/2:
    print(*l[idx:idx+n])