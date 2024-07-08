num = list(map(int ,input().split(" ")))
N = num[0]
M = num[1]
S = input()
T = input()


if T[:N] == S and T[-N:] == S:
  print(0)
elif T[:N] == S:
  print(1)
elif T[-N:] == S:
  print(2)
else:
  print(3)