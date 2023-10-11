s = input()
t = input()
k = 0
l = []
m = []
for i in range(1,len(s)):
  if s[i-1]!=s[i]:
    l.append(s[k:i])
    k = i
l.append(s[k:])
k = 0
for j in range(1,len(t)):
  if t[j-1]!=t[j]:
    m.append(t[k:j])
    k = j
m.append(t[k:])
if len(m)!=len(l):
  print("No")
  exit()
for o in range(len(l)):
  if l[o]==m[o]:
    continue
  elif l[o][0]==m[o][0] and l[o]<m[o] and len(l[o])>=2:
    continue
  else:
    print("No")
    exit()
print("Yes")