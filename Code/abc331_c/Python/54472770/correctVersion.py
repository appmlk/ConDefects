n=int(input())
a=list(map(int,input().split()))
from collections import Counter
c=sorted(Counter(a).items())
l=[]
for num,cnt in c:
    l.append(num*cnt)
from itertools import accumulate
l=list(accumulate(l))
a_set=sorted(set(a))
d=dict(zip(a_set,l))
total=sum(a)
ans=[]
for i in range(n):
    ans.append(total-d[a[i]])
print(*ans)