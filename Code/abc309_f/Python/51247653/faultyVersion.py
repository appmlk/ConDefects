from collections import defaultdict
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


class RangeDescendRangeMin:
    def __init__(self, n):
        self.n = n
        self.cover = [inf] * (4 * n)
        self.lazy_tag = [inf] * (4 * n)

    def _make_tag(self, i, val):
        self.cover[i] = min(self.cover[i], val)
        self.lazy_tag[i] = min(self.lazy_tag[i], val)
        return

    def _push_up(self, i):
        self.cover[i] = min(self.cover[i << 1], self.cover[(i << 1) | 1])
        return

    def _push_down(self, i):
        if self.lazy_tag[i] != inf:
            self.cover[i << 1] = min(self.cover[i << 1], self.lazy_tag[i])
            self.cover[(i << 1) | 1] = min(self.cover[(i << 1) | 1], self.lazy_tag[i])
            self.lazy_tag[i << 1] = min(self.lazy_tag[i << 1], self.lazy_tag[i])
            self.lazy_tag[(i << 1) | 1] = min(self.lazy_tag[(i << 1) | 1], self.lazy_tag[i])
            self.lazy_tag[i] = inf
        return

    def build(self, nums):

        stack = [(0, self.n - 1, 1)]
        while stack:
            s, t, i = stack.pop()
            if i >= 0:
                if s == t:
                    self._make_tag(i, nums[s])
                else:
                    stack.append((s, t, ~i))
                    m = s + (t - s) // 2
                    stack.append((s, m, i << 1))
                    stack.append((m + 1, t, (i << 1) | 1))
            else:
                i = ~i
                self._push_up(i)
        return

    def get(self):
        stack = [(0, self.n - 1, 1)]
        nums = [0] * self.n
        while stack:
            s, t, i = stack.pop()
            if s == t:
                nums[s] = self.cover[i]
                continue
            m = s + (t - s) // 2
            self._push_down(i)
            stack.append((s, m, i << 1))
            stack.append((m + 1, t, (i << 1) | 1))
        return nums

    def range_descend(self, left, right, val):
        # update the range descend

        stack = [(0, self.n - 1, 1)]
        while stack:
            a, b, i = stack.pop()
            if i >= 0:
                if left <= a and b <= right:
                    self._make_tag(i, val)
                    continue
                self._push_down(i)
                stack.append([a, b, ~i])
                m = a + (b - a) // 2
                if left <= m:
                    stack.append((a, m, i << 1))
                if right > m:
                    stack.append((m + 1, b, (i << 1) | 1))
            else:
                i = ~i
                self._push_up(i)
        return

    def range_min(self, left, right):
        # query the range min

        stack = [(0, self.n - 1, 1)]
        lowest = inf
        while stack:
            a, b, i = stack.pop()
            if left <= a and b <= right:
                lowest = min(lowest, self.cover[i])
                continue
            self._push_down(i)
            m = a + (b - a) // 2
            if left <= m:
                stack.append((a, m, i << 1))
            if right > m:
                stack.append((m + 1, b, (i << 1) | 1))
        return lowest


class Solution:
    def __init__(self):
        return

    @staticmethod
    def main(ac=FastIO()):
        """
        url: url of the problem
        tag: algorithm tag
        """
        n = ac.read_int()
        nodes = set()
        dct = [ac.read_list_ints() for _ in range(n)]
        for x, y, z in dct:
            nodes.add(x)
            nodes.add(y)
            nodes.add(z)
        ind = {num: i for i, num in enumerate(sorted(nodes))}
        m = len(ind)
        dct = [sorted([ind[w] for w in ls]) for ls in dct]
        ind = defaultdict(list)
        for x, y, z in dct:
            ind[x].append((y, z))
        tree = RangeDescendRangeMin(m)

        for x in ind:
            for y, z in ind[x]:
                if y:
                    pre = tree.range_min(0, y - 1)
                    if pre < z:
                        ac.st("Yes")
                        return
                if z:
                    pre = tree.range_min(0, z - 1)
                    if pre < y:
                        ac.st("Yes")
                        return
            for y, z in ind[x]:
                tree.range_descend(y, y, z)
                tree.range_descend(z, z, y)
        ac.st("No")
        return


Solution().main()
