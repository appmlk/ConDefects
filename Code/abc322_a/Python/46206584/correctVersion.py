N = int(input())
S = list(input())
count = 0
check = False

for i in range(len(S)-2):
  count += 1
  if S[i] == "A" and S[i+1] == "B" and S[i+2] == "C":
    check = True
    break

if check == True:
  print(count)
else:
  print(-1)