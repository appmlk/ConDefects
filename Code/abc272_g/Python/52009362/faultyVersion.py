import math
import random
from collections import Counter
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


class NumFactor:
    def __init__(self):
        return

    @staticmethod
    def get_all_factor(num):  # faster when 1 <= num <= 10**6!
        """Obtain all factors of an integer, including 1 and itself"""
        assert num >= 1
        factor = set()
        for i in range(1, int(math.sqrt(num)) + 1):
            if num % i == 0:
                factor.add(i)
                factor.add(num // i)
        return sorted(list(factor))


class Solution:
    def __init__(self):
        return

    @staticmethod
    def main(ac=FastIO()):
        """
        url: https://atcoder.jp/contests/abc272/tasks/abc272_g
        tag: random_guess|brute_force|num_factor|classical
        """
        n = ac.read_int()
        nums = ac.read_list_ints()
        ceil = 10 ** 9
        nf = NumFactor()
        for _ in range(10):
            i, j = random.randint(0, n - 1), random.randint(0, n - 1)
            while i == j:
                j = random.randint(0, n - 1)
            lst = nf.get_all_factor(abs(nums[i] - nums[j]))
            for m in lst:
                if 3 <= m <= ceil:
                    cur = [num % m for num in nums]
                    if max(Counter(cur).values()) * 2 > n:
                        ac.st(m)
                        return
        ac.st(-1)
        return


Solution().main()
