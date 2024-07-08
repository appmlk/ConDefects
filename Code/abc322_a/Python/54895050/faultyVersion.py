N = int(input())
S = input()

for i in range(N-3):
  if S[i] == "A" and S[i+1] == "B" and S[i+2] == "C":
    print(i+1)
    exit()
else:
  print(-1)