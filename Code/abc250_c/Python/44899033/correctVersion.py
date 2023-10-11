N, Q = map(int, input().split())
X = []
for i in range(Q):
  x = int(input())
  X.append(x)

pos = [i for i in range(N + 1)] #pos[i] := i番目の要素の値
val = [i for i in range(N + 1)] #val[i] := iは何番目 - 1にあるか

for i in range(Q):
  p0 = pos[X[i]]
  p1 = p0
  if p0 != N:
    p1 += 1
  else:
    p1 -= 1
  v0 = val[p0]
  v1 = val[p1]
  val[p0], val[p1] = val[p1], val[p0]
  pos[v0], pos[v1] = pos[v1], pos[v0]

print(*val[1:])

