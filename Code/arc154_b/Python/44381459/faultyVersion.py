N = int(input())
S = input()
T = input()
CS = [[] for i in range(26)]
ns = [0]*26
nt = [0]*26
for i in range(N):
	CS[ord(S[i])-97].append(i)
	ns[ord(S[i])-97] += 1
	nt[ord(T[i])-97] += 1
if ns != nt:
	print(-1)
	exit()
ans = N
cnt = N+1
for i in range(N):
	tmp = T[-i-1]
	ntmp = ord(tmp)-97
	if CS[ntmp] == []:
		print(ans)
		exit()
	if CS[ntmp][0] >= cnt:
		print(ans)
		exit()
	if CS[ntmp][-1] < cnt:
		cnt = CS[ntmp][-1]
		ans -= 1
		continue
	p = 0
	q = len(CS[ntmp])-1
	while q -p >= 2:
		m = (p+q)//2
		if CS[ntmp][m] >= cnt:
			q = m
		else:
			p = m
	cnt = CS[ntmp][p]
	ans -= 1
print(ans)
    