N = int(input())
S = input()

from itertools import groupby
gb = [[key, len(list(g))] for key, g in groupby(S)]

def swap(s):
    res = []
    for c in s[::-1]:
        if c=='d':
            res.append('p')
        else:
            res.append('d')
    return ''.join(res)

ans = [S]
for i,c in enumerate(S):
    if c=='p':
        for j in range(i+1, N+1):
            ans.append(S[:i]+swap(S[i:j])+S[j:])
        break
print(sorted(ans)[0])