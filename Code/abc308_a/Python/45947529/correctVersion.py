S = list(map(int,input().split()))
ans = "Yes"
for i in range(len(S)-1):
  if not(S[i] <= S[i+1]):
    ans = "No"
for j in range(len(S)):
  if not(S[j] % 25 == 0 and 100 <= S[j] <= 675):
    ans = "No"
print(ans)