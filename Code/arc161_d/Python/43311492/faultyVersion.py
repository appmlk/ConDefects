from sys import stdin

n, d = map(int, stdin.readline().split())
e = n*d

if e > (n*(n-1))//2:
	print('No')
	exit()

edge = []
u = 1
while e:
	for i in range(1, n-u+1):
		if e == 0:
			break
		if u+i > n:
			edge.append([u, u+i-n])
		else:
			edge.append([u, u+i])
		e -= 1
	u += 1

print('Yes')
for u, v in edge:
	print(u, v)