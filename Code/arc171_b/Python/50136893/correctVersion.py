MOD = 998244353

N = int(input())
A = list(map(lambda x:int(x)-1, input().split()))
memo = [[] for _ in range(N)]

#print(A)
for i in range(N):
	if A[i] < i or A[A[i]] != A[i]:
		exit(print(0))
	memo[A[i]].append(i)

#print(memo)
st = set(range(N))
y = []
for i in range(N):
	if len(memo[i]):
		y.append(i)
	for j in range(1,len(memo[i])):
		st.remove(memo[i][j])
x = sorted(st)
#print(x,y)

# https://atcoder.jp/contests/arc171/submissions/50005878
idx = 0
m = len(x)
ans = 1
for i in range(m):
	while idx < m and x[idx] <= y[i]:
		idx += 1
	ans *= (idx - i)
	ans %= MOD
print(ans)