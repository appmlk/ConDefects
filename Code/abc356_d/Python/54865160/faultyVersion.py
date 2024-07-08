N, M = map(int, input().split())
MOD = 998244353

if N == 0 or M == 0:
  print(0)
  exit()

A = []
n = 1
while n <= N:
  A.append(n)
  n *= 2
#print(A)

B = []
NN = N + 1
for a in A:
  b = NN // a
  b1 = b // 2
  b2 = b % 2
  c = NN % a
  d = b1 * a + b2 * c
  d %= MOD
  B.append(d)
B = B + [0] * 60
#print("B =", B)

MM = bin(M)
L = len(MM) - 2
ans = 0
#print("MM =", MM)
#print("L =", L)
for i in range(L):
  b = MM[-i-1]
  if b:
    ans += B[i]
    ans %= MOD
print(ans)