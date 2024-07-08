n = int(input())
s = [input() for _ in range(n)]


m = 2**61-1 # 2**31-1

from collections import defaultdict

di = defaultdict(int)

for x in s:
    num = 0
    for y in x:
        y = (ord(y)-ord('a')+1)
        num = (y+num*100)%m
        di[num] += 1

for x in s:
    ans = 0
    num = 0
    for i, y in enumerate(x):
        y = (ord(y)-ord('a')+1)
        num = (y+num*100)%m
        if di[num] >= 2:
            ans = max(i+1, ans)
    print(ans)