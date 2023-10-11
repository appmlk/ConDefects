from collections import deque
t=int(input())
for i in range(t):
  n=int(input())
  s=list(map(str,input()))
  s1,s2=s,deque()
  tt=False
  ss=True
  for i in range(n-1):
    s2.appendleft(s1[-1])
    s1.pop()
    for j in range(min(len(s1),len(s2))):
      if ord(s1[j])<ord(s2[j]):
        print("Yes")
        tt=True
        break
      elif ord(s1[j])>ord(s2[j]):
        ss=False
        break
    if not tt and ss and len(s1)<len(s2):
      tt=True
      print("Yes")
    if tt:
      break
  if not tt:
    print("No")