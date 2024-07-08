n = int(input())
a = [0] + list(map(int, input().split()))
ans = 0
b = [0 for i in range(25)]
f = [[0 for i in range(25)] for i in range(25)]
mx=0;
for i in range(1, n + 1):
	for j in range(10):
		mx=max(mx,f[j][a[i]-j+9])
	ans+=mx
	for j in range(10):
		if(b[j]):f[a[i]][a[i]-j+9]=max(b[j],f[a[i]][a[i]-j+9])
	b[a[i]]=i
print(ans)