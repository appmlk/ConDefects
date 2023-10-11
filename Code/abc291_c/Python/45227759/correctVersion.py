N = int(input())
S = input()
se = set()
se.add((0, 0))
now = (0, 0)
for i in range(N):
  if S[i] == "L":
    now = (now[0] - 1, now[1])
  if S[i] == "R":
    now = (now[0] + 1, now[1])
  if S[i] == "U":
    now = (now[0], now[1] + 1)
  if S[i] == "D":
    now = (now[0], now[1] - 1)
  se.add(now)
if len(se) != N + 1:
  print("Yes")
else:
  print("No")