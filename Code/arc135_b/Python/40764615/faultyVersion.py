n = int(input())
s = list(map(int,input().split()))
m0 = []
m1 = []
m2 = []
for i in range(n-1):
  if i%3 == 0:
    m0.append(s[i+1]-s[i])
  if i%3 == 1:
    m1.append(s[i+1]-s[i])
  if i%3 == 2:
    m2.append(s[i+1]-s[i])
r0 = [0]
r1 = [0]
r2 = [0]
for i in m0:
  r0.append(r0[-1]+i)
for i in m1:
  r1.append(r1[-1]+i)
for i in m2:
  r2.append(r2[-1]+i)
mi0 = min(r0)
mi1 = min(r1)
mi2 = min(r2)
if mi0+mi1+mi2 < -s[0]:
  print("No")
  exit()
print("Yes")
ans = [0]*(n+2)
ans[0] = -mi0
ans[1] = -mi1
ans[2] = s[0]+mi0+mi1+mi2
for i in range(3,n+2):
  if i%3 == 0:
    ans[i] = ans[i-3]+m0[i//3-1]
  if i%3 == 1:
    ans[i] = ans[i-3]+m1[i//3-1]
  if i%3 == 2:
    ans[i] = ans[i-3]+m2[i//3-1]
print(*ans,sep=" ")