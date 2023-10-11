N = int(input())
S = input()
t, a, t_win, a_win = 0, 0, False, False
for i in range(N):
  if S[i] == "T":
    t += 1
  else:
    a += 1
  if N % 2 == 0 and N / 2 == t:
    t_win = True
  elif N % 2 == 0 and N / 2 == a:
    a_win = True
if t > a:
  t_win = True
elif a > t:
  a_win = True
if t_win == True:
  print("T")
if a_win == True:
  print("A")