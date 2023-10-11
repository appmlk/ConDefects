N,M = map(int,input().split())
A = list(map(int,input().split()))

from collections import defaultdict
dic = defaultdict(int)
for a in A:
    dic[a] ^= 1

blue = 0
while dic:
    k,v = dic.popitem()
    if v==0: continue
    if M%2:
        exit(print('Alice'))
    k2 = (k + M//2) % M
    if dic[k2] == 0:
        exit(print('Alice'))
    del dic[k2]
    blue += 1
print('Alice' if blue%2 else 'Bob')