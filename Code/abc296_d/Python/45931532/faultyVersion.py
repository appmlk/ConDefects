N,M=map(int, input().split())
ans=1<<60
for i in range(1, int(M**(1/2))+1):
    if i>N: break
    b=(M+i-1)//i
    if b>N: continue
    ans=min(ans, i*b)
if ans==1<<60: ans=-1
print(ans)