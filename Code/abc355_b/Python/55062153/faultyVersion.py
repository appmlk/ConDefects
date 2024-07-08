N, M = map(int, input().split())
A = set(map(int, input().split()))
B = set(map(int, input().split()))
C = sorted(list(A | B))
for i in range(N - 1):
  if C[i] in A and C[i + 1] in A:
    print('Yes')
    exit()
print('No')