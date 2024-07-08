def query(a, x):
    l = 0
    r = len(a)
    m = (l+r)//2
    while l < r:
        if a[m] <= x:
            l = m+1
        else:
            r = m
        m = (l+r)//2
    return m
n, t = tuple(map(int,input().split()))
s = input()
x = list(map(int,input().split()))

pos = []
neg = []

for i in range(n):
    if s[i] == '0':
        neg.append(x[i])
    else:
        pos.append(x[i])

ans = 0
for x in pos:
    # how many in neg between x and x+2t 
    ans += query(neg, x+2*t) - query(neg, x-1)
print(ans)