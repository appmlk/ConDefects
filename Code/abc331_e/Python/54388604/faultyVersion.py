# √(L+1)の範囲で全探索
N, M, L = map(int, input().split())
l_a = min(int(L**0.5)+1, N)
l_b = min(int(L**0.5)+1, M)
A = list(map(int, input().split()))
A_s = list([A[i], i] for i in range(N))
A.sort(reverse=True)
A_s.sort(reverse=True)
D_A = {A_s[i][1]:i for i in range(N)}
B = list(map(int, input().split()))
B_s = list([B[i], i] for i in range(M))
B.sort(reverse=True)
B_s.sort(reverse=True)
D_B = {B_s[i][1]:i for i in range(M)}

Q = dict()
for i in range(L):
  c, d = map(int, input().split())
  c -= 1
  c = D_A[c]
  d -= 1
  d = D_B[d]
  Q[c*10**6+d] = 1
  
ans = 0
for a in range(l_a):
  for b in range(M):
    if not a*10**6+b in Q:
      ans = max(ans, A[a]+B[b])
for b in range(N):
  for b in range(l_b):
    if not a*10**6+b in Q:
      ans = max(ans, A[a]+B[b])
print(ans)