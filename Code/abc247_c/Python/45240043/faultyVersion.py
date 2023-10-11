N = int(input())
S = str(1)
if N == 1:
  print(S)
for i in range(2, N+1):
  S = S +" "+ str(i) + " " + S
print(S)