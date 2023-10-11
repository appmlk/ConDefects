def ip():return int(input())
def mp():return map(int, input().split())
def lmp():return list(map(int, input().split()))
# ABC268 D 1309 - Unique Username
# 高橋君はあるサービスで使うユーザー名を決めるのに困っています。彼を助けるプログラムを書いてください。
# 以下の条件をすべて満たす文字列 X を 1 つ求めてください。
# ・X は次の手順で得られる文字列である。
# N 個の文字列 S1,S2,…,SN を好きな順番で並べたものを S1′,S2′,…,SN′ とする。
# そして、S1′、(1 個以上の _ )、S2′、(1 個以上の _ )、…、(1 個以上の _ )、SN′ をこの順番で連結したものを X とする。
# ・X の文字数は 3 以上 16 以下である。
# ・X は M 個の文字列 T1,T2,…,TM のいずれとも一致しない。
# ただし、条件をすべて満たす文字列 X が存在しない場合は代わりに -1 と出力してください。
# ・1 ≤ N ≤ 8
# ・0 ≤ M ≤ 10^5
# ・1 ≤ |Si| ≤ 16
# ・N-1 + ∑ |Si| ≤ 16
# ・3 ≤ |Ti| ≤ 16
N, M = mp()
S = [input() for _ in range(N)]
T = {input() for _ in range(M)}
from itertools import permutations
if N == 1:
    exit(print('-1' if len(S[0]) < 3 or S[0] in T else S[0]))
rem = 16 - sum(len(s) for s in S)
for p in permutations(range(N)):
    cnt = [1] * (N+1)
    def dfs(i):
        # i番目に '_' を追加する関数
        if i == N-1:
            x = []
            for j in range(N):
                x.append(S[p[j]])
                if j < N-1:
                    x.append('_' * cnt[j])
            x = ''.join(x)
            if x not in T:
                exit(print(x))
            return
        r = rem - sum(cnt)
        for j in range(r+1):
            cnt[i] += j
            dfs(i+1)
            cnt[i] -= j
    dfs(0)
print(-1)