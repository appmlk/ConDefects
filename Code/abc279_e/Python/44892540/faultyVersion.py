n, m = map(int, input().split())
a = list(map(int, input().split()))
b = list(range(n + 1))
for i in a:
	b[i], b[i + 1] = b[i + 1], b[i]
p = [0] * (n + 1)
for i in range(n):
	p[b[i]] = i
b = list(range(n + 1))
for i in a:
	if b[i] == 1:
		print(p[b[i + 1]])
	elif b[i + 1] == 1:
		print(p[b[i]])
	else:
		print(p[1])
	b[i], b[i + 1] = b[i + 1], b[i]