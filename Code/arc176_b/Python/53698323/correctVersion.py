# based on https://atcoder.jp/contests/arc176/submissions/53409900

T = int(input())

table = [2,4,8,6]
def solve(N, M, K):
    if M-K == 1:
        return 0 if N >= K else table[(N+3)%4]	# 多分ここがafter_contestに引っかかってる
    if N >= M:
		# N<MになるまでNからM-Kを引く操作を高速に行う。
		# NからN-Mより大きいM-Kの倍数で引く
        N -= ((N-M+M-K)//(M-K))*(M-K)
    return table[(N+3)%4]

for i in range(T):
    N, M, K = map(int, input().split())
    print(solve(N, M, K))