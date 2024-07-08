N = int(input())
n = 1
while 2**n<N:n+=1
out = [[i+1 for i in range(N) if (1<<b)&i] for b in range(n)]
print(len(out),flush=True)
for q in out:
  print(len(q),*q,flush=True)
ret = [*map(int,input())]
ans = 1
for n in range(len(ret)):
  if ret[n]==1 : ans += 1<<n
print(ans,flush=True)