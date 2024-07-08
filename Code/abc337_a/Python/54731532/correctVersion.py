T = 0
A = 0

N = int(input())
for i in range(N):
  a, t = map(int, input().split())
  T += t
  A += a
  
if T == A:
  print('Draw')
elif T < A:
  print('Takahashi')
else:
  print('Aoki')