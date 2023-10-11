for _ in range(int(input())):
  n,k = map(int,input().split())
  S = input()
  k %= 2*n
  T = "".join(S[~i] if i < n else S[i-k] for i in range(k))
  if S+T == (S+T)[::-1] and T+S == (T+S)[::-1]:
    print("Yes")
  else:
    print("No")