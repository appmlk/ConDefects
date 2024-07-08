n, q = map(int,input().split())
T = list(map(int,input().split()))
hanuke = [0] * (n + 1)

for t in T:
  hanuke[t] ^= 1

print(n - hanuke.count(1))
#print(hanuke)