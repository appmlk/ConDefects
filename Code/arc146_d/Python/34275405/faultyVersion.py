n, m, k = map(int, input().split())
z = [[] for _ in range(n)]
for i in range(k):
	p, x, q, y = [int(a) - 1 for a in input().split()]
	z[p].append([x + 1, q, y + 1])
	z[p].append([x + 2, q, y + 2])
	z[q].append([y + 1, p, x + 1])
	z[q].append([y + 2, p, x + 2])
for i in z:
	i.sort(key = lambda x: -x[0])
	print(i)
f = [1] * n
for i, x in enumerate(z):
	print(i, f[i]);
	l = []
	while x and x[-1][0] <= f[i]:
		l.append(x.pop())
	while l:
		_, j, a = l.pop()
		f[j] = max(f[j], a)
		w = z[j]
		while w and w[-1][0] <= f[j]:
			l.append(w.pop())
print(-1 if max(f) > m else sum(f))
