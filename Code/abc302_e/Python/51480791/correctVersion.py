#再帰はCpython,その他はpypy
import sys
sys.setrecursionlimit(1000000)
from collections import defaultdict


N, Q = map(int, input().split())


dic = defaultdict(set)

ans = N
for i in range(Q):
    query = [*map(int, input().split())]
    if query[0] == 2:
        if len(dic[query[1]]) == 0:
            ans -= 1
        for j in dic[query[1]]:
            dic[j].remove(query[1])
            if len(dic[j]) == 0:
                ans += 1

        dic[query[1]] = set()
        ans += 1

    else:
        dic[query[1]].add(query[2])
        dic[query[2]].add(query[1])
        if len(dic[query[1]]) == 1:
            ans -= 1
        if len(dic[query[2]]) == 1:
            ans -= 1

    print(ans)









