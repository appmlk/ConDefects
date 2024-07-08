N = int(input())
S = input()

def check():
  for i in range(N-2):
    if S[i] + S[i+1] + S[i+2] == "ABC":
      return i+1
  return -1

print(check())