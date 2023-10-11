from collections import deque

N = int(input())
s = list(input())
index = {chr(ord('a') + x): deque() for x in range(26)}
for i in range(N):
    index[s[i]].append(i)

l, r = 0, N
while r - l > 1:
    for x in range(ord(s[l]) - ord('a')):
        c = chr(ord('a') + x)
        while len(index[c]) > 0:
            i = index[c].pop()
            if l < i < r:
                s[l], s[i] = s[i], s[l]
                l, r = l+1, i
                break
            elif i < l:
                index[c] = []
        else:
            continue
        break
    l += 1
print(''.join(s))