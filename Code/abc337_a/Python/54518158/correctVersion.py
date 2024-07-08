N = int(input())

scoreX = 0
scoreY = 0
for i in range(N):
  X, Y = map(int, input().split())
  scoreX += X
  scoreY += Y
if scoreX > scoreY:
  print('Takahashi')
elif scoreX < scoreY:
  print('Aoki')
else:
  print('Draw')