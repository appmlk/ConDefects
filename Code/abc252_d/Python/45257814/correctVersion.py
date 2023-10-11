import collections
n = int(input())
a = list(map(int, input().split()))
c = collections.Counter(a)

cnt = 0
for v in c.values():
    if v == 2:
        cnt += (n-2)*v*(v-1)//2 
    if v >= 3:
        cnt += v*(v-1)*(v-2)//6
        cnt += (n-v)*v*(v-1)//2

ans = n*(n-1)*(n-2)//6 - cnt
print(ans)