A, B, C, D, E, F = map(int, input().split())
N = int(input())
X = list(map(int, input().split()))

out = "Yes"

for i in range(N):
  while(X[i] >= 500 and F > 0):
    X[i] = X[i] - 500
    F = F - 1
  while(X[i] >= 100 and E > 0):
    X[i] = X[i] - 100
    E = E - 1
  while(X[i] >= 50 and D > 0):
    X[i] = X[i] - 50
    D = D - 1
  while(X[i] >= 10 and C > 0):
    X[i] = X[i] - 10
    C = C - 1
  while(X[i] >= 5 and B > 0):
    X[i] = X[i] - 5
    B = B - 1
  while(X[i] >= 1 and A > 0):
    X[i] = X[i] - 1
    A = A - 1
  if(X[i] != 0):
    out = "No"
    break
  
print(out)