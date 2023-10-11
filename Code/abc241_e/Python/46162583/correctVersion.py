def ip():return int(input())
def mp():return map(int, input().split())
def lmp():return list(map(int, input().split()))
# ABC241 E 1248 - Putting Candies
# 長さ N の数列 A = (A0,A1,…,A_{N-1}) が与えられます。
# 最初の時点では空の皿があり、高橋君は次の操作を K 回繰り返します。
# ・皿の中のアメの個数を X とする。皿に A_(X mod N) 個のアメを追加する。
# K 回の操作の後で、皿の中には何個のアメがあるか求めてください。
# ・2 ≤ N ≤ 2×10^5
# ・1 ≤ K ≤ 10^12
# ・1 ≤ Ai ≤ 10^6
N, K = mp()
A = lmp()
X = 0
fst = [-1] * N
# first[x]: xに始めてなる操作回数
fst[0] = 0
for i in range(1, N+1):
    X += A[X % N]
    K -= 1
    if fst[X % N] == -1:
        fst[X % N] = i
    else:
        loop = i - fst[X % N]
        # 2回目のとき loop を定義する
        break
    if K == 0:
        exit(print(X))
while K % loop != 0:
    # Kがloopで割り切れるまで操作を繰り返す
    K -= 1
    X += A[X % N]
if K == 0:
    exit(print(X))
loop_sum = 0
for _ in range(loop):
    # loop 1回分を計算する
    K -= 1
    loop_sum += A[X % N]
    X += A[X % N]
X += loop_sum * (K // loop)
# 繰り返しの分を加える
print(X)
