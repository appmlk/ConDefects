s=input()
t=input()
m=len(s)
n=len(t)
ng=set()
for i in range(n):
    if s[i-n+m]!=t[i] and s[i-n+m]!="?"!=t[i]:
        ng.add(i)
print("Yes" if len(ng)==0 else "No")
for j in range(n):
    if t[j]!="?" and s[j]!=t[j]:
        ng.add(j)
    else:
        ng.discard(j)
    print("Yes" if len(ng)==0 else "No")