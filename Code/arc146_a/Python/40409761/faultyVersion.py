from itertools import permutations
N = int(input())
A = sorted([i for i in input().split()])
ans = 0
for a in permutations(A[-3:],3):
    tmp = int("".join(map(str, a)))
    if ans < tmp:
        ans = tmp
print(ans)