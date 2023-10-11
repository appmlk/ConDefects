pin = [0] * 7
s = input()
p = [[6],[3],[7,1],[4,0],[8,2],[5],[9]]
for i in range(len(p)):
  cnt = 0
  for j in range(len(p[i])):
    if s[p[i][j]] == "1":
      cnt += 1
  if cnt:
    pin[i] = 1
b = 0
ok = 0
e = 0
for i in range(7):
  if pin[i] and b == 0:
    b = 1
  if b and pin[i] == 0:
    ok = 1
  if b and ok and pin[i]:
    e = 1
print("Yes" if e and s[0] == "0" else "No")

