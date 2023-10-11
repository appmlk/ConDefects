s=input()
for i in range(len(s)):
  if s[-i-1]=="a":
    print((len(s))-i)
    break
else:
    print(-1)