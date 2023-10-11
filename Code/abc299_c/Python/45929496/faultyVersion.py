N=input()
S=input()
sec = 0
ans = 0
for i in S:
  if i == "o":
    sec += 1
  else:
    if sec > ans:
      ans = sec
    sec = 0
if ans == 0:
  print(-1)
else:
  print(ans)