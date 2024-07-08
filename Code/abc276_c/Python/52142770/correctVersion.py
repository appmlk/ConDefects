n = int(input())
P = list(map(int,input().split()))

l = n - 2
r = n - 1

while P[l] < P[r] :
  l -= 1
  r -= 1

r = n - 1

while P[l] < P[r] :
  r -= 1
  
P[l] , P[r] = P[r] , P[l]

L = P[: l + 1]
R = P[l + 1 :]

R = sorted(R , reverse = True)

print(*(L + R))
