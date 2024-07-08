N = int(input())
S = input()
cur = 0
ans = ''
for i in range(N):
  if int(S[N-i-1]) != (cur // 2**i) % 2:
    X = (1 << N) - (1 << i)
    if S[N-i-1] == '1':
      ans += 'A' * (N-i)
      cur = cur ^ X
    else:
      ans += 'B' * (N-i)
      cur = cur ^ X

print(len(ans))
print(ans)