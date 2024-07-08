from heapq import heappush, heappop
from math import inf
from sys import stdin


class FastIO:
    def __init__(self):
        self.random_seed = 0
        self.flush = False
        self.inf = 1 << 32
        return

    @staticmethod
    def read_int():
        return int(stdin.readline().rstrip())

    @staticmethod
    def read_float():
        return float(stdin.readline().rstrip())

    @staticmethod
    def read_list_ints():
        return list(map(int, stdin.readline().rstrip().split()))

    @staticmethod
    def read_list_ints_minus_one():
        return list(map(lambda x: int(x) - 1, stdin.readline().rstrip().split()))

    @staticmethod
    def read_str():
        return stdin.readline().rstrip()

    @staticmethod
    def read_list_strs():
        return stdin.readline().rstrip().split()

    def get_random_seed(self):
        import random
        self.random_seed = random.randint(0, 10 ** 9 + 7)
        return

    def st(self, x):
        return print(x, flush=self.flush)

    def lst(self, x):
        return print(*x, flush=self.flush)

    def flatten(self, lst):
        self.st("\n".join(str(x) for x in lst))
        return

    @staticmethod
    def max(a, b):
        return a if a > b else b

    @staticmethod
    def min(a, b):
        return a if a < b else b

    @staticmethod
    def ceil(a, b):
        return a // b + int(a % b != 0)

    @staticmethod
    def accumulate(nums):
        n = len(nums)
        pre = [0] * (n + 1)
        for i in range(n):
            pre[i + 1] = pre[i] + nums[i]
        return pre


class Dijkstra:
    def __init__(self):
        return

    @staticmethod
    def get_shortest_path(dct, src: int, initial=0):
        """template of shortest path by dijkstra"""
        #  which can to changed to be the longest path problem by opposite number
        n = len(dct)
        dis = [inf] * n
        stack = [(initial, src)]
        dis[src] = initial

        while stack:
            d, i = heappop(stack)
            if dis[i] < d:
                continue
            for j, w in dct[i]:
                dj = d + w
                if dj < dis[j]:
                    dis[j] = dj
                    heappush(stack, (dj, j))
        return dis

    @staticmethod
    def get_longest_path(dct, src: int, initial=0):
        """template of shortest path by dijkstra"""
        #  which can to changed to be the longest path problem by opposite number
        n = len(dct)
        dis = [inf] * n
        stack = [(-initial, src)]
        dis[src] = -initial

        while stack:
            d, i = heappop(stack)
            if dis[i] < d:
                continue
            for j, w in dct[i]:
                dj = d - w
                if dj < dis[j]:
                    dis[j] = dj
                    heappush(stack, (dj, j))
        return [-x for x in dis]

    @staticmethod
    def get_cnt_of_shortest_path(dct, src: int):
        """number of shortest path"""
        n = len(dct)
        dis = [inf] * n
        stack = [(0, src)]
        dis[src] = 0
        cnt = [0] * n
        cnt[src] = 1
        while stack:
            d, i = heappop(stack)
            if dis[i] < d:
                continue
            for j, w in dct[i]:
                dj = w + d
                if dj < dis[j]:
                    dis[j] = dj
                    cnt[j] = cnt[i]
                    # smaller than the shortest path
                    heappush(stack, (dj, j))
                elif dj == dis[j]:
                    # equal to the shortest path
                    cnt[j] += cnt[i]
        return cnt, dis


class Floyd:
    def __init__(self):
        return

    @staticmethod
    def get_cnt_of_shortest_path(edges, n):  # undirected
        dis = [[inf] * n for _ in range(n)]
        cnt = [[0] * n for _ in range(n)]
        for i in range(n):
            dis[i][i] = 0
            cnt[i][i] = 1
        for x, y, w in edges:
            dis[x][y] = dis[y][x] = w
            cnt[x][y] = cnt[y][x] = 1
        for k in range(n):  # mid point
            for i in range(n):  # start point
                if dis[i][k] == inf or i == k:
                    continue
                for j in range(i + 1, n):  # end point
                    if j == k:
                        continue
                    if dis[i][k] + dis[k][j] < dis[j][i]:
                        dis[i][j] = dis[j][i] = dis[i][k] + dis[k][j]
                        cnt[i][j] = cnt[j][i] = cnt[i][k] * cnt[k][j]
                    elif dis[i][k] + dis[k][j] == dis[j][i]:
                        cnt[i][j] += cnt[i][k] * cnt[k][j]
                        cnt[j][i] += cnt[i][k] * cnt[k][j]
        return cnt, dis


class Solution:
    def __init__(self):
        return

    @staticmethod
    def main(ac=FastIO()):
        """
        url: url of the problem
        tag: algorithm tag
        """
        n, m = ac.read_list_ints()
        edges = []
        dct = [[] for _ in range(n)]
        for _ in range(m):
            x, y, w = ac.read_list_ints_minus_one()
            edges.append((x, y, w + 1))
            dct[x].append((y, w + 1))
            dct[y].append((x, w + 1))
        dis = []
        cnt = []
        for i in range(n):
            cur_cnt, cur_dis = Dijkstra().get_cnt_of_shortest_path(dct, i)
            dis.append(cur_dis)
            cnt.append(cur_cnt)
        ans = sum(cnt[x][y] > 1 or dis[x][y] < w for x, y, w in edges)
        ac.st(ans)
        return

    @staticmethod
    def main2(ac=FastIO()):
        """
        url: url of the problem
        tag: algorithm tag
        """
        n, m = ac.read_list_ints()
        edges = []
        for _ in range(m):
            x, y, w = ac.read_list_ints_minus_one()
            edges.append((x, y, w + 1))
        cnt, dis = Floyd().get_cnt_of_shortest_path(edges, n)
        ans = sum(cnt[x][y] > 1 or dis[x][y] < w for x, y, w in edges)
        ac.st(ans)
        return
    
Solution().main2()
