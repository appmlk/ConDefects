N,H,X = map(int,input().split())
P = input()
P = P.split()

for i in range(N) :
  PP = int(P[i])
  if (X - H) <= PP :
    print(i+1)
    break