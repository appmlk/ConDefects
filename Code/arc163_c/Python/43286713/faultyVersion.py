from sys import stdin
from collections import deque

T = int(stdin.readline())

for tc in range(T):
	n = int(stdin.readline())
	if n == 1:
		print('Yes')
		print(1)
	elif n == 2:
		print('No')
	
	st = {2,3,6}
	a = deque([2,3,6])
	fin = []
	while len(a) + len(fin) < n:
		cur = a.popleft()
		if (cur+1 in st) or (cur*(cur+1) in st) or (cur*(cur+1) > 1000000000):
			fin.append(cur)
			st.remove(cur)
			continue
		a.append(cur+1)
		a.append(cur*(cur+1))
		st.add(cur+1)
		st.add(cur*(cur+1))
	
	ans = fin + list(a)
	print('Yes')
	for i in ans:
		print(i, end=' ')
	print()
			