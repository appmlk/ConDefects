
from collections import deque, defaultdict, Counter
Q = int(input())
num = 1
dq = deque([1])
MOD = 998244353
ans = []
pow10 = []
p = 1
for i in range(600001):
	pow10.append(p)
	p = (p*10)%MOD

for _ in range(Q):
	q = input().split()
	if q[0]=="1":
		t = int(q[1])
		num = (num*10 + t) % MOD
		dq.append(t)

	elif q[0]=="2":
		a = dq.popleft()
		num -= (a*pow10[len(dq)])
		num = (num+MOD) % MOD

	elif q[0]=="3":
		ans.append(num)

print(*ans ,sep='\n')