#ABC342G Retroactive Range Chmax_禊

import heapq
import sys
input = sys.stdin.readline

#入力受取
N = int(input())
A = list(map(int,input().split()))

#node[i]: 現在の最小値(正負反転して扱う点に注意)
#delete[i]: ノードiのうち、削除待ちキュー
logN = (N - 1).bit_length()
size = 1 << logN
node = [[] for _ in range(2 * size)]
for i in range(N):
    node[i + size].append(-A[i])
delete = [[] for _ in range(2 * size)]

#クエリに回答
Q = int(input())
query = [None] * Q
for q in range(Q):
    t, *L = map(int, input().split())
    if t == 1:
        Lt, Rt, x = L
        Lt -= 1
        x = -x  #正負を反転
        query[q] = (Lt, Rt, x)

        #区間に加算
        Lt, Rt = Lt + size, Rt + size
        while Lt < Rt:
            if Lt & 1:
                heapq.heappush(node[Lt], x)
                Lt += 1
            if Rt & 1:
                Rt -= 1
                heapq.heappush(node[Rt], x)
            Lt >>= 1
            Rt >>= 1

    if t == 2:
        i = L[0] - 1
        Lt, Rt, x = query[i]

        #区間から削除  削除キューに入れる
        Lt, Rt = Lt + size, Rt + size
        while Lt < Rt:
            if Lt & 1:
                heapq.heappush(delete[Lt], x)
                Lt += 1
            if Rt & 1:
                Rt -= 1
                heapq.heappush(delete[Rt], x)
            Lt >>= 1
            Rt >>= 1

    if t == 3:
        i = L[0] - 1

        #fold
        i += size
        ans = 0
        while i > 0:
            #削除待ちキューを相殺
            while node[i] and delete[i] and node[i][0] == delete[i][0]:
                heapq.heappop(node[i])
                heapq.heappop(delete[i])

            #答えに反映
            if node[i]:
                ans = min(ans, node[i][0])
            i >>= 1
        print(- ans)
