# ABC-314-C-Rotate_Colored_Subsequence_2

N, M = map(int, input().split())
S = input()
S = list(S)
C = list(map(int, input().split()))

# 連想配列のlist版
from collections import defaultdict

m = defaultdict(list)
for i in range(N):
    m[C[i]].append([S[i], i])
print(m)
# 8 3
# apzbqrcs
# 1 2 3 1 2 2 1 2
# ↓
# {1: [['a', 0], ['b', 3], ['c', 6]], 2: [['p', 1], ['q', 4], ['r', 5], ['s', 7]], 3: [['z', 2]]}

node = [0]*N
for i in m:
  for j in range(len(m[i])):
    node[m[i][j][1]] = m[i][j - 1][0] # ここが良くわからない
#print(node)

print(''.join(node))


