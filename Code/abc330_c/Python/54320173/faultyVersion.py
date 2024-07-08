d=int(input())
ans=float("inf")
for x in range(1,d):
    if x**2-d>=0:
        ans=min(ans,x**2-d)
        break
    else:
        c=d-x**2
        y1=int(c**0.5)
        y2=y1+1
        ans=min(ans,abs(y1**2-c),abs(y2**2-c))
print(ans)