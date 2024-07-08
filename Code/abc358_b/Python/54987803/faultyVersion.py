N, A = map(int, input().split())
T = list(map(int, input().split()))

in_retu = 0

for i in range(N):
  go = max(in_retu, T[i]) +A
  print(go)

in_retu = go