from sortedcontainers import SortedList
import math
n=int(input())
s=input()
sl=[0]*10
for i in range(n):
    sl[int(s[i])]+=1


max=math.ceil(math.sqrt(10**n))
ans=0
for i in range(max):
    ii=str(i*i)
    now=[0]*10
    for j in ii:
        now[int(j)]+=1
    now[0]+=n-len(ii)
    # print(now)
    if now==sl:
        ans+=1


print(ans)

