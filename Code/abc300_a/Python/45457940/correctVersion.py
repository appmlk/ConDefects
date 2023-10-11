N, A, B = map(int, input().split())
C = list(map(int, input().split()))
#print(C)
for i in range(len(C)):
  if C[i] == A + B:
    print(i+1)