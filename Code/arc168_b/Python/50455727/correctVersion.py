from collections import*
n = int(input())
a = list(map(int, input().split()))
dic = defaultdict(int)
cnt = 0
for ai in a:
    dic[ai] += 1
    cnt ^= ai
if cnt:
    print(-1)
    exit()
cnt = 0
for k in dic.keys():
    if dic[k]%2: cnt = max(cnt, k)
if cnt:
    print(cnt-1)
else:
    print(0)
