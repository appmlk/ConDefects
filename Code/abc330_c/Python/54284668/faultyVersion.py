d=int(input())
ans=1001001001001
for a in range(1,2*10**6+1):
	b=(abs(d-a**2))**.5
	b=int(b)
	for nb in range(b-1,b+2):
		ans=min(ans,abs(a**2+b**2-d))
print(ans)
