n,l,r = map(int,input().split())
a = list(map(int,input().split()))
v = []
for i in range(n):
	w = a[i]
	for e in v:
		w = min(w, e^w)
	if w > 0:
		for j in range(len(v)):
			v[j] = min(v[j], v[j] ^ w)
		v.append(w)
v.sort()

#print(v)

ans = []
l-=1
r-=1
for i in range(l, r+1):
	tmp = 0
	for j in range(60):
		if (i >> j & 1):
			tmp ^= v[j]
	ans.append(tmp)
print(*ans)