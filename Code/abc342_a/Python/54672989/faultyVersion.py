S = input()

for i in range(len(S)-1):
  if S[i-1] != S[i] and S[i] != S[i+1]:
    print(i+1)