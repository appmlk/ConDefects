t=int(input())
for i in range(t):
  input()
  s=input()
  cnt=0
  for j in range(1,len(s)):
    if s[j:]>s[:j]:
      cnt=1
  if cnt:print("Yes")
  else:print("No")