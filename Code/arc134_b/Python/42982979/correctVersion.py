import sys
from collections import Counter

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


N = int(readline())
S = readline().rstrip()

C = Counter(S)
S = list(S)
alp = 'abcdefghijklmnopqrstuvwxyz'

now = 0
left = 0
right = N - 1

while left < right:
    while C[alp[now]] == 0:
        now += 1
    
    if S[left] == alp[now]:
        left += 1
        C[alp[now]] -= 1
        continue
    
    if S[right] != alp[now]:
        C[S[right]] -= 1
        right -= 1  
    else:
        S[left], S[right] = S[right], S[left]
        C[alp[now]] -= 1
        C[S[right]] -= 1
        left += 1
        right -= 1

print(''.join(S))
