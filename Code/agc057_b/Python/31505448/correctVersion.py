n,x = map(int,input().split())
a = list(map(int,input().split()))
c = [[] for _ in range(n)]
m = max(a)
for i in range(n):
	p = 1
	while True:
		l,r = p*a[i],p*a[i]+(p-1)*x
		if l<=m<=r:
			c[i] = [0,0]
			break
		elif r<m:
			p *= 2
		else:
			y,z = p//2*a[i]+(p//2-1)*x,p*a[i]
			c[i] = [y-m,z-m]
			break
c.sort()
d = 0
b = 10**15
for i in range(n):
	b = min(b,d-c[i][0])
	d = max(d,c[i][1])
if b<x:
	print(0)
else:
	print(b)
