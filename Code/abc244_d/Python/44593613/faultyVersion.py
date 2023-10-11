from collections import deque, defaultdict
from decimal import Decimal
from bisect import bisect_left, bisect_right
from heapq import heapify, heappush, heappop
from itertools import permutations, combinations
from random import randrange, choices
from string import ascii_lowercase, ascii_uppercase
from os import environ
from copy import deepcopy
import math
import sys
sys.setrecursionlimit(10000000)


class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n

    def find(self, x):
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]

    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if x == y:
            return

        if self.parents[x] > self.parents[y]:
            x, y = y, x

        self.parents[x] += self.parents[y]
        self.parents[y] = x

    def size(self, x):
        return -self.parents[self.find(x)]

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def members(self, x):
        root = self.find(x)
        return [i for i in range(self.n) if self.find(i) == root]

    def roots(self):
        return [i for i, x in enumerate(self.parents) if x < 0]

    def group_count(self):
        return len(self.roots())

    def all_group_members(self):
        group_members = defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members

    def __str__(self):
        return '\n'.join(f'{r}: {m}' for r, m in self.all_group_members().items())


s = input().split()
t = input().split()


def f(cnt, s0, s1, s2):
    if cnt == 10:
        return s0 == s[0] and s1 == s[1] and s2 == s[2]
    return f(cnt + 1, s1, s0, s2) or f(cnt + 1, s0, s2, s1) or f(cnt + 1, s2, s1, s0)


ans = f(0, s[0], s[1], s[2])
print('Yes') if ans else print('No')
