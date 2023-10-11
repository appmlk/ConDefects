n, m = map(int, input().split())

for i in range(2**m-1, 0, -1):
  co = bin(i)[2:].zfill(m)
  if co.count("1") == n:
    print(*[t+1 for t in range(m) if co[t]=="1"])