from itertools import permutations
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

    def yes(self, s=None):
        self.st("Yes" if not s else s)
        return

    def no(self, s=None):
        self.st("No" if not s else s)
        return

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


class Solution:
    def __init__(self):
        return

    @staticmethod
    def main(ac=FastIO()):
        """
        url: url of the problem
        tag: algorithm tag
        """

        def ceil(xxx, yyy):
            return xxx // yyy + int(xxx % yyy != 0)

        x, y, a, b, c = ac.read_list_ints()
        for perm in permutations([a, b, c], 3):
            aa, bb, cc = perm
            yy = ceil(aa, x)
            rest = y - yy
            if rest > 0 and ceil(bb, rest) + ceil(cc, rest) <= x:
                ac.yes()
                return

            if ceil(aa, x) + ceil(bb, x) + ceil(cc, x) <= y:
                ac.yes()
                return

            if ceil(aa, y) + ceil(bb, y) + ceil(cc, y) <= x:
                ac.yes()
                return

            xx = ceil(aa, y)
            rest = x - xx
            if rest > 0 and ceil(bb, rest) + ceil(cc, rest) <= y:
                ac.yes()
                return
        ac.no()
        return


Solution().main()
